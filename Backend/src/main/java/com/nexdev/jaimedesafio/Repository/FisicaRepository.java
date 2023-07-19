package com.nexdev.jaimedesafio.Repository;

import com.nexdev.jaimedesafio.entity.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FisicaRepository extends JpaRepository<Fisica, String> {
}
