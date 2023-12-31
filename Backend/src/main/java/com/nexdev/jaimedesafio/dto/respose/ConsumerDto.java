package com.nexdev.jaimedesafio.dto.respose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDto {
    private String id;
    private String phone;
    private IndividualDto individualConsumer;
    private LegalDto legalConsumer;
    private String userID;
}
