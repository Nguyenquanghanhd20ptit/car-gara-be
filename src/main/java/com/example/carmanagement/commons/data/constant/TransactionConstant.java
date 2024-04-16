package com.example.carmanagement.commons.data.constant;


import java.util.HashMap;
import java.util.Map;

public class TransactionConstant {
    public static final Integer CASH_CODE = 1;
    public static final Integer MOMO_CODE = 2;
    public static final Integer NAPAS_CODE = 3;
    public static final String MOMO_MESSAGE = "Thanh toán bằng Momo";
    public static final String NAPAS_MESSAGE = "Thanh toán bằng Napas";
    public static final String  CASH_MESSAGE = "Thanh toán bằng tiền mặt";

    public static Map<Integer, String> TRANSACTION_STATUS = new HashMap<>() {{
        put(CASH_CODE, CASH_MESSAGE);
        put(MOMO_CODE, MOMO_MESSAGE);
        put(NAPAS_CODE, NAPAS_MESSAGE);
    }};


}
