package br.com.microservices.salesapi.modules.sales.repository;

import br.com.microservices.salesapi.modules.sales.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT O.* FROM ORDERS O, " +
            "jsonb_array_elements(O.PRODUCTS) P " +
            "WHERE P -> 'productId' = :productId", nativeQuery = true)
    List<Order> findByProductId(String productId);
}
