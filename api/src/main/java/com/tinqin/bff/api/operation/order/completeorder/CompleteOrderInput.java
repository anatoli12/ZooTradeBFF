package com.tinqin.bff.api.operation.order.completeorder;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompleteOrderInput implements ProcessorInput {
    private UUID userId;
}
