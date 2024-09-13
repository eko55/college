package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CourseDTO;
import com.example.college.model.entity.Course;
import com.example.college.repository.CourseRepository;
import com.example.college.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {

    public static final String COURSE_NOT_FOUND = "Course with id %s does not exist!";

    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;

    public CoursesService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public Course createCourse(CourseDTO courseDTO) {
        if (courseRepository.existsByName(courseDTO.name())) {
            throw new ResourceAlreadyExistsException(String.format("Course with name %s already exists!", courseDTO.name()));
        }
        if (teacherRepository.existsById(courseDTO.teacherId())) {
            throw new ResourceNotFoundException(String.format("Teacher with id %s does not exists!", courseDTO.teacherId()));
        }

        Course course = new Course(courseDTO);
        return courseRepository.save(course);
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCourses(Long teacherId) {
        return courseRepository.findAllByTeacherId(teacherId);
    }

    public Course getCourse(String name) {
        Course course = courseRepository.findByName(name);
        if (course == null) {
            throw new ResourceNotFoundException(String.format("Course with name %s does not exists!", name));
        }
        return courseRepository.findByName(name);
    }

    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with id %s does not exists!", id));
        }
        return course.get();
    }

    public void deleteCourse(Long id) {
        if (courseRepository.existsById((id))) {
            courseRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format(COURSE_NOT_FOUND, id));
        }
    }

    public Course editCourse(Long id, CourseDTO courseDTO) {
        if (courseRepository.existsById(id)) {
            Course course = new Course(courseDTO);
            course.setId(id);
            return courseRepository.save(course);
        } else {
            throw new ResourceNotFoundException(String.format(COURSE_NOT_FOUND, id));
        }
    }
}
