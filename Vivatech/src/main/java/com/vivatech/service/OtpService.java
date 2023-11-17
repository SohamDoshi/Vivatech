package com.vivatech.service;

public interface OtpService {

    String generateOtp(String username);

    boolean validateOtp(String username, String otp);
}

