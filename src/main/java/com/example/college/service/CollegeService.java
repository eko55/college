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


    public College getCollege(String name) {
        return collegeRepository.findByName(name);
    }

    public void deleteCollege(String name) {
        collegeRepository.deleteByName(name);
    }

    public College editCollege(String name, CollegeDTO collegeDTO) {
        if (collegeRepository.existsByName(name)) {
            College ogCollege = getCollege(name);
            College college = new College(collegeDTO);
            college.setId(ogCollege.getId());
            return collegeRepository.save(college);
        } else {
            throw new ResourceNotFoundException(String.format("College with name %s does not exist!", name));
        }
    }

    public College createCollege(CollegeDTO requestBody) {

        if (collegeRepository.existsByName(requestBody.name())) {
            throw new ResourceAlreadyExistsException(String.format("College with name %s already exists!",requestBody.name()));
        }

        College college = new College(requestBody);
        return collegeRepository.save(college);
    }
}
