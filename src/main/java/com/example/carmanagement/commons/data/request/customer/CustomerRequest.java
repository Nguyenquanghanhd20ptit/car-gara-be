package com.example.carmanagement.commons.data.request.customer;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.example.carmanagement.commons.data.constant.RegexConstant.EMAIL_PATTERN;
import static com.example.carmanagement.commons.data.constant.RegexConstant.PHONE_NUMBER_PATTERN;

@Data
@Accessors(chain = true)
public class CustomerRequest {
    @NotBlank(message = "name is not null")
    private String name;
    @NotBlank(message = "email is not null")
    @Pattern(message = "email is invalid",regexp = EMAIL_PATTERN)
    private String email;
    private String address;
    @NotBlank(message = "phone is not null")
    @Pattern(message = "phone is invalid",regexp = PHONE_NUMBER_PATTERN)
    private String phoneNumber;
    private String avatarUrl;
}
