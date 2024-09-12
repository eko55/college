package com.example.college.controller;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.ProgrammeDTO;
import com.example.college.model.entity.Programme;
import com.example.college.service.ProgrammeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Programmes")
@RestController
@RequestMapping("/programmes")
public class ProgrammeController {

    private ProgrammeService programmeService;

    public ProgrammeController(ProgrammeService programmeService) {
        this.programmeService = programmeService;
    }

    @PostMapping()
    public ResponseEntity<Programme> createProgramme(@RequestBody ProgrammeDTO requestBody, UriComponentsBuilder builder) {
        Programme programme;
        try {
            programme = programmeService.createProgramme(requestBody);
        } catch (ResourceAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        URI newProgrammeLocation = builder
                .path("faculties/{id}")
                .buildAndExpand(programme.getId())
                .toUri();
        return ResponseEntity.created(newProgrammeLocation).body(programme);
    }

    @GetMapping()
    public ResponseEntity<List<Programme>> getFaculties() {
        return ResponseEntity.ok().body(programmeService.getFaculties());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Programme> getProgramme(@PathVariable String name) {

        Programme programme;
        try {
            programme = programmeService.getProgramme(name);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(programme);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Programme> getProgrammeById(@PathVariable Long id) {

        Programme programme;
        try {
            programme = programmeService.getProgramme(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return ResponseEntity.ok().body(programme);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProgramme(@PathVariable String name) {

        try{
            programmeService.deleteProgramme(name);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Programme> editProgramme(@PathVariable Long id, @RequestBody ProgrammeDTO programmeDTO){
        Programme programme;
        try{
            programme= programmeService.editProgramme(id, programmeDTO);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
        return ResponseEntity.ok().body(programme);
    }

}
