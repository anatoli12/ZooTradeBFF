package com.tinqin.bff.api.operation.security.register;

import com.tinqin.bff.api.core.ProcessorOutput;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse implements ProcessorOutput {
    private String token;
}