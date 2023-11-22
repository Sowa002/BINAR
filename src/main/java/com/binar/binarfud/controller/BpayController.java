package com.binar.binarfud.controller;

import com.binar.binarfud.dto.TransferRequest;
import com.binar.binarfud.model.Bpay;
import com.binar.binarfud.service.BpayService;
import com.sun.deploy.panel.ITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bpay")
public class BpayController {

    @Autowired
    private BpayService service;

    @PostMapping
    public Bpay create(@RequestBody Bpay bpay){
        return service.create(bpay);
    }

    @GetMapping
    public Iterable<Bpay> findAll(){
        return service.findAll();
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest transferRequest){
        service.transfer(transferRequest.getBpay1(), transferRequest.getBpay2(), transferRequest.getAmount());
    }
}
