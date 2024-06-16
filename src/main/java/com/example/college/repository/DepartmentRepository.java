package com.example.college.repository;

import com.example.college.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);

    Department findByName(String name);

    void deleteByName(String name);
}
