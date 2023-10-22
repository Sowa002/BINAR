package com.binar.binarfud.service;

import com.binar.binarfud.dto.UserDTO;
import com.binar.binarfud.model.User;
import com.binar.binarfud.repository.UserRepository;
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
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        final User result = userRepository.save(user);
        return result;
    }

    @Override
    public User update(Long id, User user) {
        User result = findById(id);
        if (result != null) {
            result.setName (user.getName());
            return userRepository.save(result);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        final User result = findById(id);
        if(result !=null){
            //hard delete
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserDTO mapToDto(User user) {
        return mapper.convertValue(user, UserDTO.class);
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        return mapper.convertValue(userDTO, User.class);
    }
}
