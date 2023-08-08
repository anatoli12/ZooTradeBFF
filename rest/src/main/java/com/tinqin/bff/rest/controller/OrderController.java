package com.tinqin.bff.rest.controller;

import com.tinqin.bff.api.operation.order.completeorder.CompleteOrderInput;
import com.tinqin.bff.api.operation.order.completeorder.CompleteOrderOperation;
import com.tinqin.bff.api.operation.order.completeorder.CompleteOrderOutput;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final CompleteOrderOperation completeOrderOperation;

    @PostMapping
    @Transactional
    public ResponseEntity<CompleteOrderOutput> completeOrder(@RequestBody @Parameter(
            name = "userid", description = "user id", example = "1db8ad10-0fdc-4d9b-80f2-c10922ae3ba5")
                                                             CompleteOrderInput input) {
        return ResponseEntity.ok(completeOrderOperation.process(input));
    }


}
