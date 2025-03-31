package com.carzone.service.serviceImpl;

import com.carzone.Enums.Status;
import com.carzone.dto.ResponseStructure;
import com.carzone.dto.UserRequest;
import com.carzone.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInerface {
    ResponseEntity<ResponseStructure<UserRequest>> registerUser(UserRequest userRequest);

    ResponseEntity<ResponseStructure<UserRequest>> loginUser(String email, String password);

    ResponseEntity<ResponseStructure<UserRequest>> getUserById(Integer id);

//    ResponseEntity<ResponseStructure<List<UserRequest>>> getAllUsers();

    ResponseEntity<ResponseStructure<List<User>>> getAllUsers();

    ResponseEntity<ResponseStructure<UserRequest>> updateUserDetails(Integer id, UserRequest updatedUserRequest);

    ResponseEntity<ResponseStructure<UserRequest>> updateStatus(Integer id, Status status);

    ResponseEntity<ResponseStructure<UserRequest>> deleteById(Integer id);

    ResponseEntity<ResponseStructure<UserRequest>> logoutUser(Integer id);
}
