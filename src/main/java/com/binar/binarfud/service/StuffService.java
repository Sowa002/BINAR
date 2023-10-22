package com.binar.binarfud.service;

import com.binar.binarfud.dto.StuffDTO;
import com.binar.binarfud.model.Stuff;

public interface StuffService {

    Stuff addStuff(Long storeId, Stuff stuff);

    Stuff mapToEntity(StuffDTO stuffDTO);
    StuffDTO mapToDTO(Stuff stuff);

}
