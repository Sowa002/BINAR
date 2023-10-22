package com.binar.binarfud.dto;

import java.util.Objects;

public class StuffDTO {
    private Long id;
    private String name;

    public StuffDTO() {
        // Konstruktor default
    }

    public StuffDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuffDTO stuffDTO = (StuffDTO) o;
        return Objects.equals(id, stuffDTO.id) &&
                Objects.equals(name, stuffDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "StuffDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
