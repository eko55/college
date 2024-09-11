package com.example.college.model.entity;

import com.example.college.model.dto.CollegeDTO;
import com.example.college.model.dto.CourseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "college")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    public College(CollegeDTO collegeDTO) {
        this.name = collegeDTO.name();
        this.address = collegeDTO.address();
    }
}
