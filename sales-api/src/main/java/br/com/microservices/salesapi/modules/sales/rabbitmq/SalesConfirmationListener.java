package br.com.microservices.salesapi.modules.sales.rabbitmq;

import br.com.microservices.salesapi.modules.sales.dto.SalesConfirmationDTO;
import br.com.microservices.salesapi.modules.sales.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesConfirmationListener {

    private final OrderService orderService;

    @RabbitListener(queues = "${app-config.rabbit.queue.sales-confirmation}")
    public void receiveSalesConfirmationMessage(SalesConfirmationDTO sales) throws JsonProcessingException {
        log.info("recebendo mensagem: {}", new ObjectMapper().writeValueAsString(sales));
        orderService.updateOrder(sales);
    }
}
