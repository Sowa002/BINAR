package com.binar.binarfud.service;

import com.binar.binarfud.dto.StuffDTO;
import com.binar.binarfud.model.Stuff;
import com.binar.binarfud.model.Store;
import com.binar.binarfud.repository.StuffRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StuffServiceImpl implements StuffService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    StoreService storeService;

    @Autowired
    StuffRepository stuffRepository;

    @Override
    public Stuff addStuff(Long storeId, Stuff stuff) {
        final Store store = storeService.findById(storeId);
        if (store !=null) {
            //add store to db
            stuff = stuffRepository.save(stuff);
            if(store.getStuffs() !=null) {
                List<Stuff> stuffs = store.getStuffs();
                stuffs.add(stuff);
                store.setStuffs(stuffs);
            } else {
                store.setStuffs(Collections.singletonList(stuff));
            }
            storeService.create(store);
            return stuff;
        }
        return null;
    }

    @Override
    public Stuff mapToEntity(StuffDTO stuffDTO) {
        return mapper.convertValue(stuffDTO, Stuff.class);
    }

    @Override
    public StuffDTO mapToDTO(Stuff stuff) {
        return mapper.convertValue(stuff, StuffDTO.class);
    }
}
