package com.tinqin.bff.core.processor.auth;

import com.tinqin.bff.api.operation.security.JwtOperation;
import com.tinqin.bff.api.operation.security.auth.AuthenticationOperation;
import com.tinqin.bff.api.operation.security.auth.AuthenticationRequest;
import com.tinqin.bff.api.operation.security.auth.AuthenticationResponse;
import com.tinqin.bff.api.operation.security.register.RegisterResponse;
import com.tinqin.bff.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationOperationProcessor implements AuthenticationOperation {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtOperation jwtOperation;

    @Override
    public AuthenticationResponse process(AuthenticationRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        var user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        var jwtToken = jwtOperation.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
