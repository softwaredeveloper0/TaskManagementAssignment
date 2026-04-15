package com.example.taskmanagement.ServiceLayers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanagement.DTO.TransactionRequest;
import com.example.taskmanagement.Entities.AccountEntity;
import com.example.taskmanagement.Entities.TransactionEntity;
import com.example.taskmanagement.Exceptions.CustomException;
import com.example.taskmanagement.Repositories.AccountRepo;
import com.example.taskmanagement.Repositories.TransactionRepo;

@Service
public class TransactionService {

    private final AccountRepo accountRepository;
    private final TransactionRepo transactionRepository;

    public TransactionService(AccountRepo accountRepository, TransactionRepo transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public String transfer(TransactionRequest request) {

        AccountEntity sender = accountRepository.findByUserId(request.getSenderId())
                .orElseThrow(() -> new CustomException("Sender not found"));

        AccountEntity receiver = accountRepository.findByUserId(request.getReceiverId())
                .orElseThrow(() -> new CustomException("Receiver not found"));

        if (request.getSenderId().equals(request.getReceiverId())) {
            throw new CustomException("Cannot transfer to same account");
        }

        if (request.getAmount() <= 0) {
            throw new CustomException("Invalid transfer amount");
        }

        if (sender.getBalance() < request.getAmount()) {
            throw new CustomException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        TransactionEntity txn = new TransactionEntity();
        txn.setSenderId(request.getSenderId());
        txn.setReceiverId(request.getReceiverId());
        txn.setAmount(request.getAmount());
        txn.setTimeStamp(LocalDateTime.now());
        txn.setStatus("SUCCESS");

        transactionRepository.save(txn);

        return "Transfer successful";
    }

    public List<TransactionEntity> getTransactions(Long userId) {

        return transactionRepository
                .findBySenderIdOrReceiverId(userId, userId);
    }
}
