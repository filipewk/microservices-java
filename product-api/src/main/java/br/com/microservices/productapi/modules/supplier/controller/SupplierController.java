package br.com.microservices.productapi.modules.supplier.controller;

import br.com.microservices.productapi.modules.supplier.dto.SupplierRequest;
import br.com.microservices.productapi.modules.supplier.dto.SupplierResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupplierController {

    ResponseEntity<SupplierResponse> save(SupplierRequest request);

    ResponseEntity<List<SupplierResponse>> findAll();

    ResponseEntity<SupplierResponse> findById(Long id);

    ResponseEntity<List<SupplierResponse>> findByName(String name);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<SupplierResponse> delete(SupplierRequest request, Long id);
}
