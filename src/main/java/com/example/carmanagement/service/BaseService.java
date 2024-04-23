package com.example.carmanagement.service;

import com.example.carmanagement.commons.data.response.DataResponse;
import com.example.carmanagement.config.gson.LocalDateTimeSerializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.*;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_DURING_PROCESS;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_SUCCESS;


public abstract class BaseService {
    protected final Gson gsonDateFormat = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(LocalDateTime.class,new LocalDateTimeSerializer())
            .create();
    protected final Gson gson = new Gson();

    protected final Gson gsonSnakeCaseBuilder = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    protected Gson gsonCamelCaseBuilder = new GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();
    protected  String invalidMessage = "invalid request!";
    protected  ResponseEntity<String> createResponseError(String errorCode, String errorMessage){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(errorCode)
                .setErrorMessage(errorMessage);
        return ResponseEntity.ok(gsonSnakeCaseBuilder.toJson(dataResponse));
    }
    protected  ResponseEntity<String> createResponseErrorValidate(){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_REQUEST_INVALID_DATA)
                .setErrorMessage(invalidMessage);
        return ResponseEntity.ok(gsonSnakeCaseBuilder.toJson(dataResponse));
    }

    protected  ResponseEntity<String> createResponseSuccess(String data){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_SUCCESS)
                .setErrorMessage(ERROR_MESSAGE_SUCCESS)
                .setData(data);
        return ResponseEntity.ok(gsonSnakeCaseBuilder.toJson(dataResponse));
    }
    protected  ResponseEntity<String> createResponseException(Exception e){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_DURING_PROCESS)
                .setErrorMessage(ERROR_MESSAGE_DURING_PROCESS);
        return ResponseEntity.ok(gsonSnakeCaseBuilder.toJson(dataResponse));
    }

    protected  ResponseEntity<String> createResponseErrorDuringProcess(){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_DURING_PROCESS)
                .setErrorMessage(ERROR_MESSAGE_DURING_PROCESS);
        return ResponseEntity.ok(gsonSnakeCaseBuilder.toJson(dataResponse));
    }

    protected  <T> T convertToObject(DataResponse dataResponse,Class<T> tClass) {
        try{
            T obj = gsonSnakeCaseBuilder.fromJson( dataResponse.getData(),tClass);
            return obj;
        }catch (Exception e){
            return null;
        }
    }
}
