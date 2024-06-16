package com.example.college.repository;

import com.example.college.model.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);
}
