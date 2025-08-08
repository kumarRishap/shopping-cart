package com.springboot.shopping_cart.repository;

import com.springboot.shopping_cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);  // better to wrap single-name lookup in Optional

    List<Product> findByBrandAndName(String brand, String name); // same for brand + name

    long countByBrandAndName(String brand, String name); // use `long` instead of `Long` for counts
}
