package com.example.college.model.entity;

import com.example.college.model.dto.HeadOfDepartmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "head_of_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadOfDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "department_id")
    private Long departmentId;

    public HeadOfDepartment(HeadOfDepartmentDTO headOfDepartmentDTO) {
        this.teacherId = headOfDepartmentDTO.teacherId();
        this.departmentId = headOfDepartmentDTO.departmentId();
    }
}
