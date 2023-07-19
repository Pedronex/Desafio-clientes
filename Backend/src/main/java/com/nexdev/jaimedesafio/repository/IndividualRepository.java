package com.nexdev.jaimedesafio.repository;

import com.nexdev.jaimedesafio.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {
}
