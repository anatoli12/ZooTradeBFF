package com.tinqin.bff.rest.controller;

import com.tinqin.bff.api.operation.cart.addtocart.AddToCartInput;
import com.tinqin.bff.api.operation.cart.addtocart.AddToCartOperation;
import com.tinqin.bff.api.operation.cart.addtocart.AddToCartOutput;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final AddToCartOperation addToCartOperation;

    /**
     * {
     *   "userId": "d2994021-c250-4e87-b519-ef0ca0886a0b",
     *   "refItemId": "8470023a-5b70-45ff-8076-61748a6a19e3",
     *   "quantity": 5
     * }
     */
    @PostMapping
    @Transactional
    public ResponseEntity<AddToCartOutput> addToCart(@RequestBody AddToCartInput input) {
        return ResponseEntity.ok(addToCartOperation.process(input));
    }

}
