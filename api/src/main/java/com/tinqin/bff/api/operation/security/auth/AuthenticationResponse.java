package com.tinqin.bff.api.operation.security.auth;

import com.tinqin.bff.api.core.ProcessorOutput;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements ProcessorOutput {
    private String token;
}
