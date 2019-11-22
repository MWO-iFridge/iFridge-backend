package mwo.controller.rest;

import mwo.entity.Course;
import mwo.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

}
