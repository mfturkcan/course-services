package com.mft.purchaseservice.service;

import com.mft.purchaseservice.model.Purchase;

import java.util.List;

/**
 * Created by mfturkcan on 9.05.2022
 **/

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);
    
    void deletePurchase(long purchaseId);
    
    List<Purchase> findAll();
}