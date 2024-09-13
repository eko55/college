package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CourseAccreditationDTO;
import com.example.college.model.dto.GetCoursesAccreditationsResponse;
import com.example.college.model.entity.CourseAccreditation;
import com.example.college.service.CourseAccreditationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Courses Accreditations")
@RestController
@RequestMapping("/coursesAccreditations")
public class CourseAccreditationController {

    private CourseAccreditationService courseAccreditationService;

    public CourseAccreditationController(CourseAccreditationService courseAccreditationService) {
        this.courseAccreditationService = courseAccreditationService;
    }

    @PostMapping()
    public ResponseEntity<CourseAccreditation> createCourseAccreditation(@RequestBody CourseAccreditationDTO requestBody, UriComponentsBuilder builder) {
        CourseAccreditation courseAccreditation;
        try {
            courseAccreditation = courseAccreditationService.createCourseAccreditation(requestBody);
        } catch (ResourceAlreadyExistsException | ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newCourseAccreditationLocation = builder
                .path("courseAccreditation/{id}")
                .buildAndExpand(courseAccreditation.getId())
                .toUri();
        return ResponseEntity.created(newCourseAccreditationLocation).body(courseAccreditation);
    }

    @GetMapping()
    public ResponseEntity<List<GetCoursesAccreditationsResponse>> getCourseAccreditations(@RequestParam(required = false) Long teacherId) {
        if (teacherId == null) {
            List<CourseAccreditation> teacherCoursesAccreditations = courseAccreditationService.getTeacherCoursesAccreditations();
            return ResponseEntity.ok().body(courseAccreditationService.prepareGetCoursesAccreditationsResponse(teacherCoursesAccreditations));
        }
        List<CourseAccreditation> teacherCoursesAccreditations = courseAccreditationService.getTeacherCoursesAccreditations(teacherId);
        return ResponseEntity.ok().body(courseAccreditationService.prepareGetCoursesAccreditationsResponse(teacherCoursesAccreditations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseAccreditation> getCourseAccreditation(@PathVariable Long id) {

        CourseAccreditation courseAccreditation;
        try {
            courseAccreditation = courseAccreditationService.getCourseAccreditation(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(courseAccreditation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseAccreditation(@PathVariable Long id) {

        try {
            courseAccreditationService.deleteCourseAccreditation(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseAccreditation> editCourseAccreditation(@PathVariable Long id, @RequestBody CourseAccreditationDTO courseAccreditationDTO) {
        CourseAccreditation courseAccreditation;
        try {
            courseAccreditation = courseAccreditationService.editCourseAccreditation(id, courseAccreditationDTO);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(courseAccreditation);
    }
}
