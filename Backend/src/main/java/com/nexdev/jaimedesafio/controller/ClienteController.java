package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.ClienteDto;
import com.nexdev.jaimedesafio.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    final
    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    private ResponseEntity<String> createCliente(@RequestBody ClienteDto clienteDto) {
        return clienteService.createCliente(clienteDto);
    }

    @PutMapping("/cliente")
    private ResponseEntity<String> updateCliente(@RequestBody ClienteDto clienteDto, @RequestParam String id) {
        return clienteService.updateCliente(clienteDto, id);
    }

}
