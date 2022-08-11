package br.com.microservices.salesapi.modules.product.service;

import br.com.microservices.salesapi.modules.product.client.ProductClient;
import br.com.microservices.salesapi.modules.product.dto.ProductCheckStockRequest;
import br.com.microservices.salesapi.modules.sales.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductClient productClient;

    @Override
    public boolean checkProductStock(Order order) {
        try {
            productClient.productCheckStock(ProductCheckStockRequest.of(order));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
