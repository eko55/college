package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.dto.CourseAccreditationDTO;
import com.example.college.model.dto.GetCoursesAccreditationsResponse;
import com.example.college.model.entity.Course;
import com.example.college.model.entity.CourseAccreditation;
import com.example.college.model.entity.CourseAccreditation;
import com.example.college.model.entity.Teacher;
import com.example.college.repository.CourseAccreditationRepository;
import com.example.college.repository.CourseRepository;
import com.example.college.repository.StudentRepository;
import com.example.college.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseAccreditationService {

    private CourseAccreditationRepository courseAccreditationRepository;

    private CoursesService coursesService;

    private TeacherService teacherService;

    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;

    public CourseAccreditationService(CourseAccreditationRepository courseAccreditationRepository, CoursesService coursesService, TeacherService teacherService, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseAccreditationRepository = courseAccreditationRepository;
        this.coursesService = coursesService;
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<CourseAccreditation> getTeacherCoursesAccreditations(Long teacherId) {
        return courseAccreditationRepository.findAllByTeacherId(teacherId);
    }

    public List<CourseAccreditation> getTeacherCoursesAccreditations() {
        return courseAccreditationRepository.findAll();
    }

    public CourseAccreditation createCourseAccreditation(CourseAccreditationDTO courseAccreditationDTO) {
        if (courseAccreditationRepository.existsByCourseIdAndTeacherId(courseAccreditationDTO.courseId(), courseAccreditationDTO.teacherId())) {
            throw new ResourceAlreadyExistsException(String.format("CourseAccreditation with course id %s and teacher id %s already exists!",courseAccreditationDTO.courseId(),courseAccreditationDTO.teacherId()));
        }
        if (!courseRepository.existsById(courseAccreditationDTO.courseId())){
            throw new ResourceNotFoundException(String.format("Course with id %s does not exist!",courseAccreditationDTO.courseId()));
        }
        if(!teacherRepository.existsById(courseAccreditationDTO.teacherId())){
            throw new ResourceNotFoundException(String.format("Teacher with id %s does not exist!",courseAccreditationDTO.teacherId()));
        }

        CourseAccreditation courseAccreditation = new CourseAccreditation(courseAccreditationDTO);
        return courseAccreditationRepository.save(courseAccreditation);
    }

    public List<CourseAccreditation> getCourseAccreditations(){
        return courseAccreditationRepository.findAll();
    }

    public CourseAccreditation getCourseAccreditation(Long id) {
        if (!courseAccreditationRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("CourseAccreditation with id %s does not exist!", id));
        }

        return courseAccreditationRepository.findById(id).get();
    }

    public void deleteCourseAccreditation(Long id){
        if(courseAccreditationRepository.existsById((id))){
            courseAccreditationRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(String.format("CourseAccreditation with id %s does not exist!",id));
        }
    }

    public CourseAccreditation editCourseAccreditation(Long id, CourseAccreditationDTO courseAccreditationDTO){
        if(courseAccreditationRepository.existsById(id)){
            CourseAccreditation courseAccreditation= new CourseAccreditation(courseAccreditationDTO);
            courseAccreditation.setId(id);
            return courseAccreditationRepository.save(courseAccreditation);
        } else{
            throw new ResourceNotFoundException(String.format("CourseAccreditation with id %s does not exist!",id));
        }
    }

    public List<GetCoursesAccreditationsResponse> prepareGetCoursesAccreditationsResponse(List<CourseAccreditation> courseAccreditations) {
        List<GetCoursesAccreditationsResponse> accreditationsResponseList = new ArrayList<>();

        for(CourseAccreditation accreditation : courseAccreditations) {
            Course course = coursesService.getCourse(accreditation.getCourseId());
            Teacher teacher = teacherService.getTeacher(accreditation.getTeacherId());

            GetCoursesAccreditationsResponse response = new GetCoursesAccreditationsResponse(
                    accreditation.getCourseId(),
                    course.getName(),
                    accreditation.getTeacherId(),
                    teacher.getFirstName(),
                    teacher.getLastName());

            accreditationsResponseList.add(response);
        }
        return accreditationsResponseList;
    }
}
