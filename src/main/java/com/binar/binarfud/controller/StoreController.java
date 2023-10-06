package com.binar.binarfud.controller;


import com.binar.binarfud.dto.StoreDTO;
import com.binar.binarfud.model.Store;
import com.binar.binarfud.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("/add/{merchantId}")
    public StoreDTO addStoreToMerchant(@PathVariable Long merchantId, @RequestBody StoreDTO request){
        final Store store = storeService.mapToEntity(request);
        final Store result = storeService.addStore(merchantId, store);
        return storeService.mapToDTO(result);
    }
}
