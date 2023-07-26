package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.FormConsumerDto;
import com.nexdev.jaimedesafio.entity.Consumer;
import com.nexdev.jaimedesafio.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsumerController {

    final
    ConsumerService consumerService;

    // Endpoint para criar um consumidor (cliente) com base nos dados fornecidos no corpo da requisição
    @PostMapping("/client")
    private ResponseEntity<String> createConsumer(@RequestBody FormConsumerDto formConsumerDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return consumerService.createConsumer(formConsumerDto, token);
    }

    // Endpoint para atualizar um consumidor existente com base nos dados fornecidos no corpo da requisição e no parâmetro "id"
    @PutMapping("/client")
    private ResponseEntity<String> updateConsumer(
            @RequestBody FormConsumerDto formConsumerDto,
            @RequestParam String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        return consumerService.updateConsumer(formConsumerDto, id, token);
    }

    // Endpoint para listar os consumidores que o usuário criou
    @GetMapping("/clients")
    private ResponseEntity<List<Consumer>> listConsumers() {
        return consumerService.getAllConsumers();
    }

}
