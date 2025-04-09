package com.carzone.controller;

import com.carzone.Enums.Status;
import com.carzone.dto.ResponseStructure;
import com.carzone.dto.UserRequest;
import com.carzone.exception.UserNotFoundException;
import com.carzone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ResponseStructure<String>> register(@RequestBody UserRequest userRequest) throws Exception {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<String>> login(@RequestParam String email, @RequestParam String password)
            throws UserNotFoundException {
        return userService.loginUser(email, password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<UserRequest>> getUser(@PathVariable Integer id, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        System.out.println("Authenticated User Email: " + email);
        return userService.getUserById(id);
    }

    //  get All Active and inactive users
    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseStructure<List<UserRequest>>> getAllUsers(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        System.out.println("Authenticated User Email: " + email);
        return userService.getAllUsers(email);
    }

    //    only owner can change details
    @PutMapping("/Update/{id}")
    public ResponseEntity<ResponseStructure<UserRequest>> update(@PathVariable Integer id, @RequestBody UserRequest userRequest, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        System.out.println("Update requested by: " + email);
        return userService.updateUserDetails(id, userRequest, email);
    }

    //    only admin can change status
    @PutMapping("/Update/status")
    public ResponseEntity<ResponseStructure<UserRequest>> updateStatus(@RequestParam Integer id, @RequestParam Status status, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        System.out.println(id + " " + status);
        return userService.updateStatus(id, status, email);
    }

    //    only admin can delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable Integer id, HttpServletRequest request) {
        String email = (String)request.getAttribute("email");
        return userService.deleteById(id, email);
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<ResponseStructure<String>> logout(@PathVariable Integer id) {
        return userService.logoutUser(id);
    }

}
