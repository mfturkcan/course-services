package com.mft.courseservice.service;

import com.mft.courseservice.model.Course;
import com.mft.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mfturkcan on 8.05.2022
 **/

@Service
public class CourseServiceImpl implements CourseService{
    
    private final CourseRepository courseRepository;
    
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Override
    public Course saveCourse(Course course){
        course.setCreateTime(LocalDateTime.now());
        
        courseRepository.save(course);
        return course;
    }
    
    @Override
    public void deleteCourse(long courseId){
        courseRepository.deleteById(courseId);
    }
    
    @Override
    public List<Course> findAll(){
        return courseRepository.findAll();
    }
}