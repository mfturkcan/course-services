package com.mft.gatewayservice.controller;

import com.mft.gatewayservice.request.PurchaseServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mfturkcan on 12.05.2022
 **/

@RestController
@RequestMapping("/gateway/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseServiceRequest purchaseServiceRequest;
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(purchaseServiceRequest.getAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Object purchaseObject){
        return new ResponseEntity(purchaseServiceRequest.save(purchaseObject), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<?> delete(@PathVariable("purchaseId") long purchaseId){
        purchaseServiceRequest.delete(purchaseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}