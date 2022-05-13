package com.mft.gatewayservice.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mfturkcan on 12.05.2022
 **/

@FeignClient(
        value = "course-service",
        path = "/api/course",
        // url = "${course-service-url}",
        configuration = FeignConfiguration.class
)
public interface CourseServiceRequest {

    @PostMapping //api/course
    Object saveCourse(@RequestBody Object course);
    
    @DeleteMapping("/{courseId}")
    void deleteCourse(@PathVariable("courseId") long courseId);
    
    @GetMapping
    List<Object> getCourses();
}