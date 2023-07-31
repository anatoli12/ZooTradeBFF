package com.tinqin.bff.rest.controller;

import com.tinqin.bff.api.operation.security.auth.AuthenticationOperation;
import com.tinqin.bff.api.operation.security.auth.AuthenticationRequest;
import com.tinqin.bff.api.operation.security.auth.AuthenticationResponse;
import com.tinqin.bff.api.operation.security.register.RegisterOperation;
import com.tinqin.bff.api.operation.security.register.RegisterRequest;
import com.tinqin.bff.api.operation.security.register.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationOperation authenticationOperation;
    private final RegisterOperation registerOperation;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(registerOperation.process(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationOperation.process(request));
    }
}
