package com.rev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rev.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	// Method to find products by seller ID
	List<Product> findBySellerId(Long sellerId);

}
