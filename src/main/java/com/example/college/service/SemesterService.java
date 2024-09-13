package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.SemesterDTO;
import com.example.college.model.entity.Programme;
import com.example.college.model.entity.Semester;
import com.example.college.repository.SemesterRepository;
import com.example.college.repository.SemesterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {

    private SemesterRepository semesterRepository;

    private ProgrammeService programmeService;

    public SemesterService(SemesterRepository semesterRepository, ProgrammeService programmeService) {
        this.semesterRepository = semesterRepository;
        this.programmeService = programmeService;
    }

    public Semester createSemester(SemesterDTO semesterDTO) {
        if (semesterRepository.existsBySemesterNumberAndProgrammeId(semesterDTO.semesterNumber(), semesterDTO.programme_id())) {
            throw new ResourceAlreadyExistsException(String.format("Semester with semester number %s and programme id already exists!", semesterDTO.semesterNumber(), semesterDTO.programme_id()));
        }
        Semester semester = new Semester(semesterDTO);
        return semesterRepository.save(semester);
    }

    public List<Semester> getSemesters() {
        return semesterRepository.findAll();
    }

    public List<Semester> getSemesters(Long programmeId) {
        return semesterRepository.findAllByProgrammeId(programmeId);
    }

    public List<Semester> getSemesters(String programmeName) {
        Programme programme = programmeService.getProgramme(programmeName);
        return semesterRepository.findAllByProgrammeId(programme.getId());
    }

    public Semester getSemester(Long id) {
        Optional<Semester> semester = semesterRepository.findById(id);
        if (semester == null) {
            throw new ResourceNotFoundException(String.format("Semester with id %s does not exist!", id));
        }
        return semesterRepository.findById(id).get();
    }

    public void deleteSemester(Long id) {
        if (semesterRepository.existsById((id))) {
            semesterRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Semester with id %s does not exist!", id));
        }
    }

//    @Transactional
//    //https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
//    public void deleteSemester(String name) {
//        if (semesterRepository.existsByName((name))) {
//            semesterRepository.deleteByName(name);
//        } else {
//            throw new ResourceNotFoundException(String.format("Semester with name %s does not exist!", name));
//        }
//    }

    public Semester editSemester(Long id, SemesterDTO semesterDTO) {
        if (semesterRepository.existsById(id)) {
            Semester semester = new Semester(semesterDTO);
            semester.setId(id);
            return semesterRepository.save(semester);
        } else {
            throw new ResourceNotFoundException(String.format("Semester with id %s does not exist!", id));
        }
    }
}
