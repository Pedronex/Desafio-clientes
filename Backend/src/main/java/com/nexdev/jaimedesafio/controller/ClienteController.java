package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.ClienteDto;
import com.nexdev.jaimedesafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/cliente")
    private ResponseEntity createCliente(@RequestBody ClienteDto clienteDto) {
        System.out.println(clienteDto);

        return clienteService.createCliente(clienteDto);
    }

    @PutMapping("/cliente")
    private ResponseEntity updateCliente(@RequestBody ClienteDto clienteDto, @RequestParam String id) {
        System.out.println(clienteDto);
        System.out.println(id);
        return clienteService.updateCliente(clienteDto, id);
    }

}
