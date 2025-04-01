package com.example.ns_travels.controller;

import com.example.ns_travels.dto.UserDTO;
import com.example.ns_travels.service.UserService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*")
public class RegistrationController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ResponseUtil> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseUtil(201, "User registered successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseUtil(500, e.getMessage(), null));
        }

    }
}
