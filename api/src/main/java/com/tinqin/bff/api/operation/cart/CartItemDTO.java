package com.tinqin.bff.api.operation.cart;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private UUID id;
    private UUID refItemId;
    private Integer quantity;
//    private UUID userId;
}
