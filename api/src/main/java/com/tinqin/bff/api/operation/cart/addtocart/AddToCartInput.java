package com.tinqin.bff.api.operation.cart.addtocart;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartInput implements ProcessorInput {
    private UUID userId;
    private UUID refItemId;
    private Integer quantity;
}
