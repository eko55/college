package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.FacultyDTO;
import com.example.college.model.entity.Faculty;
import com.example.college.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(FacultyDTO facultyDTO) {
        if (facultyRepository.existsByName(facultyDTO.name())) {
            throw new ResourceAlreadyExistsException(String.format("Faculty with name %s already exists!", facultyDTO.name()));
        }
        Faculty faculty = new Faculty(facultyDTO);
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFaculty(String name) {
        Faculty faculty = facultyRepository.findByName(name);
        if (faculty == null) {
            throw new ResourceNotFoundException(String.format("Faculty with name %s does not exist!", name));
        }
        return facultyRepository.findByName(name);
    }

    public Faculty getFaculty(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty == null) {
            throw new ResourceNotFoundException(String.format("Faculty with id %s does not exist!", id));
        }
        return facultyRepository.findById(id).get();
    }

    public void deleteFaculty(Long id) {
        if (facultyRepository.existsById((id))) {
            facultyRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Faculty with id %s does not exist!", id));
        }
    }

    @Transactional //https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
    public void deleteFaculty(String name) {
        if (facultyRepository.existsByName((name))) {
            facultyRepository.deleteByName(name);
        } else {
            throw new ResourceNotFoundException(String.format("Faculty with name %s does not exist!", name));
        }
    }

    public Faculty editFaculty(Long id, FacultyDTO facultyDTO) {
        if (facultyRepository.existsById(id)) {
            Faculty faculty = new Faculty(facultyDTO);
            faculty.setId(id);
            return facultyRepository.save(faculty);
        } else {
            throw new ResourceNotFoundException(String.format("Faculty with id %s does not exist!", id));
        }
    }
}

