package com.product_management2024.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_management2024.model.Product;
import com.product_management2024.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	public ProductRepository productRepo;

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).get();
	}

	@Override
	public String deleteProduct(Integer id) {
		Product product=productRepo.findById(id).get();
		if(product != null) {
			productRepo.delete(product);
			return "product deleted";
		}
		return "something wrong";
		
	}

	@Override
	public Product editProduct(Product newProduct, Integer id) {
		Product oldProduct=productRepo.findById(id).get();
		oldProduct.setProductName(newProduct.getProductName());
		oldProduct.setDescription(newProduct.getDescription());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setStatus(newProduct.getStatus());
		return productRepo.save(oldProduct);
	}

}
