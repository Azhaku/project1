package com.rev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rev.entity.Product;
import com.rev.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
    @GetMapping("/dashboard")
//    @ResponseBody
    public String showBuyerDashboard(HttpSession session, Model model) {
    	List<Product> products = productService.getAllProducts();
    	model.addAttribute("product",products);
    	String userName = (String) session.getAttribute("userName");
    	model.addAttribute("userName",userName);
    	for (Product product : products) {
            System.out.println(product);  // Ensure Product class has a proper toString() method
        }
//    	return products;
        return "dashboard";
    }
}
