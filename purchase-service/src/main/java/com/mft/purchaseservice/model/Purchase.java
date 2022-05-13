package com.mft.purchaseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Purchase {
    @Id @GeneratedValue
    private long id;
    
    @Column(name = "user_id", nullable = false)
    private long userId;
    
    @Column(name = "course_id", nullable = false)
    private long courseId;
    
    @Column(name = "course_title", nullable = false)
    private String courseTitle;
    
    @Column(name = "course_price", nullable = false)
    private double coursePrice;
    
    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;
}