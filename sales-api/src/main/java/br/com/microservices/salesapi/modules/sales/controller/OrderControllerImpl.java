package br.com.microservices.salesapi.modules.sales.controller;

import br.com.microservices.salesapi.modules.sales.dto.OrderRequest;
import br.com.microservices.salesapi.modules.sales.dto.OrderResponse;
import br.com.microservices.salesapi.modules.sales.dto.SalesResponse;
import br.com.microservices.salesapi.modules.sales.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<OrderResponse> save(HttpServletRequest request, @RequestBody OrderRequest orderRequest) {
        var order = orderService.createOrder(request, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.of(order));
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(OrderResponse.of(orderService.findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(OrderResponse.ofList(orderService.findAll()));
    }

    @Override
    @GetMapping("product/{productId}")
    public ResponseEntity<SalesResponse> findSalesByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(orderService.findByProductId(productId));
    }
}
