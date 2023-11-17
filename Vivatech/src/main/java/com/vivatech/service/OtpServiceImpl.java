package com.vivatech.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private Map<String, String> otpStorage = new HashMap<>();

    @Override
    public String generateOtp(String username) {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000); // Generates a random 6-digit number
        String otp = String.valueOf(otpValue);

        // Save OTP to storage (you might want to use a database for this)
        otpStorage.put(username, otp);

        return otp;
    }

    @Override
    public boolean validateOtp(String username, String otp) {
        // Retrieve OTP from storage
        String storedOtp = otpStorage.get(username);

        return storedOtp != null && storedOtp.equals(otp);
    }
}

