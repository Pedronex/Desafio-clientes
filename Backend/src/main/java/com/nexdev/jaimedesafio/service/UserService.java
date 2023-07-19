package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.repository.UserRepository;
import com.nexdev.jaimedesafio.entity.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.nexdev.jaimedesafio.util.Encrypt.encrypt;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLogin(String login, String password) throws Exception {
        if(userRepository.findByLogin(login).isPresent()){
            User user = userRepository.findByLogin(login).get();
            if (Objects.equals(user.getPassword(), encrypt(password))){
                return user;
            }
        }
        return null;
    }

    public void CreateOrUpdateUser(User user){
        userRepository.save(user);
    }
}
