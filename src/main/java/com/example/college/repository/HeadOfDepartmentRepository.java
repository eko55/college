package com.example.college.repository;

import com.example.college.model.entity.HeadOfDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadOfDepartmentRepository extends JpaRepository<HeadOfDepartment, Long> {

    boolean existsByTeacherIdAndDepartmentId(Long teacherId, Long departmentId);
}
