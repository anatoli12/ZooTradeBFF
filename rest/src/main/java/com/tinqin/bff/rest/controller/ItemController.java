package com.tinqin.bff.rest.controller;

import com.tinqin.bff.api.operation.item.findall.FindAllItemsInput;
import com.tinqin.bff.api.operation.item.findall.FindAllItemsOperation;
import com.tinqin.bff.api.operation.item.findall.FindAllItemsOutput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdInput;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOperation;
import com.tinqin.bff.api.operation.item.findbyid.FindItemByIdOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final FindItemByIdOperation findItemByIdOperation;
    private final FindAllItemsOperation findAllItemsOperation;

    @GetMapping("/{id}")
    @Operation(summary = "Get item by ID", description = "Retrieve an item by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Forbidden access", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<FindItemByIdOutput> findItemById(
            @PathVariable @Parameter(name = "id", description = "Item id", example = "8470023a-5b70-45ff-8076-61748a6a19e3")
            String id) {
        return ResponseEntity.ok(findItemByIdOperation.process(
                FindItemByIdInput.builder()
                        .id(UUID.fromString(id))
                        .build()));
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Forbidden access", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<FindAllItemsOutput> findAll(@RequestParam Boolean showDeleted,
                                                      @RequestParam Integer pageNumber,
                                                      @RequestParam Integer pageSize) {
        FindAllItemsInput input = FindAllItemsInput.builder()
                .showDeleted(showDeleted)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
        return ResponseEntity.ok(
                findAllItemsOperation.process(input));
    }
}
