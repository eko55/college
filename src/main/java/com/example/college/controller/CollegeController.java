package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CollegeDTO;
import com.example.college.model.dto.FacultyDTO;
import com.example.college.model.entity.College;
import com.example.college.model.entity.Faculty;
import com.example.college.service.CollegeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "College")
@RestController
@RequestMapping("/college")
public class CollegeController {

    private CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @PostMapping()
    public ResponseEntity<College> createCollege(@RequestBody CollegeDTO requestBody, UriComponentsBuilder builder) {
        College college;
        try {
            college = collegeService.createCollege(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newCollegeResourceLocation = builder
                .path("faculties/{id}")
                .buildAndExpand(college.getId())
                .toUri();
        return ResponseEntity.created(newCollegeResourceLocation).body(college);
    }

    @GetMapping("/{name}")
    public ResponseEntity<College> getCollege(@PathVariable String name) {

        College college;
        try {
            college = collegeService.getCollege(name);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(college);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteCollege(@PathVariable String name) {

        try{
            collegeService.deleteCollege(name);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<College> editCollege(@PathVariable String name, @RequestBody CollegeDTO collegeDTO){
        College college;
        try{
            college = collegeService.editCollege(name, collegeDTO);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(college);
    }
}
