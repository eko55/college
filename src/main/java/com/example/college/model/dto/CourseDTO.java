package com.example.college.model.dto;

public record CourseDTO(String name,
                        String description,
                        Long teacherId,
                        Integer semester,
                        Long programmeId) {
}
