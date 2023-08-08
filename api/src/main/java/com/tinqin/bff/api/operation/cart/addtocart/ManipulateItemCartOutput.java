package com.tinqin.bff.api.operation.cart.addtocart;

import com.tinqin.bff.api.core.ProcessorOutput;
import com.tinqin.bff.api.operation.cart.CartItemDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManipulateItemCartOutput implements ProcessorOutput {
    private UUID userId;
    private List<CartItemDTO> cartItems;
}
