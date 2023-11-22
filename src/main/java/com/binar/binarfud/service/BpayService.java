package com.binar.binarfud.service;

import com.binar.binarfud.model.Bpay;
import com.binar.binarfud.repository.BpayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BpayService {

    @Autowired
    private BpayRepository bpayRepository;

    public Bpay create(Bpay bpay){
        return bpayRepository.save(bpay);
    }

    public Iterable<Bpay> findAll(){
        return bpayRepository.findAll();
    }

    @Transactional
    public void transfer(String nopay1, String nopay2, double amount){
        Bpay bpay1 = bpayRepository.findByNopay(nopay1);
        if(bpay1==null){
            throw new RuntimeException("Bpay1 tidak valdiiiii wkwkkwkwkw");
        }
        if(bpay1.getSaldo()<=amount){
            throw new RuntimeException("Saldo anda kurangg xixixix");
        }
        bpay1.setSaldo(bpay1.getSaldo()-amount);
        bpayRepository.save(bpay1);

        Bpay bpay2 = bpayRepository.findByNopay(nopay2);
        if(bpay2==null){
            throw new RuntimeException("Bpay2 tidack valdi");
        }
        bpay2.setSaldo(bpay2.getSaldo()+amount);
        bpayRepository.save(bpay2);
    }
}
