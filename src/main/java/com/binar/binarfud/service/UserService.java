package com.binar.binarfud.service;

import com.binar.binarfud.dto.UserDTO;
import com.binar.binarfud.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User create(User user);
    User update(Long id, User user);
    Boolean delete(Long id);
    List<User>findAll();

    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    UserDTO mapToDto(User user);
    User mapToEntity(UserDTO userDTO);
}
