package com.rev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rev.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUsername(String username);  // Custom query method to find Seller by username
}
