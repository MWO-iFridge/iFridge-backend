package mwo.service;

import mwo.entity.Course;
import mwo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    public Course getCourseByName(String name){
        return courseRepository.getCourseByName(name);
    }
}
