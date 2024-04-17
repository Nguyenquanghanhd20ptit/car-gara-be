package com.example.carmanagement.commons.data.dto.momo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ItemMomo {
    private String id;
    private String name;
    private String category;
    private String imageUrl;
    private Long price;
    private String currency;
    private Integer quantity;
    private String unit;
    private Long totalPrice;
}
