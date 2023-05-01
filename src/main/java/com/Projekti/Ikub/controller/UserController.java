package com.Projekti.Ikub.controller;

import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.dto.user.UserUpdateDTO;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.mapper.user.UserMapper;
import com.Projekti.Ikub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/admin/{userRole}")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO, @PathVariable String userRole){
       UserDTO dto = userService.registerUser(userDTO,userRole);
       return ResponseEntity.ok(dto);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@RequestBody @Valid UserUpdateDTO userDTO, @PathVariable Integer id){
        User u = userService.findById(id);
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Integer id){
        User u = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(u));
    }

}



