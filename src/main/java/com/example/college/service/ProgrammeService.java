package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.ProgrammeDTO;
import com.example.college.model.entity.Programme;
import com.example.college.repository.ProgrammeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {

    private ProgrammeRepository programmeRepository;

    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    public Programme createProgramme(ProgrammeDTO programmeDTO) {
        if (programmeRepository.existsByName(programmeDTO.name())) {
            throw new ResourceAlreadyExistsException(String.format("Programme with name %s already exists!", programmeDTO.name()));
        }
        Programme programme = new Programme(programmeDTO);
        return programmeRepository.save(programme);
    }

    public List<Programme> getFaculties() {
        return programmeRepository.findAll();
    }

    public Programme getProgramme(String name) {
        Programme programme = programmeRepository.findByName(name);
        if (programme == null) {
            throw new ResourceNotFoundException(String.format("Programme with name %s does not exist!", name));
        }
        return programmeRepository.findByName(name);
    }

    public Programme getProgramme(Long id) {
        Optional<Programme> programme = programmeRepository.findById(id);
        if (programme == null) {
            throw new ResourceNotFoundException(String.format("Programme with id %s does not exist!", id));
        }
        return programmeRepository.findById(id).get();
    }

    public void deleteProgramme(Long id) {
        if (programmeRepository.existsById((id))) {
            programmeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Programme with id %s does not exist!", id));
        }
    }

    @Transactional
    //https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
    public void deleteProgramme(String name) {
        if (programmeRepository.existsByName((name))) {
            programmeRepository.deleteByName(name);
        } else {
            throw new ResourceNotFoundException(String.format("Programme with name %s does not exist!", name));
        }
    }

    public Programme editProgramme(Long id, ProgrammeDTO programmeDTO) {
        if (programmeRepository.existsById(id)) {
            Programme programme = new Programme(programmeDTO);
            programme.setId(id);
            return programmeRepository.save(programme);
        } else {
            throw new ResourceNotFoundException(String.format("Programme with id %s does not exist!", id));
        }
    }
}
