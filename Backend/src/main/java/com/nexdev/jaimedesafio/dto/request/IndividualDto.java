package com.nexdev.jaimedesafio.dto.request;


import java.util.Date;

public class IndividualDto {

    private String ir;
    private String name;
    private Date birthday;

    @Override
    public String toString() {
        return "IndividualDto{" +
                "ir='" + ir + '\'' +
                ", nome='" + name + '\'' +
                ", nascimento=" + birthday +
                '}';
    }

    public String getIr() {
        return ir;
    }

    public String getName() {
        return name;
    }


    public Date getBirthday() {
        return birthday;
    }
}
