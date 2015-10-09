package com.endava.twitt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endava.twitt.models.Followers;

public interface FollowersRepository extends JpaRepository<Followers, Integer> {

}
