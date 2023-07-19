package com.nexdev.jaimedesafio.repository;

import com.nexdev.jaimedesafio.entity.Legal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRepository extends JpaRepository<Legal, String> {
}
