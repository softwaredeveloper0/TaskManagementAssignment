package com.example.taskmanagement.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VerifyOTP {
    
    private String mobileNumber;
    private String otp;
}
