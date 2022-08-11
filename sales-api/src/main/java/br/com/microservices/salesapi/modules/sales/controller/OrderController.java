package br.com.microservices.salesapi.modules.sales.controller;

import br.com.microservices.salesapi.modules.sales.dto.OrderRequest;
import br.com.microservices.salesapi.modules.sales.dto.OrderResponse;
import br.com.microservices.salesapi.modules.sales.dto.SalesResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderController {

    ResponseEntity<OrderResponse> save(HttpServletRequest request, OrderRequest orderRequest);

    ResponseEntity<OrderResponse> findById(Long id);

    ResponseEntity<List<OrderResponse>> findAll();

    ResponseEntity<SalesResponse> findSalesByProductId(String productId);
}
