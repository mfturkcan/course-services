package com.mft.gatewayservice.controller;

import com.mft.gatewayservice.request.CourseServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mfturkcan on 12.05.2022
 **/

@RestController
@RequestMapping("/gateway/course")
public class CourseController {
    
    @Autowired
    private CourseServiceRequest courseServiceRequest;
    
    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody Object course){
        return new ResponseEntity(courseServiceRequest.saveCourse(course), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") long courseId){
        courseServiceRequest.deleteCourse(courseId);
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<?> getCourses(){
        return new ResponseEntity(courseServiceRequest.getCourses(), HttpStatus.OK);
    }
}