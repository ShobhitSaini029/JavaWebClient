package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

}
