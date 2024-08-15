package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.dtos.ProductRequestDto;
import br.com.danielchipolesch.domain.dtos.ProductResponseDto;
import br.com.danielchipolesch.domain.entities.Product;
import br.com.danielchipolesch.domain.entities.Supplier;
import br.com.danielchipolesch.infrastructure.repositories.ProductRepository;
import br.com.danielchipolesch.infrastructure.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ModelMapper modelMapper;

    public ProductResponseDto create(ProductRequestDto request) throws Exception {
        Product product = modelMapper.map(request, Product.class);
        Supplier supplier = supplierRepository.findById(request.supplierId()).orElseThrow(() -> new Exception("Supplier not found"));

        product.setSupplier(supplier);
        productRepository.save(product);
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public ProductResponseDto update(Long id, ProductRequestDto request) throws Exception {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("product not found"));

        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(()-> new Exception("Category not found"));

        //TODO Implementar validações para o caso do cliente não enviar algum parâmetro
        product.setName(request.name());
        product.setPrice(BigDecimal.valueOf(request.price()));
        product.setQuantity(request.quantity());
        product.setSupplier(supplier);

        return modelMapper.map(product, ProductResponseDto.class);
    }

    public ProductResponseDto getById(Long id) throws Exception {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        return modelMapper.map(product, ProductResponseDto.class);
    }

    public List<ProductResponseDto> getAll(Pageable pageable) throws Exception {

        Page<Product> products = productRepository.findAll(pageable);

        List<ProductResponseDto> response = products
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());

        return response;
    }

    public ProductResponseDto delete(Long id) throws Exception {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        productRepository.delete(product);
        return modelMapper.map(product, ProductResponseDto.class);
    }

}
