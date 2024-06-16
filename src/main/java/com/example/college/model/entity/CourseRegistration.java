package com.example.college.model.entity;

import com.example.college.model.dto.CourseRegistrationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses_registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "student_id")
    private Long studentId;

    public CourseRegistration(CourseRegistrationDTO courseRegistrationDTO) {
        this.courseId = courseRegistrationDTO.courseId();
        this.studentId = courseRegistrationDTO.studentId();
    }
}
