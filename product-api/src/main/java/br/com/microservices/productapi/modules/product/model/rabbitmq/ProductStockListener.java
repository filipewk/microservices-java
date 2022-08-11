package br.com.microservices.productapi.modules.product.model.rabbitmq;

import br.com.microservices.productapi.modules.product.dto.ProductStockDTO;
import br.com.microservices.productapi.modules.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockListener {

    private final ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void receiveProductStockMessage(ProductStockDTO product) throws JsonProcessingException {
        var data = new ObjectMapper().writeValueAsString(product);
        var transactionId = product.getTransactionId();
        log.info("Receiving message with data: {} and transactionID: {}", data, transactionId);
        productService.updateProductStock(product);
    }
}
