package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.StudentDTO;
import com.example.college.model.entity.Student;
import com.example.college.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Students")
@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO requestBody, UriComponentsBuilder builder) {
        Student student;
        try {
            student = studentService.createStudent(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newStudentLocation = builder
                .path("students/{id}")
                .buildAndExpand(student.getId())
                .toUri();
        return ResponseEntity.created(newStudentLocation).body(student);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {

        Student student;
        try {
            student = studentService.getStudent(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        try{
            studentService.deleteStudent(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody StudentDTO requestBody){
        Student student;
        try{
            student = studentService.editStudent(id, requestBody);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(student);
    }
}
