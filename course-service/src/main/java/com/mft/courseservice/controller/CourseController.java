package com.mft.courseservice.controller;

import com.mft.courseservice.model.Course;
import com.mft.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mfturkcan on 8.05.2022
 **/

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;
    
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(courseService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable long courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}