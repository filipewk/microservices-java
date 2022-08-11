package br.com.microservices.productapi.modules.supplier.controller;

import br.com.microservices.productapi.modules.supplier.dto.SupplierRequest;
import br.com.microservices.productapi.modules.supplier.dto.SupplierResponse;
import br.com.microservices.productapi.modules.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("supplier")
@RequiredArgsConstructor
public class SupplierControllerImpl implements SupplierController {

    private final SupplierService supplierService;

    @Override
    @PostMapping
    public ResponseEntity<SupplierResponse> save(@RequestBody SupplierRequest request) {
        var supplier = supplierService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(SupplierResponse.of(supplier));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SupplierResponse>> findAll() {
        var suppliers = supplierService.findAll();
        return ResponseEntity.ok(SupplierResponse.ofList(suppliers));
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<SupplierResponse> findById(@PathVariable Long id) {
        var supplier = supplierService.findById(id);
        return ResponseEntity.ok(SupplierResponse.of(supplier));
    }

    @Override
    @GetMapping("name/{name}")
    public ResponseEntity<List<SupplierResponse>> findByName(@PathVariable String name) {
        var suppliers = supplierService.findByName(name);
        return ResponseEntity.ok(SupplierResponse.ofList(suppliers));
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<SupplierResponse> delete(@RequestBody SupplierRequest request, @PathVariable Long id) {
        var supplier = supplierService.update(request, id);
        return ResponseEntity.ok(SupplierResponse.of(supplier));
    }
}
