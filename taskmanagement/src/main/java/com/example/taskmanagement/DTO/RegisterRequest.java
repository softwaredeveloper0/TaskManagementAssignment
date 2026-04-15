package com.example.taskmanagement.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    private String userName;
    private String email;
    private String mobileNumber;
}
