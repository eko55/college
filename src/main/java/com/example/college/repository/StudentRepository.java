package com.example.college.repository;

import com.example.college.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByFacultyNumber(String facultyNumber);
}
