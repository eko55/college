package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CourseRegistrationDTO;
import com.example.college.model.entity.CourseRegistration;
import com.example.college.repository.CourseRegistrationRepository;
import com.example.college.repository.CourseRepository;
import com.example.college.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistrationService {

    private CourseRegistrationRepository courseRegistrationRepository;

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    public CourseRegistrationService(CourseRegistrationRepository courseRegistrationRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public CourseRegistration createCourseRegistration(CourseRegistrationDTO courseRegistrationDTO) {
        if (courseRegistrationRepository.existsByCourseIdAndStudentId(courseRegistrationDTO.courseId(), courseRegistrationDTO.studentId())) {
            throw new ResourceAlreadyExistsException(String.format("CourseRegistration with course id %s and student id %s already exists!",courseRegistrationDTO.courseId(),courseRegistrationDTO.studentId()));
        }
        if (!courseRepository.existsById(courseRegistrationDTO.courseId())){
            throw new ResourceNotFoundException(String.format("Course with id %s does not exists!",courseRegistrationDTO.courseId()));
        }
        if(!studentRepository.existsById(courseRegistrationDTO.studentId())){
            throw new ResourceNotFoundException(String.format("Student with id %s already exists!",courseRegistrationDTO.studentId()));
        }

        CourseRegistration courseRegistration = new CourseRegistration(courseRegistrationDTO);
        return courseRegistrationRepository.save(courseRegistration);
    }

    public List<CourseRegistration> getCourseRegistrations(){
        return courseRegistrationRepository.findAll();
    }

    public CourseRegistration getCourseRegistration(Long id) {
        if (!courseRegistrationRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("CourseRegistration with id %s does not exist!", id));
        }

        return courseRegistrationRepository.findById(id).get();
    }

    public void deleteCourseRegistration(Long id){
        if(courseRegistrationRepository.existsById((id))){
            courseRegistrationRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(String.format("CourseRegistration with id %s does not exist!",id));
        }
    }

    public CourseRegistration editCourseRegistration(Long id, CourseRegistrationDTO courseRegistrationDTO){
        if(courseRegistrationRepository.existsById(id)){
            CourseRegistration courseRegistration= new CourseRegistration(courseRegistrationDTO);
            courseRegistration.setId(id);
            return courseRegistrationRepository.save(courseRegistration);
        } else{
            throw new ResourceNotFoundException(String.format("CourseRegistration with id %s does not exist!",id));
        }
    }
}
