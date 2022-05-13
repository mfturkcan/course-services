package com.mft.purchaseservice.service;

import com.mft.purchaseservice.model.Purchase;
import com.mft.purchaseservice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @Override
    public Purchase savePurchase(Purchase purchase){
        purchase.setPurchaseDate(LocalDateTime.now());
        
        purchaseRepository.save(purchase);
    
        return purchase;
    }
    
    @Override
    public void deletePurchase(long purchaseId){
        purchaseRepository.deleteById(purchaseId);
    }
    
    @Override
    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

}