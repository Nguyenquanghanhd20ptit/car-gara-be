package com.example.carmanagement.rest.controller;

import com.example.carmanagement.commons.data.dto.momo.InformationOrderMomo;
import com.example.carmanagement.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.carmanagement.service.orderMomo.CreateTransactionMomoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/momo")
public class OrderMomoController {
    @Autowired
    private CreateTransactionMomoService orderSendToMomoService;
    @PostMapping(value = "/create-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "get orderMomo by orderId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InformationOrderMomo.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> createTransaction(@RequestBody OrderMomoRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return orderSendToMomoService.createTransactionMomo(request);
    }
}
