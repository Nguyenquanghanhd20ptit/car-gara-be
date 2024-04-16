package com.example.carmanagement.commons.data.constant;

import java.util.HashMap;
import java.util.Map;

public class OrderConstant {
    public static final Integer PAID_CODE = 0;
    public static final Integer UNPAID_CODE = 1;
    public static final Integer PAID_FAIL_CODE = 2;
    public static final Integer CANCELED_CODE = 3;
    public static final Integer WAIT_PAYMENT_CODE = 4;
    public static final Integer ORDER_EXPIRED_CODE = 5;
    public static final String UNPAID_MESSAGE = "Chưa thanh toán";
    public static final String PAID_MESSAGE = "Đã thanh toán";
    public static final String PAID_FAIL_MESSAGE = "Thanh toán không thành công";
    public static final String CANCELED_MESSAGE= "Đã bị hủy";
    public static final String WAIT_PAYMENT_MESSAGE = "Đang chờ thanh toán";
    public static final String ORDER_EXPIRED_MESSAGE = "Đơn hàng đã hết hạn";
    public static final String INVALID_ORDER_MESSAGE = "đơn hàng không hợp lệ";

    public static Map<Integer, String> ORDER_STATUS = new HashMap<>() {{
        put(PAID_CODE, PAID_MESSAGE);
        put(UNPAID_CODE, UNPAID_MESSAGE);
        put(PAID_FAIL_CODE, PAID_FAIL_MESSAGE);
        put(CANCELED_CODE, CANCELED_MESSAGE);
        put(WAIT_PAYMENT_CODE, WAIT_PAYMENT_MESSAGE);
        put(ORDER_EXPIRED_CODE, ORDER_EXPIRED_MESSAGE);
    }};
}
