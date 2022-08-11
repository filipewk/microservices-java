package br.com.microservices.productapi.utils;

import br.com.microservices.productapi.config.exception.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class LogUtil {

    public static final String SERVICE_ID = "serviceid";
    private static final String TRANSACTION_ID = "transactionid";

    public <T> void logRequest(T object) {
        try {
            var currentRequest = RequestUtil.getCurrentRequest();
            var data = new ObjectMapper().writeValueAsString(object);
            var transactionId = currentRequest.getHeader(TRANSACTION_ID);
            var serviceId = currentRequest.getAttribute(SERVICE_ID);

            log.info("Request with data: {} | [transactionID: {} | serviceID: {}]", data, transactionId, serviceId);
        } catch (Exception ex) {
            throw new ValidationException(ex.getMessage());
        }
    }

    public <T> void logResponse(T object) {
        try {
            var currentRequest = RequestUtil.getCurrentRequest();
            var data = new ObjectMapper().writeValueAsString(object);
            var transactionId = currentRequest.getHeader(TRANSACTION_ID);
            var serviceId = currentRequest.getAttribute(SERVICE_ID);

            log.info("Response POST with data: {} | [transactionID: {} | serviceID: {}]", data, transactionId, serviceId);
        } catch (Exception ex) {
            throw new ValidationException(ex.getMessage());
        }
    }
}
