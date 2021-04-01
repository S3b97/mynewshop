package com.sanvalero.mynewshop.service;

import com.sanvalero.mynewshop.domain.Product;
import com.sanvalero.mynewshop.exception.ProductNotFoundException;
import com.sanvalero.mynewshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServicelmpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Set<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Set<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product modifyProduct(long id, Product newProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
                newProduct.setId(product.getId());
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(long id) {

        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);
    }
}
