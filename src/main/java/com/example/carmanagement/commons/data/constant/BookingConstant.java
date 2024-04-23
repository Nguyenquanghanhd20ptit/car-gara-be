package com.example.carmanagement.commons.data.constant;

import java.util.HashMap;
import java.util.Map;

public class BookingConstant {
    public static final Integer BOOKING_PAID_CODE = 0;
    public static final Integer BOOKING_UNCONFIRMED_CODE = 1;
    public static final Integer BOOKING_CONFIRMED_CODE = 2;
    public static final Integer BOOKING_NOT_ACCOMPLISHED_CODE = 3;
    public static final Integer BOOKING_ACCOMPLISHED_CODE = 4;
    public static final String BOOKING_PAID_MESSAGE = "Đã thanh toán";
    public static final String BOOKING_UNCONFIRMED_MESSAGE = "Chưa được xác nhận";
    public static final String BOOKING_CONFIRMED_MESSAGE= "Đã được xác nhận";
    public static final String BOOKING_NOT_ACCOMPLISHED_MESSAGE = "Chưa hoàn thành";
    public static final String BOOKING_ACCOMPLISHED_MESSAGE = "Đã hoàn thành";

    public static Map<Integer, String>  BOOKING_STATUS = new HashMap<>() {{
        put(BOOKING_PAID_CODE, BOOKING_PAID_MESSAGE);
        put(BOOKING_UNCONFIRMED_CODE, BOOKING_UNCONFIRMED_MESSAGE);
        put(BOOKING_CONFIRMED_CODE, BOOKING_CONFIRMED_MESSAGE);
        put(BOOKING_NOT_ACCOMPLISHED_CODE, BOOKING_NOT_ACCOMPLISHED_MESSAGE);
        put(BOOKING_ACCOMPLISHED_CODE,BOOKING_ACCOMPLISHED_MESSAGE);
    }};
}
