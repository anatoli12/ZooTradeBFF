package com.tinqin.bff.rest.controller;

import com.tinqin.bff.api.operation.item.findall.FindAllItemsOutput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdInput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOperation;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final FindItemByIdOperation findItemByIdOperation;

    @GetMapping("/{id}")
    public ResponseEntity<FindItemByIdOutput> findItemById(@PathVariable String id) {
        return ResponseEntity.ok(findItemByIdOperation.process(
                FindItemByIdInput.builder()
                        .id(UUID.fromString(id))
                        .build()));
    }

//    @GetMapping
//    public ResponseEntity<FindAllItemsOutput> findAllItems(){
//
//    }
}
