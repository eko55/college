package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CourseDTO;
import com.example.college.model.entity.Course;
import com.example.college.service.CoursesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Courses")
@RestController
@RequestMapping("/courses")
public class CoursesController {

    private CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping()
    public ResponseEntity<Course> createCourse(@RequestBody CourseDTO requestBody, UriComponentsBuilder builder) {
        Course course;
        try {
            course = coursesService.createCourse(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newCourseLocation = builder
                .path("courses/{id}")
                .buildAndExpand(course.getId())
                .toUri();
        return ResponseEntity.created(newCourseLocation).body(course);
    }

    @GetMapping()
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok().body(coursesService.getCourses());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Course> getCourse(@PathVariable String name) {

        Course course;
        try {
            course = coursesService.getCourse(name);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        try{
            coursesService.deleteCourse(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> editCourse(@PathVariable Long id, @RequestBody CourseDTO requestBody){
        Course course;
        try{
            course = coursesService.editCourse(id, requestBody);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(course);
    }
}
