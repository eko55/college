package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.StudentDTO;
import com.example.college.model.entity.Student;
import com.example.college.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final String STUDENT_NOT_EXISTS = "Student with id %s does not exist!";

    private StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByFacultyNumber(studentDTO.facultyNumber())) {
            throw new ResourceAlreadyExistsException(String.format("Student with personal number %s already exists!",studentDTO.facultyNumber()));
        }

        Student student = new Student(studentDTO);
        return studentRepository.save(student);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format(STUDENT_NOT_EXISTS, id));
        }

        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Long id){
        if(studentRepository.existsById((id))){
            studentRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(String.format(STUDENT_NOT_EXISTS,id));
        }
    }

    public Student editStudent(Long id, StudentDTO studentDTO){
        if(studentRepository.existsById(id)){
            Student student = new Student(studentDTO);
            student.setId(id);
            return studentRepository.save(student);
        } else{
            throw new ResourceNotFoundException(String.format(STUDENT_NOT_EXISTS,id));
        }
    }
}
