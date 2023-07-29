package com.nexdev.jaimedesafio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "FISICA")
public class Individual {

    @Id
    @Column(name = "cpf")
    //Individual Registration - CPF
    private String ir;

    @Column(name = "data_nascimento")
    private Date birthday;

    @Column(name = "nome")
    private String name;

    @Override
    public String toString() {
        return "Individual{" +
                "dataNascimento=" + birthday +
                ", cpf='" + ir + '\'' +
                ", nome='" + name + '\'' +
                '}';
    }

    public void setIr(String cpf) {
        this.ir = cpf;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }
}
