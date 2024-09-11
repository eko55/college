package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.RectorDTO;
import com.example.college.model.entity.Rector;
import com.example.college.service.RectorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Rector")
@RestController
@RequestMapping("/rector")
public class RectorController {

    private RectorService rectorService;

    public RectorController(RectorService rectorService) {
        this.rectorService = rectorService;
    }

    @PostMapping()
    public ResponseEntity<Rector> createRector(@Valid @RequestBody RectorDTO requestBody, UriComponentsBuilder builder) {
        Rector rector;
        try {
            rector = rectorService.createRector(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        URI newRectorLocation = builder
                .path("rectors/{id}")
                .buildAndExpand(rector.getId())
                .toUri();
        return ResponseEntity.created(newRectorLocation).body(rector);
    }

    @GetMapping()
    public ResponseEntity<Rector> getRector() {

        Rector rector;
        try {
            rector = rectorService.getRector();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(rector);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteRector() {
        try {
            rectorService.deleteRector();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity<Rector> editRector(@RequestBody RectorDTO requestBody) {
        Rector rector;
        try {
            rector = rectorService.editRector(requestBody);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(rector);
    }
}
