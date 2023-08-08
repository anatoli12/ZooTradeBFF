package com.tinqin.bff.api.operation.order.completeorder;

import com.tinqin.bff.api.core.ProcessorOutput;
import com.tinqin.bff.api.operation.cart.CartItemDTO;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompleteOrderOutput implements ProcessorOutput {

    private UUID orderId;
    private Timestamp createDate;
    private BigDecimal totalPrice;
    private UUID userId;
    private List<CartItemDTO> cartItems;
}
