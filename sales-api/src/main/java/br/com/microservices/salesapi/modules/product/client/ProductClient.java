package br.com.microservices.salesapi.modules.product.client;

import br.com.microservices.salesapi.config.FeignConfiguration;
import br.com.microservices.salesapi.modules.product.dto.ProductCheckStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "productClient", configuration = FeignConfiguration.class, url = "${app-config.services.product}")
public interface ProductClient {

    @PostMapping("product/check-stock")
    void productCheckStock(@RequestBody ProductCheckStockRequest product);
}
