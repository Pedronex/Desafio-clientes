package com.nexdev.jaimedesafio.Repository;

import com.nexdev.jaimedesafio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
    Optional<Usuario> findByLogin(String login);
}
