package com.nexdev.jaimedesafio.dto.request;

public class LoginDto {

    private String login;
    private String password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "login='" + login + '\'' +
                ", senha='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

}
