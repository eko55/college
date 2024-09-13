package com.example.college.repository;

import com.example.college.model.entity.CourseAccreditation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseAccreditationRepository extends JpaRepository<CourseAccreditation, Long> {

    boolean existsByCourseIdAndTeacherId(Long courseId, Long teacherId);

    List<CourseAccreditation> findAllByTeacherId(Long teacherId);
}
