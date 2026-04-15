package com.example.taskmanagement.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionRequest {
    
    private Long senderId;
    private Long receiverId;
    private double amount;
}
