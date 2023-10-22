package com.binar.binarfud.controller;


import com.binar.binarfud.dto.StuffDTO;
import com.binar.binarfud.model.Stuff;
import com.binar.binarfud.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stuff")
public class StuffController {

    @Autowired
    StuffService stuffService;

    @PostMapping("/add/{storeId}")
    public StuffDTO addStuffToStore(@PathVariable Long storeId, @RequestBody StuffDTO request){
        final Stuff stuff = stuffService.mapToEntity(request);
        final Stuff result = stuffService.addStuff(storeId, stuff);
        return stuffService.mapToDTO(result);
    }
}
