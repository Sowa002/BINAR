package com.binar.binarfud.repository;

import com.binar.binarfud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
