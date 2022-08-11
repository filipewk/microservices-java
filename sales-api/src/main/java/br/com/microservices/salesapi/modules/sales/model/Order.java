package br.com.microservices.salesapi.modules.sales.model;

import br.com.microservices.salesapi.modules.converter.JsonConverter;
import br.com.microservices.salesapi.modules.product.dto.ProductDTO;
import br.com.microservices.salesapi.modules.sales.dto.OrderRequest;
import br.com.microservices.salesapi.modules.sales.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCTS", columnDefinition = "jsonb", nullable = false)
    @Convert(converter = JsonConverter.class)
    private List<ProductDTO> products;

    @Column(name = "ID_USER", nullable = false)
    private Long userId;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public static Order of(OrderRequest request, Long userId, OrderStatus orderStatus) {
        return Order.builder()
                .products(request.getProducts())
                .userId(userId)
                .status(orderStatus)
                .build();
    }
}
