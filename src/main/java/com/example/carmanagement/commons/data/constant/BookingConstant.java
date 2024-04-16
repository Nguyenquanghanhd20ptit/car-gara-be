package com.example.carmanagement.commons.data.constant;

import java.util.HashMap;
import java.util.Map;

public class BookingConstant {
    public static final Integer PAID_CODE = 0;
    public static final Integer UNCONFIRMED_CODE = 1;
    public static final Integer CONFIRMED_CODE = 2;
    public static final Integer NOT_ACCOMPLISHED_CODE = 3;
    public static final Integer ACCOMPLISHED_CODE = 4;
    public static final String PAID_MESSAGE = "Đã thanh toán";
    public static final String UNCONFIRMED_MESSAGE = "Chưa được xác nhận";
    public static final String CONFIRMED_MESSAGE= "Đã được xác nhận";
    public static final String NOT_ACCOMPLISHED_MESSAGE = "Chưa hoàn thành";
    public static final String ACCOMPLISHED_MESSAGE = "Đã hoàn thành";

    public static Map<Integer, String>  BOOKING_STATUS = new HashMap<>() {{
        put(PAID_CODE, PAID_MESSAGE);
        put(UNCONFIRMED_CODE, UNCONFIRMED_MESSAGE);
        put(CONFIRMED_CODE, CONFIRMED_MESSAGE);
        put(NOT_ACCOMPLISHED_CODE, NOT_ACCOMPLISHED_MESSAGE);
        put(ACCOMPLISHED_CODE, ACCOMPLISHED_MESSAGE);
    }};
}
