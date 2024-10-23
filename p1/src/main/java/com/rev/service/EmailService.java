package com.rev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Method to send OTP to the user's email for verification
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("RevShop - Email Verification OTP");

        String emailContent = String.format(
            "Dear User,\n\n" +
            "To complete your registration with RevShop, please use the following One-Time Password (OTP) to verify your email address:\n\n" +
            "OTP: %s\n\n" +
            "This OTP is valid for 5 minutes.\n\n" +
            "If you did not request this email, please ignore it.\n\n" +
            "Best regards,\n" +
            "The RevShop Team",
            otp
        );

        message.setText(emailContent);
        mailSender.send(message);
    }

    // Method to send registration confirmation email after successful registration
    public void sendRegistrationEmail(String toEmail, String firstName, String userType) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to RevShop - Your Registration is Successful!");

        String emailContent = String.format(
            "Dear %s,\n\n" +
            "Thank you for registering with RevShop! We are excited to welcome you to our e-commerce community.\n\n" +
            "Your registration was successful, and you can now enjoy a wide range of products and services tailored just for you. Here are a few things you can do next:\n\n" +
            "- Log In: Access your account and explore our marketplace.\n" +
            "- Browse Products: Check out our latest offerings and special deals.\n" +
            "- Get Support: If you have any questions, feel free to reach out to our customer service team.\n\n" +
            "Your account details are as follows:\n\n" +
            "Email: %s\n" +
            "User Type: %s\n\n" +
            "For any further assistance or inquiries, don’t hesitate to contact us at support@revshop.com.\n\n" +
            "We are thrilled to have you on board and look forward to serving you!\n\n" +
            "Best regards,\n\n" +
            "The RevShop Team",
            firstName, toEmail, userType
        );

        message.setText(emailContent);
        mailSender.send(message);
    }
}
