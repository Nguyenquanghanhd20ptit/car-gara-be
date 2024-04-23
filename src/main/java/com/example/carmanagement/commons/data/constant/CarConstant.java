package com.example.carmanagement.commons.data.constant;

import java.util.HashMap;
import java.util.Map;

public class CarConstant {
    public static final Integer CAR_RETURNED_CODE = 0;
    public static final Integer  CAR_NOT_DELIVERED_CODE = 1;
    public static final Integer  CAR_NOT_FIXED_CODE = 2;
    public static final Integer  CAR_FIXING_CODE = 3;
    public static final Integer  CAR_FIXED_CODE = 4;
    public static final String  CAR_RETURNED_MESSAGE = "Đã trả xe";
    public static final String  CAR_NOT_DELIVERED_MESSAGE = "Chưa được giao đến";
    public static final String  CAR_NOT_FIXED_MESSAGE= "Chưa sửa";
    public static final String  CAR_FIXING_MESSAGE = "Đang sửa";
    public static final String  CAR_FIXED_MESSAGE = "Đã sửa xong";

    public static Map<Integer, String> CAR_STATUS = new HashMap<>() {{
        put( CAR_RETURNED_CODE,  CAR_RETURNED_MESSAGE);
        put( CAR_NOT_DELIVERED_CODE,  CAR_NOT_DELIVERED_MESSAGE);
        put( CAR_NOT_FIXED_CODE,  CAR_NOT_FIXED_MESSAGE);
        put( CAR_FIXING_CODE,  CAR_FIXING_MESSAGE);
        put( CAR_FIXED_CODE,  CAR_FIXED_MESSAGE);
    }};
}
