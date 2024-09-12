package com.example.college.service;

import com.example.college.model.entity.CourseAccreditation;
import com.example.college.repository.CourseAccreditationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CourseAccreditationService {

    private CourseAccreditationRepository repository;

    public CourseAccreditationService(CourseAccreditationRepository repository) {
        this.repository = repository;
    }

//    public List<CourseAccreditation> getCoursesAccreditations(@RequestParam String courseName, @RequestParam String teacherName) {
//
//    }
}
