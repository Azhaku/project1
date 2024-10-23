package com.rev.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rev.entity.Product;
import com.rev.entity.Seller;
import com.rev.service.ProductService;
import com.rev.service.SellerService;

@Controller
@RequestMapping("/seller")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    // Endpoint to show seller's products
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product()); // Create a new Product object
        return "add-product"; // Name of the JSP file to render
    }
    
   
    // Handle form submission to add a new product
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product, 
                             @SessionAttribute("username") String username, 
                             RedirectAttributes redirectAttributes) {
        Optional<Seller> seller = sellerService.findByUsername(username);
        if (seller.isPresent()) {
            product.setSellerId(seller.get().getId()); // Set the seller ID to the product
            productService.addProduct(product); // Call your service to save the product

            // Add a success message
            redirectAttributes.addFlashAttribute("message", "Product added successfully!");

            return "redirect:/sellers/my-products"; // Redirect to the seller's products page
        } else {
            return "redirect:/sellers/login"; // Redirect to login if the seller is not found
        }
    }

    

    
}
