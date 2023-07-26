package com.nexdev.jaimedesafio.dto.respose;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDto {

    private String ir;
    private String name;
    private Date birthday;
}
