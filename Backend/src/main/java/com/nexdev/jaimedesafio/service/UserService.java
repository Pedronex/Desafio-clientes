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

    // Método para buscar um usuário por login e senha
    public User getUserByLogin(String login, String password) {
        // Autenticar o usuário com o login e senha fornecidos
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login,
                        password
                )
        );

        // Retornar o usuário encontrado no banco de dados pelo login
        return userRepository.findByLogin(login).orElseThrow();
    }

    // Método para criar ou atualizar um usuário
    public void createOrUpdateUser(User user){
        // Salvar o usuário no banco de dados
        userRepository.save(user);
    }
}
