package com.example.college.model.entity;

import com.example.college.model.dto.FacultyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "faculties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    @Column(name = "email")
    private String email;

//    @Column(name = "college_id")
//    private Long collegeId;

    public Faculty(FacultyDTO facultyDTO){
        this.name = facultyDTO.name();
        this.address = facultyDTO.address();
        this.phone = facultyDTO.phone();
        this.email = facultyDTO.email();
//        this.collegeId = facultyDTO.collegeId();
    }
}
