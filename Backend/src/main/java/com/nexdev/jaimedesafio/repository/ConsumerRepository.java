package com.nexdev.jaimedesafio.repository;

import com.nexdev.jaimedesafio.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {
}
