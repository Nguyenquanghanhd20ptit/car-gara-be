package com.example.carmanagement.commons.data.dto.momo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class InformationOrderMomo {
    private Long amount;
    private String orderId;
    private String redirectUrl;
    private UserInfoMomo userInfo;
    private List<ItemMomo> items;
}
