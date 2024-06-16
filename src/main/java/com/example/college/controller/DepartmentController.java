package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.DepartmentDTO;
import com.example.college.model.entity.Department;
import com.example.college.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Departments")
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping()
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO requestBody, UriComponentsBuilder builder) {
        Department department;
        try {
            department = departmentService.createDepartment(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newDepartmentLocation = builder
                .path("departments/{id}")
                .buildAndExpand(department.getId())
                .toUri();
        return ResponseEntity.created(newDepartmentLocation).body(department);
    }

    @GetMapping()
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok().body(departmentService.getDepartments());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Department> getDepartment(@PathVariable String name) {

        Department department;
        try {
            department = departmentService.getDepartment(name);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        try{
            departmentService.deleteDepartment(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> editDepartment(@PathVariable Long id, @RequestBody DepartmentDTO requestBody){
        Department department;
        try{
            department = departmentService.editDepartment(id, requestBody);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(department);
    }
}
