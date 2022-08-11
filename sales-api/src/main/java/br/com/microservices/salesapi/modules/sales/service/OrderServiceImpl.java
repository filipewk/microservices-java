package br.com.microservices.salesapi.modules.sales.service;

import br.com.microservices.salesapi.config.exception.ValidationException;
import br.com.microservices.salesapi.modules.jwt.JwtService;
import br.com.microservices.salesapi.modules.product.dto.ProductStockDTO;
import br.com.microservices.salesapi.modules.product.rabbitmq.ProductStockUpdateSender;
import br.com.microservices.salesapi.modules.product.service.ProductService;
import br.com.microservices.salesapi.modules.sales.dto.OrderRequest;
import br.com.microservices.salesapi.modules.sales.dto.SalesConfirmationDTO;
import br.com.microservices.salesapi.modules.sales.dto.SalesResponse;
import br.com.microservices.salesapi.modules.sales.enums.OrderStatus;
import br.com.microservices.salesapi.modules.sales.model.Order;
import br.com.microservices.salesapi.modules.sales.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductStockUpdateSender productStockUpdateSender;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final JwtService jwtService;

    @Override
    public Order createOrder(HttpServletRequest request, OrderRequest orderRequest) {
        var authUser = jwtService.getUserByRequest(request);
        var order = Order.of(orderRequest, authUser.getId().longValue(), OrderStatus.PENDING);

        validateProductStock(order);
        var createdOrder = orderRepository.save(order);
        productStockUpdateSender.sendProductStockUpdateMessage(ProductStockDTO.of(order));

        return createdOrder;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no order for the given ID."));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrder(SalesConfirmationDTO sales) {
        var order = findById(sales.getSalesId());

        if (sales.getStatus().equals(order.getStatus()))
            return;

        order.setStatus(sales.getStatus());
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public SalesResponse findByProductId(String productId) {
        return SalesResponse.ofList(orderRepository.findByProductId(productId));
    }

    private void validateProductStock(Order order) {
        var stockIsOk = productService.checkProductStock(order);
        if (!stockIsOk)
            throw new ValidationException("The stock is out for the products.");
    }
}
