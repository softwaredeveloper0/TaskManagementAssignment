package com.example.taskmanagement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.taskmanagement.DTO.RegisterRequest;
import com.example.taskmanagement.Entities.UserEntity;
import com.example.taskmanagement.Repositories.OtpRepo;
import com.example.taskmanagement.Repositories.UserRepo;
import com.example.taskmanagement.ServiceLayers.AuthService;


public class AuthServiceTest {

    @Mock
    private UserRepo userRepository;

    @Mock
    private OtpRepo otpRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {

        RegisterRequest request = new RegisterRequest();
        request.setUserName("Test");
        request.setEmail("test@gmail.com");
        request.setMobileNumber("9999999999");

        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);

        when(userRepository.findByMobileNumber(request.getMobileNumber()))
                .thenReturn(java.util.Optional.empty());
        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(savedUser);

        String result = authService.registerUser(request);

        assertTrue(result.startsWith("User registered. OTP: "));
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(otpRepository, times(1)).save(any());
    }
}
