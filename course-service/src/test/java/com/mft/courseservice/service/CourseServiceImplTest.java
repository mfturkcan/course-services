package com.mft.courseservice.service;

import com.mft.courseservice.CourseServiceApplication;
import com.mft.courseservice.model.Course;
import com.mft.courseservice.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by mfturkcan on 8.05.2022
 **/

@DataJpaTest
@ContextConfiguration(classes = {CourseServiceApplication.class, CourseServiceImpl.class})
class CourseServiceImplTest {
    
    private final CourseService courseService;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    public CourseServiceImplTest (CourseService service){
        this.courseService = service;
    }
    
    @Test
    void saveCourse_emptyTitle_throwsException() {
        Course course = Course.builder()
                        .title(null)
                        .subtitle("subtitle")
                        .price(7.00)
                        .build();
//        assertThrows(Exception.class, () -> );
        assertThrows(Exception.class , ()-> courseService.saveCourse(course));
    }
    
    @Test
    void saveCourse_regularClass(){
        Course course = new Course(null, "Test course", "Test subtitle", 5.30, null );
        
        var id = courseService.saveCourse(course).getId();
        System.out.println(id);
        assertNotNull(id);
    }
    
    @Test
    void deleteCourse_nonexitsId_throwsException() {
        long id = (long) Math.random()*100;
        
        assertThrows(Exception.class, () -> courseService.deleteCourse(id));
    }
    
    @Test
    void deleteCourse_existsId() {
        Course course = new Course(null, "Test course", "Test subtitle", 5.30, null );
        Course savedCourse = courseService.saveCourse(course);
        System.out.println(savedCourse);
        assertDoesNotThrow(() -> courseService.deleteCourse(savedCourse.getId()));
    }
    
    @Test
    void findAll_empty() {
        when(courseRepository.findAll()).thenReturn(List.of());
        
        int courseCount = courseService.findAll().size();
        
        assertEquals(0, courseCount);
    }
    
    @Test
    void findAll() {
        when(courseRepository.findAll()).thenReturn(List.of(
                new Course(null, "Test course", "Test subtitle", 5.30, LocalDateTime.now() ),
                new Course(null, "Test course", "Test subtitle", 5.30, LocalDateTime.now()  )
        ));
    
        int courseCount = courseService.findAll().size();
    
        assertEquals(2, courseCount);
    }
}