package com.example.college.model.entity;

import com.example.college.model.dto.DepartmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    @Column(name = "faculty_id")
    private Long facultyId;

    public Department(DepartmentDTO request) {
        this.name = request.name();
        this.phone = request.phone();
        this.email = request.email();
        this.facultyId = request.facultyId();
    }
}
