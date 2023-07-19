package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.Repository.UsuarioRepository;
import com.nexdev.jaimedesafio.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.nexdev.jaimedesafio.util.Encrypt.encrypt;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUsuarioByLogin(String login, String password) throws Exception {
        if(usuarioRepository.findByLogin(login).isPresent()){
            Usuario usuario = usuarioRepository.findByLogin(login).get();
            if (Objects.equals(usuario.getSenha(), encrypt(password))){
                return usuario;
            }
        }
        return null;
    }

    public void CreateOrUpdateUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
