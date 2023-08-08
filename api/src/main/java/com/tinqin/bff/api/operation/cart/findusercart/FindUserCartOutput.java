package com.tinqin.bff.api.operation.cart.findusercart;

import com.tinqin.bff.api.core.ProcessorOutput;
import com.tinqin.bff.api.operation.cart.CartItemDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserCartOutput implements ProcessorOutput {
    private UUID userId;
    private List<CartItemDTO> cartItems;
}
