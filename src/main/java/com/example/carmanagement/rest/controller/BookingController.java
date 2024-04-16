package com.example.carmanagement.rest.controller;

import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import com.example.carmanagement.service.accessory.AccessorySearchService;
import com.example.carmanagement.service.booking.BookingDetailService;
import com.example.carmanagement.service.booking.BookingSearchService;
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

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingSearchService bookingSearchService;
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = " search booking")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> search(@RequestBody SearchRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return bookingSearchService.search(request);
    }

    @Autowired
    private BookingDetailService bookingDetailService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "get booking by Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookingResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> get(@PathVariable("id") Integer id){
        if(ObjectUtils.isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        return bookingDetailService.getById(id);
    }
}
