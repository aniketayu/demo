package com.product_management2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product_management2024.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
