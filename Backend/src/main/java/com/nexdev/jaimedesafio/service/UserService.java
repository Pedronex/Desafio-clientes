package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.repository.UserRepository;
import com.nexdev.jaimedesafio.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public User getUserByLogin(String login, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login,
                        password
                )
        );
        return userRepository.findByLogin(login).orElseThrow();
    }

    public void createOrUpdateUser(User user){
        userRepository.save(user);
    }
}
