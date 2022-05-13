package com.mft.gatewayservice.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mfturkcan on 12.05.2022
 **/

@FeignClient(
        value = "purchase-service",
        path = "/api/purchase",
        // url = "${purchase-service-url}",
        configuration = FeignConfiguration.class
)
public interface PurchaseServiceRequest {
    @GetMapping
    List<Object> getAll();
    
    @PostMapping
    Object save(@RequestBody Object object);
    
    @DeleteMapping("/{purchaseId}")
    void delete(@PathVariable("purchaseId") long purchaseId);
}