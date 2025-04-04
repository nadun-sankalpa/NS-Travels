package com.example.ns_travels.dto;

import com.example.ns_travels.enums.Role;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Role role;


}