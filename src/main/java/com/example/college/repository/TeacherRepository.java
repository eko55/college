package com.example.college.repository;

import com.example.college.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByPersonalNumber(String pn);

    boolean existsByPersonalNumber(String pn);

    void deleteByPersonalNumber(String pn);
}
