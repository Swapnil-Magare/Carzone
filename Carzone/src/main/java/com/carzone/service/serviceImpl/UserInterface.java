package com.carzone.service.serviceImpl;

import com.carzone.Enums.Status;
import com.carzone.dto.ResponseStructure;
import com.carzone.dto.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {
    ResponseEntity<ResponseStructure<String>> registerUser(UserRequest userRequest);

    ResponseEntity<ResponseStructure<String>> loginUser(String email, String password);

    ResponseEntity<ResponseStructure<UserRequest>> getUserById(Integer id);

    public ResponseEntity<ResponseStructure<List<UserRequest>>> getAllUsers(String email);

    ResponseEntity<ResponseStructure<UserRequest>> updateUserDetails(Integer id, UserRequest updatedUserRequest, String email);

    ResponseEntity<ResponseStructure<UserRequest>> updateStatus(Integer id, Status status, String email);

    ResponseEntity<ResponseStructure<String>> deleteById(Integer id, String email);

    ResponseEntity<ResponseStructure<String>> logoutUser(Integer id);
}
