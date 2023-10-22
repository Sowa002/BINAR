package com.binar.binarfud.repository;

import com.binar.binarfud.model.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuffRepository extends JpaRepository<Stuff, Long> {
}
