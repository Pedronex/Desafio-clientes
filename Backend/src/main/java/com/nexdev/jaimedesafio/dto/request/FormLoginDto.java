package com.nexdev.jaimedesafio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormLoginDto {

    @NotBlank(message = "O campo login deve ser preenchido")
    private String login;

    @NotBlank(message = "informe a senha")
    private String password;
}
