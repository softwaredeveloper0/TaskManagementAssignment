package com.example.taskmanagement.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.DTO.TransactionRequest;
import com.example.taskmanagement.ServiceLayers.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer( @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.transfer(request));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactions(userId));
    }
}
