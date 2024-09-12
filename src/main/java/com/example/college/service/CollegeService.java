package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CollegeDTO;
import com.example.college.model.entity.College;
import com.example.college.repository.CollegeRepository;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {

    private CollegeRepository collegeRepository;

    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }


    public College getCollege() {
        if (!collegeExists()) {
            throw new ResourceNotFoundException("College is not created yet!");
        }
        return collegeRepository.findAll().get(0);
    }

    public void deleteCollege() {
        if (collegeExists()) {
            collegeRepository.deleteAll();
        } else {
            throw new ResourceNotFoundException("College is not created yet!");
        }
    }

    public College editCollege(CollegeDTO collegeDTO) {
        if (collegeExists()) {
            College ogCollege = getCollege();
            College college = new College(collegeDTO);
            college.setId(ogCollege.getId());
            return collegeRepository.save(college);
        } else {
            throw new ResourceNotFoundException("College is not created yet!");
        }
    }

    public College createCollege(CollegeDTO requestBody) {

        if (collegeExists()) {
            throw new ResourceAlreadyExistsException("Only one college is allowed!");
        }

        College college = new College(requestBody);
        return collegeRepository.save(college);
    }

    private boolean collegeExists() {
        return !collegeRepository.findAll().isEmpty();
    }
}
