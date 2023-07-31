package com.tinqin.bff.api.operation.security.auth;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthenticationRequest implements ProcessorInput {
    private String email;
    private String password;
}
