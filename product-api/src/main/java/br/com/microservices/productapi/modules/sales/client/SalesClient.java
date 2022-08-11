package br.com.microservices.productapi.modules.sales.client;

import br.com.microservices.productapi.config.interceptor.FeignConfiguration;
import br.com.microservices.productapi.modules.sales.dto.SalesProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "salesClient", configuration = FeignConfiguration.class, url = "${app-config.services.sales}")
public interface SalesClient {

    @GetMapping("order/product/{productId}")
    Optional<SalesProductResponse> findSalesByProductId(@PathVariable Long productId);
}
