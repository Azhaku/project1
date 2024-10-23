package com.rev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rev.entity.Buyer;
import com.rev.repository.BuyerRepository;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    // Method to save a new buyer
    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    // Method to get all buyers
    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    // Method to get a buyer by ID
    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    // Method to get a buyer by username
    public Buyer getBuyerByUsername(String username) {
        return buyerRepository.findByUsername(username);
    }

    // Method to update buyer details
    public Buyer updateBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    // Method to delete a buyer by ID
    public void deleteBuyerById(Long id) {
        buyerRepository.deleteById(id);
    }
    
    // Method to find a seller by username
    public Optional<Buyer> findByUsername(String username) {
        return Optional.ofNullable(buyerRepository.findByUsername(username));
    }

}
