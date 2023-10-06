package com.binar.binarfud.service;

import com.binar.binarfud.dto.MerchantDTO;
import com.binar.binarfud.model.Merchant;
import com.binar.binarfud.repository.MerchantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant create(Merchant merchant) {
        final Merchant result = merchantRepository.save(merchant);
        return result;
    }

    @Override
    public Merchant update(Long id, Merchant merchant) {
        Merchant result = findById(id);
        if (result != null) {
            result.setName (merchant.getName());
            return merchantRepository.save(result);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        final Merchant result = findById(id);
        if(result !=null){
            //hard delete
            merchantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Page<Merchant> findAll(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }

    @Override
    public Merchant findById(Long id) {
        Optional<Merchant> result = merchantRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public MerchantDTO mapToDto(Merchant merchant) {
        return mapper.convertValue(merchant, MerchantDTO.class);
    }

    @Override
    public Merchant mapToEntity(MerchantDTO merchantDTO) {
        return mapper.convertValue(merchantDTO, Merchant.class);
    }
}
