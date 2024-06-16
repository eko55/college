package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.HeadOfDepartmentDTO;
import com.example.college.model.entity.HeadOfDepartment;
import com.example.college.service.HeadOfDepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Head Of Department")
@RestController
@RequestMapping("/headOfDepartment")
public class HeadOfDepartmentController {

    private HeadOfDepartmentService headOfDepartmentService;

    public HeadOfDepartmentController(HeadOfDepartmentService headOfDepartmentService) {
        this.headOfDepartmentService = headOfDepartmentService;
    }

    @PostMapping()
    public ResponseEntity<HeadOfDepartment> createHeadOfDepartment(@RequestBody HeadOfDepartmentDTO requestBody, UriComponentsBuilder builder) {
        HeadOfDepartment headOfDepartment;
        try {
            headOfDepartment = headOfDepartmentService.createHeadOfDepartment(requestBody);
        } catch (ResourceAlreadyExistsException | ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newHeadOfDepartmentLocation = builder
                .path("headOfDepartment/{id}")
                .buildAndExpand(headOfDepartment.getId())
                .toUri();
        return ResponseEntity.created(newHeadOfDepartmentLocation).body(headOfDepartment);
    }

    @GetMapping()
    public ResponseEntity<List<HeadOfDepartment>> getFaculties() {
        return ResponseEntity.ok().body(headOfDepartmentService.getHeadOfDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeadOfDepartment> getHeadOfDepartment(@PathVariable Long id) {

        HeadOfDepartment headOfDepartment;
        try {
            headOfDepartment = headOfDepartmentService.getHeadOfDepartment(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(headOfDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeadOfDepartment(@PathVariable Long id) {

        try{
            headOfDepartmentService.deleteHeadOfDepartment(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeadOfDepartment> editHeadOfDepartment(@PathVariable Long id, @RequestBody HeadOfDepartmentDTO headOfDepartmentDTO){
        HeadOfDepartment headOfDepartment;
        try{
            headOfDepartment= headOfDepartmentService.editHeadOfDepartment(id, headOfDepartmentDTO);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(headOfDepartment);
    }
}
