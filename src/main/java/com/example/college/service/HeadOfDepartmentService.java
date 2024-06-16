package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.HeadOfDepartmentDTO;
import com.example.college.model.entity.HeadOfDepartment;
import com.example.college.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadOfDepartmentService {

    private static final String HEAD_OF_DEPARTMENT_NOT_EXISTS = "HeadOfDepartment with id %s does not exist!";
    private HeadOfDepartmentRepository headOfDepartmentRepository;

    private TeacherRepository teacherRepository;

    private DepartmentRepository departmentRepository;

    public HeadOfDepartmentService(HeadOfDepartmentRepository headOfDepartmentRepository, TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.headOfDepartmentRepository = headOfDepartmentRepository;
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public HeadOfDepartment createHeadOfDepartment(HeadOfDepartmentDTO headOfDepartmentDTO) {
        if (headOfDepartmentRepository.existsByTeacherIdAndDepartmentId(headOfDepartmentDTO.teacherId(), headOfDepartmentDTO.departmentId())) {
            throw new ResourceAlreadyExistsException(String.format("HeadOfDepartment with teacher id %s and department id %s already exists!",headOfDepartmentDTO.teacherId(),headOfDepartmentDTO.departmentId()));
        }
        if (!teacherRepository.existsById(headOfDepartmentDTO.teacherId())){
            throw new ResourceNotFoundException(String.format("Teacher with id %s does not exists!",headOfDepartmentDTO.teacherId()));
        }
        if(!departmentRepository.existsById(headOfDepartmentDTO.departmentId())){
            throw new ResourceNotFoundException(String.format("Department with id %s already exists!",headOfDepartmentDTO.departmentId()));
        }

        HeadOfDepartment headOfDepartment = new HeadOfDepartment(headOfDepartmentDTO);
        return headOfDepartmentRepository.save(headOfDepartment);
    }

    public List<HeadOfDepartment> getHeadOfDepartments(){
        return headOfDepartmentRepository.findAll();
    }

    public HeadOfDepartment getHeadOfDepartment(Long id) {
        if (!headOfDepartmentRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format(HEAD_OF_DEPARTMENT_NOT_EXISTS, id));
        }

        return headOfDepartmentRepository.findById(id).get();
    }

    public void deleteHeadOfDepartment(Long id){
        if(headOfDepartmentRepository.existsById((id))){
            headOfDepartmentRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(String.format(HEAD_OF_DEPARTMENT_NOT_EXISTS,id));
        }
    }

    public HeadOfDepartment editHeadOfDepartment(Long id, HeadOfDepartmentDTO headOfDepartmentDTO){
        if(headOfDepartmentRepository.existsById(id)){
            HeadOfDepartment headOfDepartment= new HeadOfDepartment(headOfDepartmentDTO);
            headOfDepartment.setId(id);
            return headOfDepartmentRepository.save(headOfDepartment);
        } else{
            throw new ResourceNotFoundException(String.format(HEAD_OF_DEPARTMENT_NOT_EXISTS,id));
        }
    }
}
