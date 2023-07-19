package com.nexdev.jaimedesafio.dto.request;


import java.util.Date;

public class FisicaDto {

    private String cpf;
    private String nome;
    private Date nascimento;

    @Override
    public String toString() {
        return "FisicaDto{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                '}';
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }


    public Date getNascimento() {
        return nascimento;
    }
}
