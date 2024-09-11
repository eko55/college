package com.example.college.repository;

import com.example.college.model.entity.Rector;
import com.example.college.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RectorRepository extends JpaRepository<Rector, Long> {

    Optional<Rector> findByPersonalNumber(String pn);

    boolean existsByPersonalNumber(String pn);

    void deleteByPersonalNumber(String pn);

    @Modifying
    @Query("delete from Rector")
    void deleteRector();
}
