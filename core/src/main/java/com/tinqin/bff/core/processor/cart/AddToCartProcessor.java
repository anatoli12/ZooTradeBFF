package com.tinqin.bff.core.processor.cart;

import com.tinqin.bff.api.operation.cart.CartItemDTO;
import com.tinqin.bff.api.operation.cart.addtocart.AddToCartInput;
import com.tinqin.bff.api.operation.cart.addtocart.AddToCartOperation;
import com.tinqin.bff.api.operation.cart.addtocart.AddToCartOutput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdInput;
import com.tinqin.bff.core.processor.item.FindItemByIdProcessor;
import com.tinqin.bff.persistence.model.CartItem;
import com.tinqin.bff.persistence.model.User;
import com.tinqin.bff.persistence.repository.CartItemRepository;
import com.tinqin.bff.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddToCartProcessor implements AddToCartOperation {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final FindItemByIdProcessor findItemByIdProcessor;

    public AddToCartOutput process(AddToCartInput input) {
        final User user = userRepository.findById(input.getUserId())
                .orElseThrow(); //TODO: Appropriate exception

        // check if item exists
        findItemByIdProcessor.process(FindItemByIdInput.builder().id(input.getRefItemId()).build());

        /*
          The code first tries to find an existing CartItem in the user's cart with a matching refItemId using the findFirst() method after filtering the stream of cart items.
          If a matching CartItem is found, the quantity of that item is updated by adding the quantity from the input object.
          If no matching CartItem is found, the orElseGet() method is triggered, and a new CartItem is created using the CartItem.builder(). It is then added to the collection of cart items for the user's cart.
          Finally, the map() function returns the existing CartItem with updated quantity or the newly created CartItem.
         */
        final CartItem newCartItem = user.getCart().getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getRefItemId().equals(input.getRefItemId()))
                .findFirst()
                .map(cartItem -> {
                            cartItem.setQuantity(cartItem.getQuantity() + input.getQuantity());
                            return cartItem;
                        }
                )
                .orElseGet(() -> {
                    CartItem cartItem = CartItem.builder()
                            .refItemId(input.getRefItemId())
                            .quantity(input.getQuantity())
                            .build();
                    user.getCart().getCartItems().add(cartItem);
                    return cartItem;
                });
        cartItemRepository.save(newCartItem);
        userRepository.save(user);
// TODO: Check code
// TODO: Implement storage check and storage decrement
        List<CartItemDTO> cartItemDTOList = user.getCart().getCartItems()
                .stream()
                .map(cartItem ->
                        CartItemDTO.builder()
                                .id(cartItem.getId())
                                .refItemId(cartItem.getRefItemId())
                                .quantity(cartItem.getQuantity())
                                .build()
                )
                .toList();
        return AddToCartOutput.builder()
                .userId(user.getId())
                .cartItems(cartItemDTOList)
                .build();
    }
}
