package com.example.taskmanagement.Entities;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "otp")
public class OtpEntity {
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "otp", nullable = false)
    private String otp;
    @Column(name = "expiry_time", nullable = false)
    private LocalDateTime expiryTime;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    
}
