package br.com.microservices.productapi.modules.product.repository;

import br.com.microservices.productapi.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(Long id);

    List<Product> findBySupplierId(Long id);

    Boolean existsByCategoryId(Long id);

    Boolean existsBySupplierId(Long id);


    //TODO rever sobre o lazy load
    @Query(value = "SELECT P FROM Product P " +
            "INNER JOIN FETCH P.category " +
            "INNER JOIN FETCH P.supplier " +
            "WHERE P.id = :id")
    Product getProductById(Long id);
}
