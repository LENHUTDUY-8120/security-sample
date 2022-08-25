package com.lnduy8120.securitysample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lnduy8120.securitysample.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

    UserEntity findByUsername (String username);
    
}
