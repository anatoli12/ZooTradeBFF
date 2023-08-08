package com.tinqin.bff.rest.controller;

import com.tinqin.bff.api.operation.cart.addtocart.ManipulateItemCartInput;
import com.tinqin.bff.api.operation.cart.addtocart.ManipulateItemCartOutput;
import com.tinqin.bff.api.operation.cart.addtocart.ManipulateItemInCartOperation;
import com.tinqin.bff.api.operation.cart.findusercart.FindUserCartInput;
import com.tinqin.bff.api.operation.cart.findusercart.FindUserCartOperation;
import com.tinqin.bff.api.operation.cart.findusercart.FindUserCartOutput;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ManipulateItemInCartOperation manipulateItemInCartOperation;
    private final FindUserCartOperation findUserCartOperation;

    @PostMapping
    @Transactional
    public ResponseEntity<ManipulateItemCartOutput> manipulateItem(@RequestBody ManipulateItemCartInput input) {
        return ResponseEntity.ok(manipulateItemInCartOperation.process(input));
    }

    @GetMapping("/{userId}")
    @Transactional
    public ResponseEntity<FindUserCartOutput> findUserCart(@PathVariable UUID userId) {
        FindUserCartInput i = FindUserCartInput.builder()
                .userId(userId)
                .build();
        return ResponseEntity.ok(findUserCartOperation.process(i));
    }

}
