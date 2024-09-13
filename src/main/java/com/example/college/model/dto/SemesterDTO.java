package com.example.college.model.dto;

import com.example.college.model.entity.Course;

import java.util.List;

public record SemesterDTO(Integer semesterNumber,
                          Long programme_id,
                          List<Course> courses) {
}
