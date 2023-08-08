package com.tinqin.bff.core.processor.cart;

import com.tinqin.bff.api.operation.cart.CartItemDTO;
import com.tinqin.bff.api.operation.cart.findusercart.FindUserCartInput;
import com.tinqin.bff.api.operation.cart.findusercart.FindUserCartOperation;
import com.tinqin.bff.api.operation.cart.findusercart.FindUserCartOutput;
import com.tinqin.bff.persistence.model.User;
import com.tinqin.bff.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserCartProcessor implements FindUserCartOperation {

    private final UserRepository userRepository;
    @Override
    public FindUserCartOutput process(FindUserCartInput input) {
        final User user = userRepository.findById(input.getUserId())
                .orElseThrow(); //TODO: Appropriate exception

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
        return FindUserCartOutput.builder()
                .userId(user.getId())
                .cartItems(cartItemDTOList)
                .build();
    }
}
