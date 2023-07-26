package com.nexdev.jaimedesafio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Juridica")
public class Legal {
    @Id
    private String cnpj;

    @Column(name = "razao_social")
    private String corporateName;

    @Column(name = "nome_fantasia")
    private String trade;

    @Override
    public String toString() {
        return "Legal{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + corporateName + '\'' +
                ", nomeFantasia='" + trade + '\'' +
                '}';
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }
}
