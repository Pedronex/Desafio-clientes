package com.nexdev.jaimedesafio.controller;

import com.nexdev.jaimedesafio.dto.request.LoginDto;
import com.nexdev.jaimedesafio.dto.respose.UsuarioDto;
import com.nexdev.jaimedesafio.entity.Usuario;
import com.nexdev.jaimedesafio.service.UsuarioService;
import com.nexdev.jaimedesafio.util.Encrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    final
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    private ResponseEntity<UsuarioDto> loginUsuario(@RequestBody LoginDto loginDto) {
        try {
            Usuario result = usuarioService.getUsuarioByLogin(loginDto.getLogin(), loginDto.getSenha());
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new UsuarioDto(result.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UsuarioDto(""));
        }
    }

    @PostMapping("/user")
    private ResponseEntity<String> createUsuario(@RequestBody LoginDto loginDto) {
        try {
            Usuario usuario = new Usuario();
            usuario.setLogin(loginDto.getLogin());
            usuario.setSenha(Encrypt.encrypt(loginDto.getSenha()));
            usuarioService.CreateOrUpdateUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }


    }

}
