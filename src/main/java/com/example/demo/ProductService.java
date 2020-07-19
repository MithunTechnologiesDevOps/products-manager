package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);


	@Autowired
	private ProductRepository repo;
	
	public List<Product> listAll() {
		log.info("List All Products ");
		return repo.findAll();
	}
	
	public void save(Product product) {
		log.info("Save Product "+ product.getName());
		repo.save(product);
	}
	
	public Product get(long id) {
		log.info("Get Product By Id : "+id);
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		log.info("Delete Product By Id : "+id);
		repo.deleteById(id);
	}
}
