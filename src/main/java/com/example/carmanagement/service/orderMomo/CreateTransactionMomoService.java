package com.example.carmanagement.service.orderMomo;

import com.example.carmanagement.commons.data.dto.momo.InformationOrderMomo;
import com.example.carmanagement.commons.data.dto.momo.ReceiveInfoMomo;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.entity.TransactionEntity;
import com.example.carmanagement.commons.data.entity.TransactionTypeEntity;
import com.example.carmanagement.commons.data.mapper.orderMomo.OrderMomoMapper;
import com.example.carmanagement.commons.data.model.enums.CurrencyEnum;
import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.carmanagement.commons.data.response.DataResponse;
import com.example.carmanagement.config.ResourceProperties;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.db.mysql.repository.ITransactionTypeRepository;
import com.example.carmanagement.db.redis.repository.order.IStatusOrderRedis;
import com.example.carmanagement.service.BaseService;
import com.example.carmanagement.utils.ResTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;
import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_WAIT_PAYMENT_CODE;
import static com.example.carmanagement.commons.data.constant.TransactionConstant.MOMO_CODE;
import static com.example.carmanagement.commons.data.constant.TransactionConstant.MOMO_MESSAGE;

@Service
public class CreateTransactionMomoService extends BaseService {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private OrderMomoMapper orderMomoMapper;
    @Autowired
    private ResourceProperties resourceProperties;
    @Autowired
    private ResTemplateUtils resTemplateUtils;
    @Autowired
    private ITransactionTypeRepository transactionTypeRepository;
    @Autowired
    private OrderMomoComponent orderMomoComponent;
    public ResponseEntity<String> createTransactionMomo(OrderMomoRequest request){
        try {
           if(!validateReq(request.getOrderId())){
               return createResponseErrorValidate();
           }
            Optional<OrderEntity> opt = orderRepository.findById(request.getOrderId());
            OrderEntity orderEntity = opt.orElse(new OrderEntity());
            InformationOrderMomo orderMomo = orderMomoMapper.toOrderMomo(orderEntity,request.getRedirectUrl());

            String url = resourceProperties.getMomoModuleUrl() + "/create-transaction";
            ResponseEntity<String> response = resTemplateUtils.callPostApiJsonBody(url, gsonSnakeCaseBuilder.toJson(orderMomo));

            if (response == null || response.getBody() == null || response.getBody().isEmpty()) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi kết nối đến momo");
            }

            DataResponse dataResponse = gsonSnakeCaseBuilder.fromJson(response.getBody(),DataResponse.class);
            if(dataResponse.getErrorCode() == null){
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi kết nối đến momo");
            }else if(dataResponse.getErrorCode().equals("00")){
                ReceiveInfoMomo receiveInfoMomo = convertToObject(dataResponse, ReceiveInfoMomo.class);
                if(receiveInfoMomo == null){
                    return createResponseError(ERROR_CODE_DURING_PROCESS, " không thể lấy dữ liệu từ service momo");
                }
                orderRepository.updateStatusOrderById(request.getOrderId(),ORDER_WAIT_PAYMENT_CODE);
                orderRepository.updateTransactionName(request.getOrderId(),MOMO_MESSAGE);
                TransactionTypeEntity transactionType = new TransactionTypeEntity()
                        .setOrder(new OrderEntity().setId(request.getOrderId()))
                        .setTransaction(new TransactionEntity().setId(MOMO_CODE))
                        .setTranTypeId(receiveInfoMomo.getOrderMomoId());
                transactionTypeRepository.save(transactionType);

                StatusOrderRequest statusOrderRequest = new StatusOrderRequest()
                        .setOrderId(request.getOrderId().toString())
                        .setStatusCode(ORDER_WAIT_PAYMENT_CODE)
                        .setTransactionId(MOMO_CODE);

                orderMomoComponent.updateStatusOrder(statusOrderRequest);
                return createResponseSuccess(dataResponse.getData());
            }else {
              return createResponseError(dataResponse.getErrorCode(),dataResponse.getErrorMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return createResponseException(e);
        }
    }


    private Boolean validateReq(Integer orderId) {
        try {
            Optional<OrderEntity> opt = orderRepository.findById(orderId);
            if(opt.isEmpty()){
                this.invalidMessage = "orderId is null";
                return false;
            }
            OrderEntity orderEntity = opt.get();
            if(orderEntity.getCurrency().equals(CurrencyEnum.USD.toString())){
                this.invalidMessage = "orderId is null";
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
