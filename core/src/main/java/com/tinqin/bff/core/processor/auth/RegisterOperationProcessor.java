package com.tinqin.bff.core.processor.auth;

import com.tinqin.bff.api.operation.security.JwtOperation;
import com.tinqin.bff.api.operation.security.register.RegisterOperation;
import com.tinqin.bff.api.operation.security.register.RegisterRequest;
import com.tinqin.bff.api.operation.security.register.RegisterResponse;
import com.tinqin.bff.persistence.model.Role;
import com.tinqin.bff.persistence.model.User;
import com.tinqin.bff.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterOperationProcessor implements RegisterOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtOperation jwtOperation;

    @Override
    public RegisterResponse process(RegisterRequest input) {
        var user = User.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtOperation.generateToken(user);
        return RegisterResponse.builder()
                .token(jwtToken)
                .build();
    }
}
