package com.app.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.app.model.ProductResource;
import com.app.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductService {
	
	private final ProductsRepository prep;
	
	public Optional<ProductResource> getSingleProduct(Integer id){
		return prep.findById(id);
	}
	
	public List<ProductResource> getAllProducts(){
		return prep.findAll();
	}
	
	public ProductResource putProduct (Integer id, ProductResource prod) {
		prod.setId(id);
		return prep.save(prod);
	}
	
	public ProductResource postProduct(ProductResource prod) {
		return prep.save(prod);
	}
	
	public void deleteProduct(Integer id) {
		prep.deleteById(id);
	}
	

}
