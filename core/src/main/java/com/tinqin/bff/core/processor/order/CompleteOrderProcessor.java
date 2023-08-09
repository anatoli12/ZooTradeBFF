package com.tinqin.bff.core.processor.order;

import com.tinqin.bff.api.operation.cart.CartItemDTO;
import com.tinqin.bff.api.operation.order.completeorder.CompleteOrderInput;
import com.tinqin.bff.api.operation.order.completeorder.CompleteOrderOperation;
import com.tinqin.bff.api.operation.order.completeorder.CompleteOrderOutput;
import com.tinqin.bff.persistence.model.CartItem;
import com.tinqin.bff.persistence.model.Order;
import com.tinqin.bff.persistence.model.User;
import com.tinqin.bff.persistence.repository.CartItemRepository;
import com.tinqin.bff.persistence.repository.OrderRepository;
import com.tinqin.bff.persistence.repository.UserRepository;
import com.tinqin.restexport.StorageRestExport;
import com.tinqin.storage.api.operation.storage.StorageBaseDTO;
import com.tinqin.storage.api.operation.storage.changestoragequantity.ChangeStorageQuantityInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CompleteOrderProcessor implements CompleteOrderOperation {
    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;
    private final StorageRestExport storageRestExport;
    private final OrderRepository orderRepository;
    Logger logger = LoggerFactory.getLogger(CompleteOrderProcessor.class);

    @Override
    public CompleteOrderOutput process(CompleteOrderInput input) {
        final User user = userRepository.findById(input.getUserId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<CartItem> cartItems = user.getCart().getCartItems();

        boolean hasInsufficientQuantity = cartItems.parallelStream()
                .map(c -> storageRestExport.findByItemId(String.valueOf(c.getRefItemId())).getStorageBaseDTO())
                .anyMatch(storageDto -> storageDto.getQuantity() < cartItems.stream()
                        .filter(c -> String.valueOf(c.getRefItemId()).equals(storageDto.getItemId()))
                        .mapToInt(CartItem::getQuantity)
                        .sum());

        if (hasInsufficientQuantity) {
            throw new IllegalArgumentException("Not enough quantity of some items");
        }

        BigDecimal totalPrice = cartItems.parallelStream()
                .map(c -> {
                    StorageBaseDTO storageDto = storageRestExport.findByItemId(String.valueOf(c.getRefItemId())).getStorageBaseDTO();
                    storageRestExport.updateStorageQuantity(ChangeStorageQuantityInput.builder()
                            .itemId(String.valueOf(c.getRefItemId()))
                            .quantity(c.getQuantity() * (-1))
                            .build());
                    return new BigDecimal(storageDto.getPrice()).multiply(new BigDecimal(c.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .createDate(new Timestamp(System.currentTimeMillis()))
                .user(user)
                .cartItems(user.getCart().getCartItems())
                .totalPrice(totalPrice)
                .build();

        orderRepository.save(order);
        cartItems.parallelStream()
                .forEach(c -> {
                    c.setCart(null);
                    c.setOrder(order);
                    cartItemRepository.save(c);
                });

        CompleteOrderOutput result = CompleteOrderOutput.builder()
                .userId(order.getUser().getId())
                .orderId(order.getOrderId())
                .createDate(order.getCreateDate())
                .totalPrice(order.getTotalPrice())
                .cartItems(order.getCartItems().stream()
                        .map(cartItem ->
                                CartItemDTO.builder()
                                        .id(cartItem.getId())
                                        .refItemId(cartItem.getRefItemId())
                                        .quantity(cartItem.getQuantity())
                                        .build())
                        .toList())
                .build();
        user.getCart().setCartItems(new ArrayList<>());
        cartItemRepository.saveAll(user.getCart().getCartItems());
        userRepository.save(user);

        return result;
    }
}
