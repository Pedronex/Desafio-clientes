package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.FormLoginDto;
import com.nexdev.jaimedesafio.dto.respose.LoginDto;
import com.nexdev.jaimedesafio.entity.User;
import com.nexdev.jaimedesafio.provider.TokenProvider;
import com.nexdev.jaimedesafio.security.Role;
import com.nexdev.jaimedesafio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    private final UserService userService;
    private final TokenProvider provider;
    private final PasswordEncoder passwordEncoder;

    // Endpoint para autenticar um usuário com base nos dados fornecidos no corpo da requisição e gerar um token de acesso
    @PostMapping("/login")
    private ResponseEntity<LoginDto> loginUser(@RequestBody @Valid FormLoginDto formLoginDto) {
        try {
            // Autenticar o usuário com base no login e senha fornecidos
            UserDetails result = userService.getUserByLogin(formLoginDto.getLogin(), formLoginDto.getPassword());

            System.out.println(result);
            // Gerar um token de acesso para o usuário autenticado
            String token = provider.generateToken(result);

            // Criar um objeto LoginDto para encapsular o token gerado e retorná-lo na resposta
            var loginDto = LoginDto.builder().token(token).build();

            // Retornar a resposta com o token no corpo e código de status OK (200)
            return ResponseEntity.ok(loginDto);
        } catch (BadCredentialsException e) {
            var loginDto = LoginDto.builder().message(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto);
        } catch (Exception e) {
            e.printStackTrace();
            // Se ocorrer um erro durante a autenticação, retornar um token vazio BAD REQUEST (400)
            var loginDto = LoginDto.builder().token("").build();
            return ResponseEntity.badRequest().body(loginDto);
        }
    }

    // Endpoint para criar um usuário com base nos dados fornecidos no corpo da requisição
    @PostMapping("/user")
    private ResponseEntity<LoginDto> createUser(@RequestBody @Valid FormLoginDto loginDto) {
        try {
            // Criar um objeto User com base nos dados fornecidos na requisição
            var user = User.builder()
                    .login(loginDto.getLogin())
                    .pass(passwordEncoder.encode(loginDto.getPassword()))
                    .role(Role.USER)
                    .build();
            // Chamar o serviço para criar ou atualizar o usuário no banco de dados
            userService.createOrUpdateUser(user);

            var token = provider.generateToken(user);

            // Retornar uma resposta com código de condição CREATED (201) e uma mensagem indicando que o usuário foi cadastrado
            return ResponseEntity.ok(LoginDto.builder().token(token).build());
        } catch (Exception e) {
            e.printStackTrace();
            // Se ocorrer um erro durante a criação do usuário, retornar uma resposta com código de condição BAD REQUEST (400) e uma mensagem de erro
            return ResponseEntity.badRequest().body(LoginDto.builder().token("").build());
        }
    }
}
