package br.com.microservices.salesapi.modules.product.service;

import br.com.microservices.salesapi.modules.sales.model.Order;

public interface ProductService {

    boolean checkProductStock(Order order);
}
