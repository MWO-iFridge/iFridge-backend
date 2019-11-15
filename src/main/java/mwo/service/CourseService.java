package mwo.service;

import java.util.List;

import mwo.entity.Course;
import mwo.entity.Ingredient;
import mwo.repository.CourseRepository;
import mwo.repository.IngredientRepository;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Course getCourseById(Long id){
        return courseRepository.findById(id).get();
    }
}
