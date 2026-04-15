package com.example.taskmanagement.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.Entities.UserEntity;

@Repository
public interface  UserRepo extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByMobileNumber(String mobileNumber);
    Optional<UserEntity> findByEmail(String email);
}
