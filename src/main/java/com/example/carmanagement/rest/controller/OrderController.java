package com.example.carmanagement.rest.controller;

import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.request.order.OrderRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.order.OrderResponse;
import com.example.carmanagement.service.order.OrderAddService;
import com.example.carmanagement.service.order.OrderDetailService;
import com.example.carmanagement.service.order.OrderSearchService;
import com.example.carmanagement.service.order.UpdateStatusOrderIsSuccessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderAddService orderAddService;
    @PostMapping(value = "/addNew", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "add new order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> addNew(@RequestBody @Valid OrderRequest orderRequest){
        if(ObjectUtils.isEmpty(orderRequest)){
            return ResponseEntity.badRequest().build();
        }
        return orderAddService.addNew(orderRequest);
    }

    @Autowired
    private OrderDetailService orderDetailService;
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "get order byId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> get(@PathVariable("id") Integer id){
        if(ObjectUtils.isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        return orderDetailService.getById(id);
    }

    @Autowired
    private OrderSearchService orderSearchService;

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = " search order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> search(@RequestBody SearchRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return orderSearchService.search(request);
    }

    @Autowired
    private UpdateStatusOrderIsSuccessService updateStatusOrderIsSuccessService;

    @PostMapping(value = "/update-status/isPaid", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = " update status order")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> updateStatus(@RequestParam("id") Integer id){
        if(ObjectUtils.isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        return updateStatusOrderIsSuccessService.updateStatus(id);
    }
}
