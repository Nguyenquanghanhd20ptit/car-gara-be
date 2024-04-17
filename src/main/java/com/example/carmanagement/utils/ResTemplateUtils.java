package com.example.carmanagement.utils;


import com.example.carmanagement.commons.data.response.DataResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.net.SocketTimeoutException;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.*;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.*;

@Component
public class ResTemplateUtils {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> callGetApi(String url) {
        JsonObject respFailBody = new JsonObject();
        try {
            return restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_BAD_REQUEST,
                    REST_TEMPLATE_MESSAGE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } catch (HttpServerErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnknownHttpStatusCodeException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ResourceAccessException rex) {
            DataResponse dataResponse = new DataResponse();
            if (rex.getCause() instanceof SocketTimeoutException) {
                if (rex.getCause().getMessage().equals("connect timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_CONNECT_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_CONNECT_TIMEOUT);
                } else if (rex.getCause().getMessage().equals("Read timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_READ_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_READ_TIMEOUT);
                }
            }
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(respFailBody.toString());
        }
    }

    public ResponseEntity<String> callPostApiJsonBody(String url, String jsonBody){
        JsonObject respFailBody = new JsonObject();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
            return restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_BAD_REQUEST,
                    REST_TEMPLATE_MESSAGE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } catch (HttpServerErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnknownHttpStatusCodeException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ResourceAccessException rex) {
            DataResponse dataResponse = new DataResponse();
            if (rex.getCause() instanceof SocketTimeoutException) {
                if (rex.getCause().getMessage().equals("connect timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_CONNECT_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_CONNECT_TIMEOUT);
                } else if (rex.getCause().getMessage().equals("Read timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_READ_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_READ_TIMEOUT);
                }
            }
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(respFailBody.toString());
        }
    }

    public ResponseEntity<String> callPostApiJsonBody(String url, String jsonBody,
                                                      boolean signature, String hashKey) {
        JsonObject respFailBody = new JsonObject();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (signature) {
                String checksum = HashUtils.md5(jsonBody + hashKey);
                headers.set("Signature", checksum);
            }
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
            return restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_BAD_REQUEST,
                    REST_TEMPLATE_MESSAGE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } catch (HttpServerErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnknownHttpStatusCodeException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ResourceAccessException rex) {
            DataResponse dataResponse = new DataResponse();
            if (rex.getCause() instanceof SocketTimeoutException) {
                if (rex.getCause().getMessage().equals("connect timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_CONNECT_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_CONNECT_TIMEOUT);
                } else if (rex.getCause().getMessage().equals("Read timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_READ_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_READ_TIMEOUT);
                }
            }
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(respFailBody.toString());
        }
    }

    public ResponseEntity<String> callPostAptJsonBodyToken(String url, String jsonBody,
                                                           boolean signature, String hashKey,
                                                           boolean auth, String token) {
        JsonObject respFailBody = new JsonObject();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (signature) {
                String checksum = HashUtils.md5(jsonBody + hashKey);
                headers.set("Signature", checksum);
            }
            if (auth) {
                headers.set("Authorization", "Bearer " + token);
            }
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
            return restTemplate.postForEntity(url, entity, String.class);

        } catch (HttpClientErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_BAD_REQUEST,
                    REST_TEMPLATE_MESSAGE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } catch (HttpServerErrorException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnknownHttpStatusCodeException e) {
            return createRespFailBody(e, REST_TEMPLATE_CODE_INTERNAL_SERVER,
                    REST_TEMPLATE_MESSAGE_INTERNAL_SERVER, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ResourceAccessException rex) {
            DataResponse dataResponse = new DataResponse();
            if (rex.getCause() instanceof SocketTimeoutException) {
                if (rex.getCause().getMessage().equals("connect timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_CONNECT_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_CONNECT_TIMEOUT);
                } else if (rex.getCause().getMessage().equals("Read timed out")) {
                    dataResponse.setErrorCode(REST_TEMPLATE_CODE_READ_TIMEOUT)
                            .setErrorMessage(REST_TEMPLATE_MESSAGE_READ_TIMEOUT);
                }
            }
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(respFailBody.toString());
        }
    }

    private static ResponseEntity<String> createRespFailBody(Exception e, String code,
                                                             String message, HttpStatus status) {
        DataResponse dataResponse = new DataResponse()
                .setErrorMessage(message)
                .setErrorCode(code);
        return ResponseEntity.status(status).body(new Gson().toJson(dataResponse));
    }
}
