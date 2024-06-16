package com.example.college.model.entity;

import com.example.college.model.dto.TeacherDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "department_id")
    private Long departmentId;

    public Teacher(TeacherDTO teacherDTO) {
        this.personalNumber = teacherDTO.personalNumber();
        this.firstName = teacherDTO.firstName();
        this.lastName = teacherDTO.lastName();
        this.departmentId = teacherDTO.departmentId();
    }
}
