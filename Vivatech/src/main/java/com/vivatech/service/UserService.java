package com.vivatech.service;

import com.vivatech.dto.UserProfileResponse;
import com.vivatech.dto.UserRegistrationRequest;

public interface UserService {

    /**
     * Register a new user.
     *
     * @param registrationRequest Registration details.
     */
    void registerUser(UserRegistrationRequest registrationRequest);

    /**
     * Login the user with the provided username and OTP.
     *
     * @param username User's username.
     * @param otp      One-Time Password for login.
     * @return JWT token upon successful login.
     * @throws RuntimeException If the OTP is invalid or the user is not found.
     */
    String loginUser(String username, String otp);

    /**
     * Get the user profile based on the username.
     *
     * @param username User's username.
     * @return UserProfileResponse containing user profile details.
     * @throws RuntimeException If the user is not found.
     */
    UserProfileResponse getUserProfile(String username);
}

