package com.vivatech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivatech.dto.UserProfileResponse;
import com.vivatech.dto.UserRegistrationRequest;
import com.vivatech.model.User;
import com.vivatech.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService; // Inject UserService for user-related operations

    @Override
    public void registerUser(UserRegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest);
    }

    @Override
    public String loginUser(String username, String otp) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (otpService.validateOtp(username, otp)) {
            // Valid OTP, perform login
            // Generate and return JWT token
            return jwtService.generateToken(username);
        } else {
            throw new RuntimeException("Invalid OTP");
        }
    }

    @Override
    public UserProfileResponse getUserProfile(String username) {
        return userService.getUserProfile(username);
    }
}

