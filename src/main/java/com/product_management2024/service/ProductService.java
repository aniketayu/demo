package com.product_management2024.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product_management2024.model.Product;


public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Integer id);
	
	
	public String deleteProduct(Integer id);
	
	public Product editProduct(Product product,Integer id);

}
