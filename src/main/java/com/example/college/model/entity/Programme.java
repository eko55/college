package com.example.college.model.entity;

import com.example.college.model.dto.DepartmentDTO;
import com.example.college.model.dto.ProgrammeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "programmes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Programme(ProgrammeDTO request) {
        this.name = request.name();
        this.description = request.description();
    }
}
