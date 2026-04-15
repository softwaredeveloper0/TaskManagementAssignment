package com.example.taskmanagement.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.Entities.OtpEntity;

@Repository
public interface OtpRepo extends JpaRepository<OtpEntity, Long> {
    
    

    Optional<OtpEntity> findByUserId(Long userId);
}
