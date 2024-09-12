package com.example.college.repository;

import com.example.college.model.entity.Faculty;
import com.example.college.model.entity.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {

    boolean existsByName(String name);

    Programme findByName(String name);

    void deleteByName(String name);
}
