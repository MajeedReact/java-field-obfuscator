package com.reflection.obfuscate.controller;

import com.reflection.obfuscate.dto.response.UserDTO;
import com.reflection.obfuscate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUserDetails(){
        return ResponseEntity.ok(userService.getUserDetails());
    }
}
