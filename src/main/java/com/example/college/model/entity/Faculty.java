package com.example.college.model.entity;

import com.example.college.model.dto.FacultyCreationRequest;
import com.example.college.model.dto.FacultyEditRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    @Column(name = "email")
    private String email;

    public Faculty() {
    }

    public Faculty(FacultyCreationRequest facultyCreationRequest){
        this.name = facultyCreationRequest.name();
        this.address = facultyCreationRequest.address();
        this.phone = facultyCreationRequest.phone();
        this.email = facultyCreationRequest.email();
    }

    public Faculty(Long id,FacultyEditRequest facultyEditRequest){
        this.id = id;
        this.name = facultyEditRequest.name();
        this.address = facultyEditRequest.address();
        this.phone = facultyEditRequest.phone();
        this.email = facultyEditRequest.email();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
