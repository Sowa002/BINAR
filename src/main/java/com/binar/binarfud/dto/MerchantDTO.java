package com.binar.binarfud.dto;

import java.util.List;

public class MerchantDTO {
    private Long id;
    private String name;
    private List<StoreDTO> stores;

    public MerchantDTO() {
        // Default constructor
    }

    public MerchantDTO(Long id, String name, List<StoreDTO> stores) {
        this.id = id;
        this.name = name;
        this.stores = stores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoreDTO> getStores() {
        return stores;
    }

    public void setStores(List<StoreDTO> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "MerchantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stores=" + stores +
                '}';
    }
}
