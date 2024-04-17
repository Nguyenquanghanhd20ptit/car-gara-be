package com.example.carmanagement.service.orderMomo;

import com.example.carmanagement.commons.data.dto.momo.InformationOrderMomo;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.mapper.orderMomo.OrderMomoMapper;
import com.example.carmanagement.commons.data.model.enums.CurrencyEnum;
import com.example.carmanagement.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.carmanagement.commons.data.response.DataResponse;
import com.example.carmanagement.config.ResTemplateConfig;
import com.example.carmanagement.config.ResourceProperties;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import com.example.carmanagement.utils.ResTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;
import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_SUCCESS;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_SUCCESS;

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
    public ResponseEntity<String> createTransactionMomo(OrderMomoRequest request){
        try {
           if(!validateReq(request.getOrderId())){
               return createResponseErrorValidate();
           }
            Optional<OrderEntity> opt = orderRepository.findById(request.getOrderId());
            OrderEntity orderEntity = opt.orElse(new OrderEntity());
            InformationOrderMomo orderMomo = orderMomoMapper.toOrderMomo(orderEntity);

            String url = resourceProperties.getMomoModuleUrl() + "/create-transaction";
            ResponseEntity<String> response = resTemplateUtils.callPostApiJsonBody(url, gsonSnakeCaseBuilder.toJson(orderMomo));

            if (response == null || response.getBody() == null || response.getBody().isEmpty()) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi kết nối đến momo");
            }

            DataResponse dataResponse = gsonSnakeCaseBuilder.fromJson(response.getBody(),DataResponse.class);

            if(dataResponse.getErrorCode() == null){
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi kết nối đến momo");
            }else {
                return response;
            }

        }catch (Exception e){
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
