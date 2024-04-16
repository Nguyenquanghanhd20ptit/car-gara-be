package com.example.carmanagement.commons.data.constant;

import java.util.HashMap;
import java.util.Map;

public class CarConstant {
    public static final Integer RETURNED_CODE = 0;
    public static final Integer NOT_DELIVERED_CODE = 1;
    public static final Integer NOT_FIXED_CODE = 2;
    public static final Integer FIXING_CODE = 3;
    public static final Integer FIXED_CODE = 4;
    public static final String RETURNED_MESSAGE = "Đã trả xe";
    public static final String NOT_DELIVERED_MESSAGE = "Chưa đươợc giao đến";
    public static final String NOT_FIXED_MESSAGE= "Chưa sửa";
    public static final String FIXING_MESSAGE = "Đang sửa";
    public static final String FIXED_MESSAGE = "Đã sửa xong";

    public static Map<Integer, String> CAR_STATUS = new HashMap<>() {{
        put(RETURNED_CODE, RETURNED_MESSAGE);
        put(NOT_DELIVERED_CODE, NOT_DELIVERED_MESSAGE);
        put(NOT_FIXED_CODE, NOT_FIXED_MESSAGE);
        put(FIXING_CODE, FIXING_MESSAGE);
        put(FIXED_CODE, FIXED_MESSAGE);
    }};
}
