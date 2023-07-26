package com.nexdev.jaimedesafio.dto.respose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LegalDto {
    private String cnpj;
    private String corporateName;
    private String trade;
}
