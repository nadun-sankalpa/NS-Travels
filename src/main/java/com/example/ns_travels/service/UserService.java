package com.example.ns_travels.service;

import com.example.ns_travels.dto.AuthTokenDTO;
import com.example.ns_travels.dto.UserDTO;
import com.example.ns_travels.dto.UserLoginDTO;

import java.util.List;

public interface UserService {
    void save(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(Long id);
    UserDTO getUserById(Long id);
    UserDTO findByEmail(String email);
    List<UserDTO> getAllUsers();
    List<UserDTO> getUsersByRole(String role);
    UserDTO getUserByEmail(String email);
    AuthTokenDTO verifyUser(UserLoginDTO userDTO);
}
