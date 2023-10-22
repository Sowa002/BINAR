package com.binar.binarfud.service;

import com.binar.binarfud.dto.StoreDTO;
import com.binar.binarfud.model.Store;

public interface StoreService {

    Store create(Store store);
    Store addStore(Long merchantId, Store store);

    Store mapToEntity(StoreDTO storeDTO);
    StoreDTO mapToDTO(Store store);

    Store findById(Long id);

    }
