package com.vivatech.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivatech.service.EmailService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private EmailService emailService; // You need to implement this service

    private Map<String, String> otpStorage = new HashMap<>();

    @PostMapping("/otp/send")
    public ResponseEntity<?> sendOtp(@RequestParam String email) {
        // Generate OTP
        String otp = generateOtp();

        // Save OTP to storage (you might want to use a database for this)
        otpStorage.put(email, otp);

        // Send OTP via email
        boolean emailSent = sendOtpByEmail(email, otp);

        if (emailSent) {
            return ResponseEntity.ok("OTP sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to send OTP");
        }
    }

    @PostMapping("/otp/validate")
    public ResponseEntity<?> validateOtp(@RequestParam String email, @RequestParam String otp) {
        // Retrieve OTP from storage
        String storedOtp = otpStorage.get(email);

        if (storedOtp != null && storedOtp.equals(otp)) {
            // Valid OTP
            return ResponseEntity.ok("OTP validation successful");
        } else {
            // Invalid OTP
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }

    // Helper method to generate a random 6-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000); // Generates a random 6-digit number
        return String.valueOf(otpValue);
    }

    // Helper method to send OTP via email
    private boolean sendOtpByEmail(String email, String otp) {
        // Implement email sending logic using JavaMail or any other email library
        // Example:
        try {
            emailService.sendEmail(email, "Your OTP is: " + otp, "OTP Verification");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

