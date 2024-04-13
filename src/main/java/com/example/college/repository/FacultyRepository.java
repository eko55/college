package com.example.college.repository;

import com.example.college.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByName(String name);
}
