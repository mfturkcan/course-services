package com.mft.purchaseservice.controller;

import com.mft.purchaseservice.model.Purchase;
import com.mft.purchaseservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    
    private final PurchaseService purchaseService;
    
    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(purchaseService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Purchase purchase){
        return new ResponseEntity(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<?> delete(@PathVariable long purchaseId){
        purchaseService.deletePurchase(purchaseId);
        
        return ResponseEntity.ok().build();
    }
}