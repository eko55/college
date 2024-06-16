package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.TeacherDTO;
import com.example.college.model.entity.Teacher;
import com.example.college.repository.DepartmentRepository;
import com.example.college.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private static final String TEACHER_NOT_EXISTS = "Teacher with id %s does not exist!";

    private TeacherRepository teacherRepository;

    private DepartmentRepository departmentRepository;

    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public Teacher createTeacher(TeacherDTO teacherDTO) {
        if (teacherRepository.existsByPersonalNumber(teacherDTO.personalNumber())) {
            throw new ResourceAlreadyExistsException(String.format("Teacher with personal number %s already exists!",teacherDTO.personalNumber()));
        }
        if (!departmentRepository.existsById(teacherDTO.departmentId())){
            throw new ResourceNotFoundException(String.format("Department with id %s does not exists!",teacherDTO.departmentId()));
        }
        Teacher department = new Teacher(teacherDTO);
        return teacherRepository.save(department);
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format(TEACHER_NOT_EXISTS, id));
        }

        return teacherRepository.findById(id).get();
    }

    public void deleteTeacher(Long id){
        if(teacherRepository.existsById((id))){
            teacherRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(String.format(TEACHER_NOT_EXISTS,id));
        }
    }

    public Teacher editTeacher(Long id, TeacherDTO teacherDTO){
        if(teacherRepository.existsById(id)){
            Teacher teacher = new Teacher(teacherDTO);
            teacher.setId(id);
            return teacherRepository.save(teacher);
        } else{
            throw new ResourceNotFoundException(String.format(TEACHER_NOT_EXISTS,id));
        }
    }
}
