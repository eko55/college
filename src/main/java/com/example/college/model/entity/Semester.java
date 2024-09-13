package com.example.college.model.entity;

import com.example.college.model.dto.SemesterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "semesters",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"semester_number", "programme_id"})
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semester_number")
    private Integer semesterNumber;

    @Column(name = "programme_id")
    private Long programmeId;

//    private List<Long> courses_ids;

    public Semester(SemesterDTO semesterDTO) {
        this.semesterNumber = semesterDTO.semesterNumber();
        this.programmeId = semesterDTO.programme_id();
//        this.courses_ids = semesterDTO.courses();
    }
}
