package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.TeacherDTO;
import com.example.college.model.entity.Teacher;
import com.example.college.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Teachers")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping()
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO requestBody, UriComponentsBuilder builder) {
        Teacher teacher;
        try {
            teacher = teacherService.createTeacher(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newTeacherLocation = builder
                .path("teachers/{id}")
                .buildAndExpand(teacher.getId())
                .toUri();
        return ResponseEntity.created(newTeacherLocation).body(teacher);
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getTeachers() {
        return ResponseEntity.ok().body(teacherService.getTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {

        Teacher teacher;
        try {
            teacher = teacherService.getTeacher(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {

        try{
            teacherService.deleteTeacher(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> editTeacher(@PathVariable Long id, @RequestBody TeacherDTO requestBody){
        Teacher teacher;
        try{
            teacher = teacherService.editTeacher(id, requestBody);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(teacher);
    }
}
