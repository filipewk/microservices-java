package br.com.microservices.productapi.config.interceptor;

import br.com.microservices.productapi.utils.RequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignConfiguration implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";

    @Override
    public void apply(RequestTemplate template) {
        template
                .header(AUTHORIZATION, RequestUtil.getCurrentRequest().getHeader(AUTHORIZATION))
                .header(TRANSACTION_ID, RequestUtil.getCurrentRequest().getHeader(TRANSACTION_ID));
    }
}
