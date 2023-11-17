package com.vivatech.service;

import com.vivatech.dto.UserProfileResponse;
import com.vivatech.dto.UserRegistrationRequest;

public interface AuthService {

    void registerUser(UserRegistrationRequest registrationRequest);

    String loginUser(String username, String otp);

    UserProfileResponse getUserProfile(String username);
}

