package br.com.microservices.productapi.modules.supplier.service;

import br.com.microservices.productapi.modules.supplier.dto.SupplierRequest;
import br.com.microservices.productapi.modules.supplier.model.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier save(SupplierRequest request);

    Supplier findById(Long id);

    List<Supplier> findAll();

    List<Supplier> findByName(String name);

    void delete(Long id);

    Supplier update(SupplierRequest request, Long id);
}
