package com.tinqin.bff.rest.controller;

import com.tinqin.api.operation.item.findall.FindAllItemsOperation;
import com.tinqin.api.operation.item.findall.FindAllItemsOutput;
import com.tinqin.api.operation.item.findallregex.FindAllItemsRegexOutput;
import com.tinqin.api.operation.item.findbyid.FindItemByIdOperation;
import com.tinqin.api.operation.item.findbyid.FindItemByIdOutput;
import com.tinqin.restexport.ZooStoreRestExport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final FindItemByIdOperation findItemByIdOperation;
    private final FindAllItemsOperation findAllItemsOperation;
    private final ZooStoreRestExport zooStoreRestExport;

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
        return ResponseEntity.ok(zooStoreRestExport.findItemById(id));
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Forbidden access", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<FindAllItemsOutput> findAll(@RequestParam Boolean showDeleted,
                                                      @RequestParam Optional<Integer> pageNumber,
                                                      @RequestParam Optional<Integer> pageSize,
                                                      @RequestParam Optional<String> titleContains,
                                                      @RequestParam Optional<String> descriptionContains) {
        return ResponseEntity.ok(zooStoreRestExport.findAll(
                showDeleted,
                pageNumber,
                pageSize,
                titleContains,
                descriptionContains
        ));
    }

    @GetMapping("/regex")
    public ResponseEntity<FindAllItemsRegexOutput> findItemsByRegex(@RequestParam Optional<Integer> pageNumber,
                                                                    @RequestParam Optional<Integer> pageSize,
                                                                    @RequestParam String regex) {
        return ResponseEntity.ok(zooStoreRestExport.findRegexItems(
                pageNumber,
                pageSize,
                regex
        ));
    }
}
