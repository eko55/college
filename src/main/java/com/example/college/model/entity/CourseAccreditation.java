package com.example.college.model.entity;

import com.example.college.model.dto.CourseAccreditationDTO;
import com.example.college.model.dto.CourseRegistrationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses_accreditations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAccreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "teacher_id")
    private Long teacherId;

    public CourseAccreditation(CourseAccreditationDTO courseAccreditationDTO) {
        this.courseId = courseAccreditationDTO.courseId();
        this.teacherId = courseAccreditationDTO.teacherId();
    }
}
