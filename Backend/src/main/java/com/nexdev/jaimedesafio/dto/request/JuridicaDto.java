package com.nexdev.jaimedesafio.dto.request;

public class JuridicaDto {

    private String cnpj;
    private String razao;
    private String fantasia;

    @Override
    public String toString() {
        return "JuridicaDto{" +
                "cnpj='" + cnpj + '\'' +
                ", razao='" + razao + '\'' +
                ", fantasia='" + fantasia + '\'' +
                '}';
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public String getFantasia() {
        return fantasia;
    }
}
