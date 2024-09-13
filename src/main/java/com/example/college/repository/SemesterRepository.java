package com.example.college.repository;

import com.example.college.model.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    boolean existsBySemesterNumberAndProgrammeId(Integer semesterNumber,Long programmeId);

    List<Semester> findAllByProgrammeId(Long programmeId);
}
