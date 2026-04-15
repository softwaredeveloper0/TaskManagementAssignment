package com.example.taskmanagement.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.Entities.AccountEntity;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Long> {
    
    Optional<AccountEntity> findByUserId(Long userId);
}
