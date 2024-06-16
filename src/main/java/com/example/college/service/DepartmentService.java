package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.DepartmentDTO;
import com.example.college.model.entity.Department;
import com.example.college.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department createDepartment(DepartmentDTO departmentDTO) {
        if (repository.existsByName(departmentDTO.name())) {
            throw new ResourceAlreadyExistsException(String.format("Department with name %s already exists!",departmentDTO.name()));
        }
        Department department = new Department(departmentDTO);
        return repository.save(department);
    }

    public List<Department> getDepartments(){
        return repository.findAll();
    }

    public Department getDepartment(String name){
        Department department = repository.findByName(name);
        if(department == null){
            throw new ResourceNotFoundException(String.format("Department with name %s does not exist!",name));
        }
        return repository.findByName(name);}

    public void deleteDepartment(Long id){
        if(repository.existsById((id))){
            repository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(String.format("Department with id %s does not exist!",id));
        }
    }

    public Department editDepartment(Long id, DepartmentDTO departmentDTO){
        if(repository.existsById(id)){
            Department department= new Department(departmentDTO);
            department.setId(id);
            return repository.save(department);
        } else{
            throw new ResourceNotFoundException(String.format("Department with id %s does not exist!",id));
        }
    }
}
