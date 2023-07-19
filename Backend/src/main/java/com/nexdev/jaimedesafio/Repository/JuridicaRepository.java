package com.nexdev.jaimedesafio.Repository;

import com.nexdev.jaimedesafio.entity.Juridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuridicaRepository extends JpaRepository<Juridica, String> {
}
