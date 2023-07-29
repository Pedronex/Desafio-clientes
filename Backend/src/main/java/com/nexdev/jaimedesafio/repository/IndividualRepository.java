package com.nexdev.jaimedesafio.repository;

import com.nexdev.jaimedesafio.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {
    @Transactional
    @Modifying
    @Query("update Individual i set i.birthday = ?1, i.name = ?2 where i.ir = ?3")
    void updateBirthdayAndNameByIr(@Nullable Date birthday, @Nullable String name, String ir);
}
