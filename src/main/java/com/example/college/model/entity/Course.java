package com.example.college.model.entity;

import com.example.college.model.dto.CourseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "semester_id")
    private Long semesterId;

    public Course(CourseDTO courseDTO) {
        this.name = courseDTO.name();
        this.description = courseDTO.description();
        this.teacherId = courseDTO.teacherId();
        this.semesterId = courseDTO.semesterId();
    }
}
