package com.example.carmanagement.db.redis.listener;

import com.example.carmanagement.commons.data.entity.TransactionTypeEntity;
import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.commons.data.response.DataResponse;
import com.example.carmanagement.config.ResourceProperties;
import com.example.carmanagement.db.mysql.repository.ITransactionTypeRepository;
import com.example.carmanagement.service.orderMomo.OrderMomoComponent;
import com.example.carmanagement.utils.ResTemplateUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_WAIT_PAYMENT_CODE;
import static com.example.carmanagement.commons.data.constant.TransactionConstant.MOMO_CODE;

@Component
public class StatusOrderRedisListener implements MessageListener {
    @Autowired
    private ITransactionTypeRepository transactionTypeRepository;
    @Autowired
    private ResTemplateUtils resTemplateUtils;
    @Autowired
    private ResourceProperties resourceProperties;
    @Autowired
    private OrderMomoComponent orderMomoComponent;
    protected final Gson gsonSnakeCaseBuilder = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        String key = new String(body);
        if (key.startsWith("orderId:")) {
            int index = key.indexOf(":"); // nen them bien so lan check trong key redis
            String orderId;
            if (index != -1 && index < key.length() - 1) {
                orderId = key.substring(index + 1);
            } else return;
            System.out.println(orderId);
            List<TransactionTypeEntity> list = transactionTypeRepository.findByOrderId(Integer.parseInt(orderId));
            if(list.isEmpty()){
                return;
            }
            TransactionTypeEntity transactionType = list.get(0);
            if (transactionType.getTransaction().getId().equals(MOMO_CODE)) {
                System.out.println("check status order momo: " + orderId);
                String url = resourceProperties.getMomoModuleUrl()+"/check-status/"+orderId;
                ResponseEntity<String> response = resTemplateUtils.callGetApi(url);
                if(response == null || response.getBody() == null || response.getBody().isEmpty()){
                    checkAgain(orderId);
                }else {
                    try {
                        DataResponse dataResponse = gsonSnakeCaseBuilder.fromJson(response.getBody(),DataResponse.class);
                        if(!dataResponse.getErrorCode().equals("00")){
                            checkAgain(orderId);
                        }
                    }catch (Exception e){
                        checkAgain(orderId);
                    }
                }
            } else {
                System.out.println("dfdfdf");
            }
        }
    }

    private void checkAgain(String orderId) {
        StatusOrderRequest statusOrderRequest = new StatusOrderRequest()
                .setOrderId(orderId)
                .setStatusCode(ORDER_WAIT_PAYMENT_CODE)
                .setTransactionId(MOMO_CODE);
        orderMomoComponent.updateStatusOrder(statusOrderRequest);
    }
}
