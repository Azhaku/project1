package com.rev.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rev.entity.Buyer;
import com.rev.service.BuyerService;
import com.rev.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping("/logout")
    private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("buyer", new Buyer());
        return "register";  // This should correspond to register
    }

    @PostMapping("/register")
    public String registerBuyer(@ModelAttribute Buyer buyer,RedirectAttributes redirectAttributes) {
        buyerService.saveBuyer(buyer);
        
        // Send registration confirmation email
        emailService.sendRegistrationEmail(buyer.getEmail(), buyer.getUsername(), "buyer");

        // Add a flash attribute for the success message
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! A confirmation email has been sent to your registered email address.");

        return "redirect:/login";  // Redirect to login page
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";  // This should correspond to login
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Buyer buyer, Model model, HttpSession session) {
        Buyer existingBuyer = buyerService.getBuyerByUsername(buyer.getUsername()); // Assuming you have this method in your service
        
        if (existingBuyer != null && existingBuyer.getPassword().equals(buyer.getPassword())) {
        	session.setAttribute("userName", buyer.getUsername());
        	session.setAttribute("userId", buyer.getId());
            return "redirect:/dashboard";  // Redirect to buyer dashboard
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";  
        }
    }
    
    @GetMapping("/profile")
    public String viewBuyerProfile(@SessionAttribute("username") String username, Model model) {
        Optional<Buyer> buyerOpt = buyerService.findByUsername(username); // Fetch buyer using the username
        if (buyerOpt.isPresent()) {
            model.addAttribute("buyer", buyerOpt.get());
            return "profile";  // Render the buyer profile template
        }
        return "redirect:/login"; // Redirect to login if buyer not found
    }

    @PostMapping("/updateProfile")
    public String updateBuyerProfile(@ModelAttribute Buyer buyer, Model model, HttpSession session) {
        // Retrieve the username from session
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";  // Redirect to login if session is missing
        }

        Optional<Buyer> existingBuyerOpt = buyerService.findByUsername(username);
        if (existingBuyerOpt.isPresent()) {
            Buyer existingBuyer = existingBuyerOpt.get();
            existingBuyer.setEmail(buyer.getEmail());
            existingBuyer.setPhoneNumber(buyer.getPhoneNumber());
            existingBuyer.setAddress(buyer.getAddress());
            
            // Update password only if a new one is provided
            if (buyer.getPassword() != null && !buyer.getPassword().isEmpty()) {
                existingBuyer.setPassword(buyer.getPassword());
            }
            buyerService.saveBuyer(existingBuyer); // Save updated buyer
            model.addAttribute("success", "Profile updated successfully!");
        } else {
            model.addAttribute("error", "Buyer not found!");
        }
        
        return "redirect:/dashboard";  // Redirect back to dashboard after updating
    }

    private Map<String, String> otpStorage = new HashMap<>();

    // Generate and send OTP for email verification
    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, Object>> sendOtp(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        System.out.println("Sending OTP to: " + email); // Logging email
        try {
            // Generate a 6-digit OTP
            String otp = String.format("%06d", new Random().nextInt(1000000)); // Ensure OTP is 6 digits
            otpStorage.put(email, otp); // Store the OTP associated with the email
            
            // Log the OTP and email for verification
            System.out.println("Generated OTP: " + otp + " for Email: " + email);

            // Send OTP to user's email
            emailService.sendOtpEmail(email, otp); 

            // Return a structured JSON response
            return ResponseEntity.ok(Map.of("success", true, "message", "OTP sent successfully to " + email));
        } catch (Exception e) {
            System.err.println("Error sending OTP email: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
            // Return a structured error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("success", false, "message", "Failed to send OTP. Please try again."));
        }
    }

    // Verify the OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestParam("email") String email, 
                                                          @RequestParam("otp") String otp) {
        // Retrieve stored OTP from the storage
        String storedOtp = otpStorage.get(email);
        System.out.println("Verifying OTP for email: " + email); // Log email being verified
        System.out.println("Stored OTP: " + storedOtp + ", Input OTP: " + otp); // Log stored and input OTP for debugging

        Map<String, Object> response = new HashMap<>();

        // Check if the OTP is correct
        if (storedOtp != null && storedOtp.equals(otp)) {
            // OTP is correct
            response.put("success", true);
            otpStorage.remove(email); // Remove OTP after verification
            System.out.println("OTP verification successful for email: " + email); // Log success
        } else {
            // OTP is incorrect
            response.put("success", false);
            System.out.println("OTP verification failed for email: " + email); // Log failure
        }

        // Return the response as JSON
        return ResponseEntity.ok(response);
    }

    
    @PostMapping("/test-email")
    public ResponseEntity<String> testEmail() {
        try {
            emailService.sendOtpEmail("azhaku333@gmail.com", "123456");
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send email: " + e.getMessage());
        }
    }

}
