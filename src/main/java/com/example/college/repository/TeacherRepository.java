package com.example.college.repository;

import com.example.college.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByPersonalNumber(String pn);
}
