package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.FacultyDTO;
import com.example.college.model.entity.Faculty;
import com.example.college.service.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Faculties")
@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping()
    public ResponseEntity<Faculty> createFaculty(@RequestBody FacultyDTO requestBody, UriComponentsBuilder builder) {
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

    @GetMapping("/{name}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable String name) {

        Faculty faculty;
        try {
            faculty = facultyService.getFaculty(name);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(faculty);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {

        Faculty faculty;
        try {
            faculty = facultyService.getFaculty(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(faculty);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
//
//        try{
//            facultyService.deleteFaculty(id);
//        } catch (ResourceNotFoundException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
//        }
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String name) {

        try{
            facultyService.deleteFaculty(name);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody FacultyDTO facultyDTO){
        Faculty faculty;
        try{
            faculty= facultyService.editFaculty(id, facultyDTO);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(faculty);
    }

}
