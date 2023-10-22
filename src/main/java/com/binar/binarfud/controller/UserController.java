package com.binar.binarfud.controller;

import com.binar.binarfud.dto.UserDTO;
import com.binar.binarfud.model.Merchant;
import com.binar.binarfud.model.User;
import com.binar.binarfud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserDTO create(@RequestBody UserDTO request){
        final User user = userService.mapToEntity(request);
        final User result = userService.create(user);
        return userService.mapToDto(result);
    }

    @PutMapping("/update/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO request){
        final User user = userService.mapToEntity(request);
        final User result = userService.update(id, user);
        return userService.mapToDto(result);
    }

    @GetMapping("/all")
    public List<UserDTO> findAll(){
        return userService.findAll().stream().map(merchant -> userService.mapToDto(merchant))
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public Page<UserDTO> findAllWithPagination(@PageableDefault Pageable pageable){
        return userService.findAll(pageable).map(merchant -> userService.mapToDto(merchant));
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id){
        return userService.delete(id);
    }

}
