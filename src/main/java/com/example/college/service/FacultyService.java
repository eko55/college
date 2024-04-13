package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.model.Faculty;
import com.example.college.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        if (facultyRepository.existsByName(faculty.getName())) {
            throw new ResourceAlreadyExistsException(String.format("Faculty with name %s already exists!",faculty.getName()));
        }
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }
}
