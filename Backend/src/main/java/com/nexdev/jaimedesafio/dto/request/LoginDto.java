package com.nexdev.jaimedesafio.dto.request;

public class LoginDto {

    private String login;
    private String senha;

    @Override
    public String toString() {
        return "LoginDto{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }


    public String getSenha() {
        return senha;
    }

}
