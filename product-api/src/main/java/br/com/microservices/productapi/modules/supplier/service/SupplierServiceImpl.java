package br.com.microservices.productapi.modules.supplier.service;

import br.com.microservices.productapi.config.exception.ValidationException;
import br.com.microservices.productapi.modules.product.repository.ProductRepository;
import br.com.microservices.productapi.modules.supplier.dto.SupplierRequest;
import br.com.microservices.productapi.modules.supplier.model.Supplier;
import br.com.microservices.productapi.modules.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    @Override
    public Supplier save(SupplierRequest request) {
        validateSupplierNameInformed(request);
        return supplierRepository.save(Supplier.of(request));
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for the given ID."));
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public List<Supplier> findByName(String name) {
        if (name.isBlank())
            throw new ValidationException("The Supplier name cannot be blank.");
        return supplierRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsBySupplierId(id))
            throw new ValidationException("You cannot delete this supplier because it's already defined by a product");
        supplierRepository.deleteById(id);
    }

    @Override
    //TODO verificar id antes de salvar.
    public Supplier update(SupplierRequest request, Long id) {
        var supplier = Supplier.of(request);
        supplier.setId(id);
        return supplierRepository.save(supplier);
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (ObjectUtils.isEmpty(request.getName())) {
            throw new ValidationException("The Supplier's name was not informed.");
        }
    }
}
