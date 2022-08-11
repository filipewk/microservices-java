package br.com.microservices.salesapi.modules.sales.service;

import br.com.microservices.salesapi.modules.sales.dto.OrderRequest;
import br.com.microservices.salesapi.modules.sales.dto.SalesConfirmationDTO;
import br.com.microservices.salesapi.modules.sales.dto.SalesResponse;
import br.com.microservices.salesapi.modules.sales.model.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    Order createOrder(HttpServletRequest request, OrderRequest orderRequest);

    Order findById(Long id);

    List<Order> findAll();

    void updateOrder(SalesConfirmationDTO sales);

    SalesResponse findByProductId(String productId);
}
