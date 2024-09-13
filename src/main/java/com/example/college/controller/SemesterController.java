package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.SemesterDTO;
import com.example.college.model.entity.Semester;
import com.example.college.service.SemesterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Semesters")
@RestController
@RequestMapping("/semesters")
public class SemesterController {

    private SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @PostMapping()
    public ResponseEntity<Semester> createSemester(@RequestBody SemesterDTO requestBody, UriComponentsBuilder builder) {
        Semester semester;
        try {
            semester = semesterService.createSemester(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newSemesterLocation = builder
                .path("semesters/{id}")
                .buildAndExpand(semester.getId())
                .toUri();
        return ResponseEntity.created(newSemesterLocation).body(semester);
    }

    @GetMapping()
    public ResponseEntity<List<Semester>> getSemesters(@RequestParam(required = false) String programmeName) {
        if (programmeName == null) {
            return ResponseEntity.ok().body(semesterService.getSemesters());
        } else {
            return ResponseEntity.ok().body(semesterService.getSemesters(programmeName));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemester(@PathVariable Long id) {

        Semester semester;
        try {
            semester = semesterService.getSemester(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(semester);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable Long id) {

        Semester semester;
        try {
            semester = semesterService.getSemester(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(semester);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
//
//        try{
//            semesterService.deleteSemester(id);
//        } catch (ResourceNotFoundException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
//        }
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {

        try{
            semesterService.deleteSemester(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semester> editSemester(@PathVariable Long id, @RequestBody SemesterDTO semesterDTO){
        Semester semester;
        try{
            semester= semesterService.editSemester(id, semesterDTO);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(semester);
    }

}
