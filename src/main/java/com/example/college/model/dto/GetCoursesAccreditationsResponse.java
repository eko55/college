package com.example.college.model.dto;

public record GetCoursesAccreditationsResponse(Long courseId,
                                               String courseName,
                                               Long teacherId,
                                               String teacherFirstName,
                                               String teacherLastName) {
}
