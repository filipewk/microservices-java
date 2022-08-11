package br.com.microservices.salesapi.modules.product.rabbitmq;

import br.com.microservices.salesapi.modules.product.dto.ProductStockDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockUpdateSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;

    @Value("${app-config.rabbit.routingKey.product-stock}")
    private String productStockKey;

    public void sendProductStockUpdateMessage(ProductStockDTO message) {
        try {
            log.info("Send message: {}", new ObjectMapper().writeValueAsString(message));
            rabbitTemplate.convertAndSend(productTopicExchange, productStockKey, message);
            log.info("Message was sent successfully.");
        } catch (Exception ex) {
            log.info("Error while trying to send sales confirmation message: ", ex);
        }
    }
}
