package com.nexdev.jaimedesafio.dto.request;

public class LegalDto {

    private String cnpj;
    private String corporateName;
    private String trade;

    @Override
    public String toString() {
        return "LegalDto{" +
                "cnpj='" + cnpj + '\'' +
                ", razao='" + corporateName + '\'' +
                ", fantasia='" + trade + '\'' +
                '}';
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public String getTrade() {
        return trade;
    }
}
