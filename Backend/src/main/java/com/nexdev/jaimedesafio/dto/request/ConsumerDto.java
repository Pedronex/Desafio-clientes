package com.nexdev.jaimedesafio.dto.request;

public class ConsumerDto {
    private String phone;
    private IndividualDto individualConsumer;
    private LegalDto legalConsumer;

    private String userID;

    @Override
    public String toString() {
        return "ConsumerDto{" +
                "phone='" + phone + '\'' +
                ", individualConsumer=" + individualConsumer +
                ", legalConsumer=" + legalConsumer +
                ", userID='" + userID + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public IndividualDto getIndividualConsumer() {
        return individualConsumer;
    }

    public LegalDto getLegalConsumer() {
        return legalConsumer;
    }

    public String getUserID() {
        return userID;
    }
}
