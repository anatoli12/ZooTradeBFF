package com.tinqin.bff.api.operation.security.register;

import com.tinqin.bff.api.core.ProcessorInput;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest implements ProcessorInput {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
