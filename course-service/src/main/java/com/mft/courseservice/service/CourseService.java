package com.mft.courseservice.service;

import com.mft.courseservice.model.Course;

import java.util.List;

/**
 * Created by mfturkcan on 8.05.2022
 **/

public interface CourseService {
    Course saveCourse(Course course);
    
    void deleteCourse(long courseId);
    
    List<Course> findAll();
}