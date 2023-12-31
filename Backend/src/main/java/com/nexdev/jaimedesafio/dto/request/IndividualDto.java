package com.nexdev.jaimedesafio.dto.request;


import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDto {

    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    private String ir;
    private String name;
    private Date birthday;
}
