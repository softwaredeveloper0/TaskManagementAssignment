package com.example.taskmanagement;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.taskmanagement.DTO.TransactionRequest;
import com.example.taskmanagement.Entities.AccountEntity;
import com.example.taskmanagement.Entities.TransactionEntity;
import com.example.taskmanagement.Exceptions.CustomException;
import com.example.taskmanagement.Repositories.AccountRepo;
import com.example.taskmanagement.Repositories.TransactionRepo;
import com.example.taskmanagement.ServiceLayers.TransactionService;

public class TransactionServiceTest {

    @Mock
    private AccountRepo accountRepository;

    @Mock
    private TransactionRepo transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TransferSuccess() {

        AccountEntity sender = new AccountEntity();
        sender.setUserId(1L);
        sender.setBalance(1000.0);

        AccountEntity receiver = new AccountEntity();
        receiver.setUserId(2L);
        receiver.setBalance(500.0);

        when(accountRepository.findByUserId(1L))
                .thenReturn(Optional.of(sender));

        when(accountRepository.findByUserId(2L))
                .thenReturn(Optional.of(receiver));

        TransactionRequest request = new TransactionRequest();
        request.setSenderId(1L);
        request.setReceiverId(2L);
        request.setAmount(200.0);

        String result = transactionService.transfer(request);

        assertEquals("Transfer successful", result);
        assertEquals(800.0, sender.getBalance());
        assertEquals(700.0, receiver.getBalance());

        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void testInsufficientBalance() {

        AccountEntity sender = new AccountEntity();
        sender.setUserId(1L);
        sender.setBalance(100.0);

        AccountEntity receiver = new AccountEntity();
        receiver.setUserId(2L);
        receiver.setBalance(500.0);

        when(accountRepository.findByUserId(1L))
                .thenReturn(Optional.of(sender));

        when(accountRepository.findByUserId(2L))
                .thenReturn(Optional.of(receiver));

        TransactionRequest request = new TransactionRequest();
        request.setSenderId(1L);
        request.setReceiverId(2L);
        request.setAmount(200.0);

        assertThrows(CustomException.class, () -> {
            transactionService.transfer(request);
        });
    }

    @Test
    void testSenderNotFound() {

        when(accountRepository.findByUserId(1L))
                .thenReturn(Optional.empty());

        TransactionRequest request = new TransactionRequest();
        request.setSenderId(1L);
        request.setReceiverId(2L);
        request.setAmount(100.0);

        assertThrows(CustomException.class, () -> {
            transactionService.transfer(request);
        });
    }
}
