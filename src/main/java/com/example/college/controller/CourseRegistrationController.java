package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CourseRegistrationDTO;
import com.example.college.model.entity.CourseRegistration;
import com.example.college.service.CourseRegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Courses Registrations")
@RestController
@RequestMapping("/courseRegistration")
public class CourseRegistrationController {

    private CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService = courseRegistrationService;
    }

    @PostMapping()
    public ResponseEntity<CourseRegistration> createCourseRegistration(@RequestBody CourseRegistrationDTO requestBody, UriComponentsBuilder builder) {
        CourseRegistration courseRegistration;
        try {
            courseRegistration = courseRegistrationService.createCourseRegistration(requestBody);
        } catch (ResourceAlreadyExistsException | ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newCourseRegistrationLocation = builder
                .path("courseRegistration/{id}")
                .buildAndExpand(courseRegistration.getId())
                .toUri();
        return ResponseEntity.created(newCourseRegistrationLocation).body(courseRegistration);
    }

    @GetMapping()
    public ResponseEntity<List<CourseRegistration>> getFaculties() {
        return ResponseEntity.ok().body(courseRegistrationService.getCourseRegistrations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseRegistration> getCourseRegistration(@PathVariable Long id) {

        CourseRegistration courseRegistration;
        try {
            courseRegistration = courseRegistrationService.getCourseRegistration(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(courseRegistration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseRegistration(@PathVariable Long id) {

        try{
            courseRegistrationService.deleteCourseRegistration(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseRegistration> editCourseRegistration(@PathVariable Long id, @RequestBody CourseRegistrationDTO courseRegistrationDTO){
        CourseRegistration courseRegistration;
        try{
            courseRegistration= courseRegistrationService.editCourseRegistration(id, courseRegistrationDTO);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(courseRegistration);
    }
}
