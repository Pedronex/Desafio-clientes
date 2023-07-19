package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.LoginDto;
import com.nexdev.jaimedesafio.dto.respose.UserDto;
import com.nexdev.jaimedesafio.entity.User;
import com.nexdev.jaimedesafio.service.UserService;
import com.nexdev.jaimedesafio.util.Encrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    private ResponseEntity<UserDto> loginUser(@RequestBody LoginDto loginDto) {
        try {
            User result = userService.getUserByLogin(loginDto.getLogin(), loginDto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new UserDto(result.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserDto(""));
        }
    }

    @PostMapping("/user")
    private ResponseEntity<String> createUser(@RequestBody LoginDto loginDto) {
        try {
            User user = new User();
            user.setLogin(loginDto.getLogin());
            user.setPassword(Encrypt.encrypt(loginDto.getPassword()));
            userService.CreateOrUpdateUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }


    }

}
