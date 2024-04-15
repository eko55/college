package com.example.college.repository;

import com.example.college.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByName(String name);

    Faculty findByName(String name);

    void deleteByName(String name);
}
