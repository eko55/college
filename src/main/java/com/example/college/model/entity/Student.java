package com.example.college.model.entity;

import com.example.college.model.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faculty_number")
    private String facultyNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Student(StudentDTO studentDTO) {
        this.facultyNumber = studentDTO.facultyNumber();
        this.firstName = studentDTO.firstName();
        this.lastName = studentDTO.lastName();
    }
}
