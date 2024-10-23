package com.rev.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rev.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByUsername(String username);  // Custom query method to find Buyer by username
}
