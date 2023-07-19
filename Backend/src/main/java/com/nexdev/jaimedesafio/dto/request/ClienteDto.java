package com.nexdev.jaimedesafio.dto.request;

public class ClienteDto {
    private String telefone;
    private FisicaDto fisicaCliente;
    private JuridicaDto juridicaCliente;

    private String userID;

    @Override
    public String toString() {
        return "ClienteDto{" +
                "telefone='" + telefone + '\'' +
                ", fisicaCliente=" + fisicaCliente +
                ", juridicaCliente=" + juridicaCliente +
                ", userID='" + userID + '\'' +
                '}';
    }

    public String getTelefone() {
        return telefone;
    }

    public FisicaDto getFisicaCliente() {
        return fisicaCliente;
    }

    public JuridicaDto getJuridicaCliente() {
        return juridicaCliente;
    }

    public String getUserID() {
        return userID;
    }
}
