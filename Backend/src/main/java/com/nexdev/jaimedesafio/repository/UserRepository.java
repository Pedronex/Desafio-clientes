package com.nexdev.jaimedesafio.repository;

import com.nexdev.jaimedesafio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u WHERE u.login = :login")
    Optional<User> findByLogin(String login);
}
