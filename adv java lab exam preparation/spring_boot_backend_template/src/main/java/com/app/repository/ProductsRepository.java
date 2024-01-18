package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.ProductResource;

public interface ProductsRepository extends JpaRepository<ProductResource, Integer> {
	

}
