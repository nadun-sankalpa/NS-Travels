package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.UserDTO;
import com.example.ns_travels.entity.User;
import com.example.ns_travels.repository.UserRepo;
import com.example.ns_travels.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepo.save(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        Optional<User> optUser = userRepo.findById(userDTO.getId());
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userDTO.getId());
        }

        User user = modelMapper.map(userDTO, User.class);
        userRepo.save(user);
    }

    @Override
    public void delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        return modelMapper.map(user.get(), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        List<User> users = userRepo.findByRole(role);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return modelMapper.map(user.get(), UserDTO.class);
    }
}
