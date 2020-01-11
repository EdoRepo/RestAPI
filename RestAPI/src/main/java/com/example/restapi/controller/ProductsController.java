package com.example.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.model.Product;
import com.example.restapi.service.ProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "ProductsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {
	
	private ProductsService productsService;
	
	@Autowired
	public void productRepository (ProductsService productsService) {
		this.productsService = productsService;
	}
	
	@GetMapping(path = "/api/products/")
	public List<Product> getProducts() {
		return productsService.getProducts();		
	}
	
	@GetMapping(path = "/api/products/{id}")
	@ApiOperation("Gest the product with specific id")
	@ApiResponses( value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
	public Product getProduct(@PathVariable(name = "id") String id) {
		return productsService.getProduct(id);		
	}
	
	@PostMapping(path = "/api/products/", consumes = "application/json")
	public Product saveProduct(@RequestBody Product productToSave) {
		
		return productsService.saveProduct(productToSave);
	}
	
	@PutMapping(path = "/api/products/{id}", consumes = "application/json")
	public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {

		return productsService.updateProduct(productToUpdate, id);
		
	}
	
	@DeleteMapping(path = "/api/products/{id}")
	public void deleteProduct(@PathVariable(name = "id") String id) {
		productsService.deleteProduct(id);
	}
}
