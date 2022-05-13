package com.mft.purchaseservice.repository;

import com.mft.purchaseservice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mfturkcan on 9.05.2022
 **/

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}