package com.rev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rev.entity.Product;
import com.rev.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
    
    public void addProduct(Product product) {
        productRepository.save(product); 
    }
    
    public List<Product> getAllProducts(){
    	return productRepository.findAll();
    }
   
}
