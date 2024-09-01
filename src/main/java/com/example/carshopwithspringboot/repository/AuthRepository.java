package com.example.carshopwithspringboot.repository;

import com.example.carshopwithspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {

    User findUserByUserName(String username);
}