package com.example.restapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapi.controller.ProductsController;
import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;

@Service
public class ProductsService {
	
	private Logger LOG = LoggerFactory.getLogger(ProductsController.class);
	
	private ProductRepository productRepository;	
	
	@Autowired
	public void productRepository (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProducts() {
		return productRepository.findAll();		
	}
	
	public Product getProduct(String id) {
		LOG.info("Getting the product with given id: " + id);
		return productRepository.findByID(id);		
	}
	
	public Product saveProduct(Product product) {
		Product productToSave;
		try {
			LOG.info("Saving product...");
			productToSave = productRepository.save(product);
			return productToSave;
		} catch (Exception e){
			LOG.info("An error occurred during product saving: " + e.getMessage());
		}
		return new Product();
	}
	
	public Product updateProduct(Product productToUpdate, String id) {
		
		Product foundProduct = productRepository.findByID(id);
		
		/* First draft with no exception handling
		if (foundProduct != null) {
			foundProduct.setName(productToUpdate.getName());
			foundProduct.setCategory(productToUpdate.getCategory());
			foundProduct.setDescription(productToUpdate.getDescription());
			foundProduct.setType(productToUpdate.getType());
			return productRepository.save(foundProduct);
		} else {
			LOG.info("UPDATE - No products found with given id: " + id);
			return productToUpdate;
		}
		*/
		
		try {
			foundProduct.setName(productToUpdate.getName());
			foundProduct.setCategory(productToUpdate.getCategory());
			foundProduct.setDescription(productToUpdate.getDescription());
			foundProduct.setType(productToUpdate.getType());
			return productRepository.save(foundProduct);
		} catch (Exception e){
			LOG.info("An error occurred during update of product: " + e.getMessage());
		}
		return productToUpdate;
		
	}

	public void deleteProduct(String id) {
		Product foundProduct = productRepository.findByID(id);
		try {
			productRepository.delete(foundProduct);
		} catch (Exception e){
			LOG.info("An error occurred during deleting of product: " + e.getMessage());
		}
	}
	
}
