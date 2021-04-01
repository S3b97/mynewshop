package com.sanvalero.mynewshop.service;

import com.sanvalero.mynewshop.domain.Product;

import java.util.Optional;
import java.util.Set;


public interface ProductService {
Set<Product> findAll();
Set<Product> findByCategory(String category);
Optional<Product> findById(long id);
Product addProduct(Product product);
Product modifyProduct(long id, Product newProduct);
void deleteProduct(long id);



}
