package com.example.carmanagement.commons.data.constant;

import java.util.HashMap;
import java.util.Map;

public class OrderConstant {
    public static final Integer ORDER_PAID_CODE = 0;
    public static final Integer ORDER_UNPAID_CODE = 1;
    public static final Integer ORDER_PAID_FAIL_CODE = 2;
    public static final Integer ORDER_CANCELED_CODE = 3;
    public static final Integer ORDER_WAIT_PAYMENT_CODE = 4;
    public static final Integer ORDER_ORDER_EXPIRED_CODE = 5;
    public static final String ORDER_UNPAID_MESSAGE = "Chưa thanh toán";
    public static final String ORDER_PAID_MESSAGE = "Đã thanh toán";
    public static final String ORDER_PAID_FAIL_MESSAGE = "Thanh toán không thành công";
    public static final String ORDER_CANCELED_MESSAGE= "Đã bị hủy";
    public static final String ORDER_WAIT_PAYMENT_MESSAGE = "Đang chờ thanh toán";
    public static final String ORDER_ORDER_EXPIRED_MESSAGE = "Đơn hàng đã hết hạn";
    public static final String ORDER_INVALID_ORDER_MESSAGE = "đơn hàng không hợp lệ";

    public static Map<Integer, String> ORDER_STATUS = new HashMap<>() {{
        put(ORDER_PAID_CODE, ORDER_PAID_MESSAGE);
        put(ORDER_UNPAID_CODE, ORDER_UNPAID_MESSAGE);
        put(ORDER_PAID_FAIL_CODE, ORDER_PAID_FAIL_MESSAGE);
        put(ORDER_CANCELED_CODE, ORDER_CANCELED_MESSAGE);
        put(ORDER_WAIT_PAYMENT_CODE, ORDER_WAIT_PAYMENT_MESSAGE);
        put(ORDER_ORDER_EXPIRED_CODE, ORDER_ORDER_EXPIRED_MESSAGE);
    }};
}
