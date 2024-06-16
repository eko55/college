package com.example.college.repository;

import com.example.college.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByName(String name);

    Course findByName(String name);
}
