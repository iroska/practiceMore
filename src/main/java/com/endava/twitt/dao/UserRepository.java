package com.endava.twitt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endava.twitt.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
