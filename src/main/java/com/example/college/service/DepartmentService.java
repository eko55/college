package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.DepartmentDTO;
import com.example.college.model.entity.Department;
import com.example.college.repository.DepartmentRepository;
import com.example.college.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private FacultyRepository facultyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Department createDepartment(DepartmentDTO departmentDTO) {
        if (!facultyRepository.existsById(departmentDTO.facultyId())){
            throw new ResourceNotFoundException(String.format("Faculty with id %s does not exist!", departmentDTO.facultyId()));
        }
        if (departmentRepository.existsByName(departmentDTO.name())) {
            throw new ResourceAlreadyExistsException(String.format("Department with name %s already exists!", departmentDTO.name()));
        }
        Department department = new Department(departmentDTO);
        return departmentRepository.save(department);
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(String name) {
        Department department = departmentRepository.findByName(name);
        if (department == null) {
            throw new ResourceNotFoundException(String.format("Department with name %s does not exist!", name));
        }
        return departmentRepository.findByName(name);
    }

    public void deleteDepartment(Long id) {
        if (departmentRepository.existsById((id))) {
            departmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Department with id %s does not exist!", id));
        }
    }

    @Transactional
    public void deleteDepartment(String name) {
        if (departmentRepository.existsByName(name)) {
            departmentRepository.deleteByName(name);
        } else {
            throw new ResourceNotFoundException(String.format("Department with name %s does not exist!", name));
        }
    }

    public Department editDepartment(Long id, DepartmentDTO departmentDTO) {
        if (departmentRepository.existsById(id)) {
            Department department = new Department(departmentDTO);
            department.setId(id);
            return departmentRepository.save(department);
        } else {
            throw new ResourceNotFoundException(String.format("Department with id %s does not exist!", id));
        }
    }
}
