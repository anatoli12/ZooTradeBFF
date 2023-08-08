package com.tinqin.bff.api.operation.cart.findusercart;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserCartInput implements ProcessorInput {
    private UUID userId;
}
