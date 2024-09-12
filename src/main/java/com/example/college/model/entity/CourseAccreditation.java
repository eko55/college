package com.example.college.model.entity;

import com.example.college.model.dto.CourseAccreditationDTO;
import com.example.college.model.dto.CourseRegistrationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses_accreditation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAccreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "teacher_personal_number")
    private String teacherPersonalNumber;

    public CourseAccreditation(CourseAccreditationDTO courseAccreditationDTO) {
        this.courseName = courseAccreditationDTO.courseName();
        this.teacherPersonalNumber = courseAccreditationDTO.teacherPersonalNumber();
    }
}
