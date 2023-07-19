package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.ConsumerDto;
import com.nexdev.jaimedesafio.service.ConsumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    final
    ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/client")
    private ResponseEntity<String> createConsumer(@RequestBody ConsumerDto consumerDto) {
        return consumerService.createConsumer(consumerDto);
    }

    @PutMapping("/client")
    private ResponseEntity<String> updateConsumer(@RequestBody ConsumerDto consumerDto, @RequestParam String id) {
        return consumerService.updateConsumer(consumerDto, id);
    }

}
