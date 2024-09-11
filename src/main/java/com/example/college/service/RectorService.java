package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.RectorDTO;
import com.example.college.model.dto.TeacherDTO;
import com.example.college.model.entity.Rector;
import com.example.college.model.entity.Teacher;
import com.example.college.repository.DepartmentRepository;
import com.example.college.repository.RectorRepository;
import com.example.college.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RectorService {

    private static final String RECTOR_NOT_EXISTS = "No rector is currently in service!";

    private RectorRepository repository;

    public RectorService(RectorRepository repository) {
        this.repository = repository;
    }

    public Rector createRector(RectorDTO rectorDTO) {
        if (repository.findAll().size() > 0) {
            throw new ResourceAlreadyExistsException(String.format("Only one rector is allowed!"));
        }
        Rector rector = new Rector(rectorDTO);
        return repository.save(rector);
    }

    public Rector getRector() {
        if (repository.findAll().size() == 0) {
            throw new ResourceNotFoundException(RECTOR_NOT_EXISTS);
        }

        return repository.findAll().get(0);
    }

    @Transactional
//https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
    public void deleteRector() {
        if (repository.findAll().size() > 0) {
            repository.deleteRector();
        } else {
            throw new ResourceNotFoundException(RECTOR_NOT_EXISTS);
        }
    }

    //    @Transactional //https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
//    public void deleteTeacher(String personalNumber) {
//        if (teacherRepository.existsByPersonalNumber((personalNumber))) {
//            teacherRepository.deleteByPersonalNumber(personalNumber);
//        } else {
//            throw new ResourceNotFoundException(String.format(TEACHER_NOT_EXISTS, personalNumber));
//        }
//    }
//
//    public Teacher editTeacher(Long id, TeacherDTO teacherDTO) {
//        if (teacherRepository.existsById(id)) {
//            Teacher teacher = new Teacher(teacherDTO);
//            teacher.setId(id);
//            return teacherRepository.save(teacher);
//        } else {
//            throw new ResourceNotFoundException(String.format(TEACHER_NOT_EXISTS, id));
//        }
//    }
    public Rector editRector(RectorDTO rectorDTO) {
        if (rectorExists()) {
            Rector ogRector = getRector();
            Rector newRector = new Rector(rectorDTO);
            newRector.setId(ogRector.getId());
            return repository.save(newRector);
        } else {
            throw new ResourceNotFoundException(RECTOR_NOT_EXISTS);
        }
    }

    public boolean rectorExists() {
        return repository.findAll().size() > 0;
    }
}
