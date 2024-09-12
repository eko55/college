package com.example.college.model.entity;

import com.example.college.model.dto.TeacherDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NotBlank(message = "personalNumber should not be blank")
    @Pattern(regexp = "[0-9]{10}")
    private String personalNumber;

    @Column(name = "first_name")
    @NotBlank(message = "firstName should not be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "lastName should not be blank")
    private String lastName;

    @Column(name = "department_id")
    @NotNull(message = "departmentId should not be null")
    private Long departmentId;

    public Teacher(TeacherDTO teacherDTO) {
        this.personalNumber = teacherDTO.personalNumber();
        this.firstName = teacherDTO.firstName();
        this.lastName = teacherDTO.lastName();
        this.departmentId = teacherDTO.departmentId();
    }
}
