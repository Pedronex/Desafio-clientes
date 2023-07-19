package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.Repository.UsuarioRepository;
import com.nexdev.jaimedesafio.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.nexdev.jaimedesafio.util.Encrypt.encrypt;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioByLogin(String login, String password) throws Exception {
        System.out.println("Login: " + login + " Senha: " + password);

        System.out.println(usuarioRepository.findAll());
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
        System.out.println("usuario criado: " + usuario);
    }

    public Usuario FistData(){
        Usuario usuario = usuarioRepository.findAll().get(0);
        return usuario;
    }

    public void DeleteUsuario(String id){
        usuarioRepository.deleteById(id);
    }
}
