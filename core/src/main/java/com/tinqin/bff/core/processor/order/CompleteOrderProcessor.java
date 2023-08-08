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
import com.tinqin.storage.api.operation.storage.changestoragequantity.ChangeStorageQuantityOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

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
                .orElseThrow(); //TODO: Appropriate exception

        for (CartItem c : user.getCart().getCartItems()) {

            StorageBaseDTO storageDto = storageRestExport.findByItemId(String.valueOf(c.getRefItemId())).getStorageBaseDTO();

            if (storageDto.getQuantity() < c.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity of item " + c.getRefItemId());
            }
        }
        BigDecimal totalPrice = BigDecimal.valueOf(0.00);
        for (CartItem c : user.getCart().getCartItems()) {

            storageRestExport.updateStorageQuantity(ChangeStorageQuantityInput.builder()
                    .itemId(String.valueOf(c.getRefItemId()))
                    .quantity(c.getQuantity()*(-1))
                    .build());
            totalPrice = totalPrice.add(new BigDecimal(
                    storageRestExport
                            .findByItemId(String.valueOf(c.getRefItemId()))
                            .getStorageBaseDTO()
                            .getPrice()).multiply(new BigDecimal(c.getQuantity())));
        }

        Order order = Order.builder()
                .createDate(new Timestamp(System.currentTimeMillis()))
                .user(user)
                .cartItems(user.getCart().getCartItems())
                .totalPrice(totalPrice)
                .build();

        orderRepository.save(order);

        for (CartItem c : user.getCart().getCartItems()) {
            c.setCart(null);
            c.setOrder(order);
            cartItemRepository.save(c);
        }

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
