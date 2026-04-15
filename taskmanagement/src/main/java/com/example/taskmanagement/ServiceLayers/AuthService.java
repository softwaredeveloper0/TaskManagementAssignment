package com.example.taskmanagement.ServiceLayers;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.taskmanagement.DTO.LoginRequest;
import com.example.taskmanagement.DTO.RegisterRequest;
import com.example.taskmanagement.DTO.VerifyOTP;
import com.example.taskmanagement.Entities.AccountEntity;
import com.example.taskmanagement.Entities.OtpEntity;
import com.example.taskmanagement.Entities.UserEntity;
import com.example.taskmanagement.Exceptions.CustomException;
import com.example.taskmanagement.JWT.JwtUtil;
import com.example.taskmanagement.Repositories.AccountRepo;
import com.example.taskmanagement.Repositories.OtpRepo;
import com.example.taskmanagement.Repositories.UserRepo;



@Service
public class AuthService {

        private final UserRepo userRepository;
        private final OtpRepo otpRepository;
        private final AccountRepo accountRepository;
        private final JwtUtil jwtUtil;

        public AuthService(UserRepo userRepository, OtpRepo otpRepository, AccountRepo accountRepository, JwtUtil jwtUtil) {
            this.userRepository = userRepository;
            this.otpRepository = otpRepository;
            this.accountRepository = accountRepository;
            this.jwtUtil = jwtUtil;
        }


    public String registerUser(RegisterRequest request) {

        if (userRepository.findByMobileNumber(request.getMobileNumber()).isPresent()) {
            throw new CustomException("User already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUserName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setStatus("PENDING");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        String otpValue = String.valueOf(new Random().nextInt(900000) + 100000);


        OtpEntity otp = new OtpEntity();
        otp.setUserId(user.getId());
        otp.setOtp(otpValue);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otp.setCreatedAt(LocalDateTime.now());

        otpRepository.save(otp);

        return "User registered. OTP: " + otpValue;
    }

    public String verifyOtp(VerifyOTP request) {

        UserEntity user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new CustomException("User not found"));

        OtpEntity otp = otpRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CustomException("OTP not found"));

        if (!otp.getOtp().equals(request.getOtp()) ||
                otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new CustomException("Invalid or expired OTP");
        }

        user.setStatus("ACTIVE");
        userRepository.save(user);

        AccountEntity account = new AccountEntity();
        account.setUserId(user.getId());
        account.setBalance(1000.0);
        account.setCreatedAt(LocalDateTime.now());

        accountRepository.save(account);

        otpRepository.delete(otp);

        return "User verified successfully";
    }

    public String login(LoginRequest request) {

        UserEntity user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new CustomException("User not found"));

        if (!user.getStatus().equals("ACTIVE")) {
            throw new CustomException("User not verified");
        }

        return jwtUtil.generateToken(user.getMobileNumber());
    }
}
