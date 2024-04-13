package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.model.Faculty;
import com.example.college.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping()
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty requestBody, UriComponentsBuilder builder) {
        Faculty faculty;
        try {
            faculty = facultyService.createFaculty(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newFacultyLocation = builder
                .path("faculties/{id}")
                .buildAndExpand(faculty.getId())
                .toUri();
        return ResponseEntity.created(newFacultyLocation).body(faculty);
    }

    @GetMapping()
    public ResponseEntity<List<Faculty>> getFaculties() {
        return ResponseEntity.ok().body(facultyService.getFaculties());
    }

}
