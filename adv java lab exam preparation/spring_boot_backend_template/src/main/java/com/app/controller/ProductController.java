package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.ProductResource;
import com.app.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService pser;
	
	@GetMapping
	public List<ProductResource> getAllProducts(){
		return pser.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ProductResource getSingleProduct(@PathVariable Integer id) {
		return pser.getSingleProduct(id).orElseThrow();
	}
	
	@PostMapping
	public ProductResource addProduct(@RequestBody ProductResource prod) {
		System.out.println(prod.getPcode() + prod.getPname() + prod.getManufacturingDate());
		return pser.postProduct(prod);
	}
	
	@PutMapping("/{id}")
	public ProductResource putProduct(@PathVariable Integer id, @RequestBody  ProductResource prod) {
		return pser.putProduct(id, prod);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		pser.deleteProduct(id);
	}
	
	
	
	
}
