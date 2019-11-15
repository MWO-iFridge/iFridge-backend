package mwo.repository;

import mwo.entity.Course;
import mwo.entity.Quantity;
import mwo.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
