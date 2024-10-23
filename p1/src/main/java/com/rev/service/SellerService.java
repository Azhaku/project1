package com.rev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rev.entity.Seller;
import com.rev.repository.SellerRepository;


@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    // Method to save a new seller
    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    // Method to find a seller by username
    public Optional<Seller> findByUsername(String username) {
        return Optional.ofNullable(sellerRepository.findByUsername(username));
    }
}
