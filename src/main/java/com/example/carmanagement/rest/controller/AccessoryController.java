package com.example.carmanagement.rest.controller;

import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.service.accessory.AccessorySearchService;
import com.example.carmanagement.service.customer.CustomerSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accessory")
public class AccessoryController {
    @Autowired
    private AccessorySearchService accessorySearchService;
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = " search accessory")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> search(@RequestBody SearchRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return accessorySearchService.searchAccessory(request);
    }

}
