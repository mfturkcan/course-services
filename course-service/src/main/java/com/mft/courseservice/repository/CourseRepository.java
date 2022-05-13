package com.mft.courseservice.repository;

import com.mft.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mfturkcan on 8.05.2022
 **/

public interface CourseRepository extends JpaRepository<Course, Long> {
}