package com.example.restapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;

@RestController
public class ProductsController {
	
	private ProductRepository productRepository;
	
	@Autowired
	public void productRepository (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("/api/products/{id}")
	public Product getProduct(@PathVariable(name = "id") String id) {
		Optional<Product> product=productRepository.findById(id);
		
		return product.get();
	}

}
