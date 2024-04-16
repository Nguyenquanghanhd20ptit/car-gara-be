package com.example.carmanagement.rest.controller;

import com.cloudinary.Search;
import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.request.customer.CustomerRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.service.customer.*;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDetailService customerDetailService;
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "get customer byId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookingEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> get(@PathVariable("id") Integer id){
        if(ObjectUtils.isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        return customerDetailService.getById(id);
    }

    @Autowired
    private CustomerSearchService customerSearchService;

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = " search customer")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> search(@RequestBody SearchRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return customerSearchService.searchCustomer(request);
    }


    @Autowired
    private CustomerAddNewService customerAddNewService;
    @PostMapping(value = "/addNew", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "add new customer")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> addNew(@RequestBody @Valid CustomerRequest customerRequest){
        if(ObjectUtils.isEmpty(customerRequest)){
            return ResponseEntity.badRequest().build();
        }
        return customerAddNewService.addNew(customerRequest);
    }

    @Autowired
    private CustomerUpdateService customerUpdateService;
    @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "update customer")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> update(@PathVariable("id") Integer id,
                                         @RequestBody @Valid CustomerRequest customerRequest){
        if(ObjectUtils.isEmpty(customerRequest)){
            return ResponseEntity.badRequest().build();
        }
        return customerUpdateService.update(id,customerRequest);
    }


    @Autowired
    private CustomerDeleteService customerDeleteService;

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete customer by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        return customerDeleteService.deleteById(id);
    }
}
