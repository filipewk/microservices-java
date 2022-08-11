package br.com.microservices.productapi.modules.supplier.dto;

import br.com.microservices.productapi.modules.supplier.model.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SupplierResponse {

    private Long id;
    private String name;

    public static SupplierResponse of(Supplier supplier) {
        var response = new SupplierResponse();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }

    public static List<SupplierResponse> ofList(List<Supplier> suppliers) {
        return suppliers.stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }
}
