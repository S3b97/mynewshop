package com.sanvalero.mynewshop.repository;

import com.sanvalero.mynewshop.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findAll();
    Product findByName(String name);
    Set<Product> findByCategory(String category);
    Set<Product> findByNameAndPrice(String name, float price);

}
