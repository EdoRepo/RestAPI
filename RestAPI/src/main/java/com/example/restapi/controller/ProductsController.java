package com.example.restapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;

@RestController
public class ProductsController {
	
	private ProductRepository productRepository;
	
	private Logger LOG = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	public void productRepository (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping(path = "/api/products")
	public List<Product> getProducts() {
		return productRepository.findAll();		
	}
	
	@GetMapping(path = "/api/products/{id}")
	public Product getProduct(@PathVariable(name = "id") String id) {
		return productRepository.findByID(id);		
	}
	
	@PostMapping(path = "/api/products", consumes = "application/json")
	public Product saveProduct(@RequestBody Product productToSave) {
		
		return productRepository.save(productToSave);
	}
	
	@PutMapping(path = "/api/products/{id}", consumes = "application/json")
	public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {
		
		Product foundProduct = productRepository.findByID(id);
		
		if (foundProduct != null) {
			foundProduct.setName(productToUpdate.getName());
			foundProduct.setCategory(productToUpdate.getCategory());
			foundProduct.setDescription(productToUpdate.getDescription());
			foundProduct.setType(productToUpdate.getType());
			return productRepository.save(foundProduct);
		} else {
			LOG.info("No products found with given id: " + id);
			return productToUpdate;
		}
		
	}
}
