package com.carzone.controller;

import com.carzone.Enums.Status;
import com.carzone.dto.ResponseStructure;
import com.carzone.dto.UserRequest;
import com.carzone.exception.UserAlreadyExistsException;
import com.carzone.exception.UserNotFoundException;
import com.carzone.model.User;
import com.carzone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserRequest>> register(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<UserRequest>> login(@RequestParam String email, @RequestParam String password)
            throws UserNotFoundException {
        return userService.loginUser(email, password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<UserRequest>> getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseStructure<List<User>>> getAll() {
        return userService.getAllUsers();
    }

    @PutMapping("/Update")
    public ResponseEntity<ResponseStructure<UserRequest>> update(@RequestParam Integer id, @RequestParam UserRequest userRequest) {
        return userService.updateUserDetails(id, userRequest);
    }

    @PutMapping("/Update/status")
    public ResponseEntity<ResponseStructure<UserRequest>> updateStatus(@RequestParam Integer id, @RequestParam Status status) {
        System.out.println(id + " " + status);
        return userService.updateStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<UserRequest>> deleteUser(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseStructure<UserRequest>> logout(@RequestParam Integer id) {
        return userService.logoutUser(id);
    }

}
