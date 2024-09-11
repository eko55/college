package com.example.college.model.entity;

import com.example.college.model.dto.RectorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rector")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rector {

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

    public Rector(RectorDTO rectorDTO) {
        this.personalNumber = rectorDTO.personalNumber();
        this.firstName = rectorDTO.firstName();
        this.lastName = rectorDTO.lastName();
    }
}
