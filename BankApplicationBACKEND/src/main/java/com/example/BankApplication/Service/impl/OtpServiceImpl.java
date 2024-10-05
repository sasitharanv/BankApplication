package com.example.BankApplication.Service.impl;

import com.example.BankApplication.Service.OtpService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpServiceImpl implements OtpService {

    private static final int OTP_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    // Temporary storage for generated OTPs
    private final Map<String, String> otpStorage = new HashMap<>();

    public String generateOtp(String email) {
        StringBuilder otpBuilder = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otpBuilder.append(random.nextInt(10)); // Generate a digit (0-9)
        }

        // Store the generated OTP in memory
        otpStorage.put(email, otpBuilder.toString());
        return otpBuilder.toString();
    }

    public boolean validateOtp(String email, String otp) {
        // Retrieve the stored OTP for the given email
        String storedOtp = otpStorage.get(email);

        // Check if the stored OTP matches the provided OTP
        if (storedOtp != null && storedOtp.equals(otp)) {
            // Optionally, remove the OTP after validation
            otpStorage.remove(email);
            return true; // OTP is valid
        }

        return false; // OTP is invalid
    }

    // Optional: Clear OTP method to be called after successful registration
    public void clearOtp(String email) {
        otpStorage.remove(email);
    }
}
