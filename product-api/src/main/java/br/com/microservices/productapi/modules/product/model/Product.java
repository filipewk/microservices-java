package br.com.microservices.productapi.modules.product.model;

import br.com.microservices.productapi.modules.category.model.Category;
import br.com.microservices.productapi.modules.product.dto.ProductRequest;
import br.com.microservices.productapi.modules.supplier.model.Supplier;
import br.com.microservices.productapi.validators.annotations.QuantityConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{validation.notNull}")
    @Column(name = "NAME", nullable = false)
    private String name;

    @QuantityConstraint
    @NotNull(message = "{validation.notNull}")
    @Column(name = "QUANTITY_AVAILABLE", nullable = false)
    private BigDecimal quantityAvailable;

    @NotNull(message = "{validation.notNull}")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY", nullable = false)
    private Category category;

    @NotNull(message = "{validation.notNull}")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUPPLIER", nullable = false)
    private Supplier supplier;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

//    @PrePersist
//    public void prePersist() {
//        createdAt = LocalDateTime.now();
//    }

    public static Product of(ProductRequest request, Category category, Supplier supplier) {
        return Product.builder()
                .name(request.getName())
                .quantityAvailable(request.getQuantityAvailable())
                .category(category)
                .supplier(supplier)
                .build();
    }

    public void updateStock(BigDecimal quantity) {
        quantityAvailable = quantityAvailable.subtract(quantity);
    }
}
