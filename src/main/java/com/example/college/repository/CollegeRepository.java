package com.example.college.repository;

import com.example.college.model.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {

    boolean existsByName(String name);

    College findByName(String name);

    void deleteByName(String name);
}
