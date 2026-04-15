package com.example.taskmanagement.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.Entities.TransactionEntity;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {
    
    List<TransactionEntity> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
