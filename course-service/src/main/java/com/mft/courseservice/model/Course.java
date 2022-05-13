package com.mft.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mfturkcan on 8.05.2022
 **/

@Entity
@Table(name = "course")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    
    @Column(name = "subtitle", length = 100, nullable = false)
    private String subtitle;
    
    @Column(name = "price", nullable = false)
    private double price;
    
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
}