package com.carzone.dto;

import com.carzone.Enums.Role;
import com.carzone.Enums.Status;
import lombok.Data;

@Data
public class UserRequest {
    private Integer id;
    private String name;
    private String email;
    private Long phone;
    private Status status;
    private String password;
    private Role role;

}
