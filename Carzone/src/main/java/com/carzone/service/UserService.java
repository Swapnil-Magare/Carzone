package com.carzone.service;

import com.carzone.Enums.Role;
import com.carzone.Enums.Status;
import com.carzone.dto.ResponseStructure;
import com.carzone.dto.UserRequest;
import com.carzone.exception.UserAlreadyExistsException;
import com.carzone.exception.UserInactiveException;
import com.carzone.exception.UserNotAuthorizedException;
import com.carzone.exception.UserNotFoundException;
import com.carzone.model.User;
import com.carzone.repositoy.UserRepository;
import com.carzone.security.JwtUtil;
import com.carzone.service.serviceImpl.UserInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<ResponseStructure<String>> registerUser(UserRequest userRequest) {
        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists!");
        }
        userRequest.setStatus(Status.INACTIVE);
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

        String token = jwtUtil.generateToken(user.getEmail());
        user.setToken(token);
        userRepository.save(user);

        ResponseStructure<String> rs = new ResponseStructure<>(
                HttpStatus.CREATED.value(),"User registered successfully!",token);

        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<ResponseStructure<String>> loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid password!");
        }

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(user, userRequest);

        String token = user.getToken();

        ResponseStructure<String> rs = new ResponseStructure<>(
                HttpStatus.OK.value(),"Login successful",token);

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> getUserById(Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found !!!!"));

        if (user.getStatus() != Status.ACTIVE) {
            throw new UserInactiveException("User is Not ACTIVE !!!!");
        }

        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(user, userRequest);

        ResponseStructure<UserRequest> rs = new ResponseStructure<>(
                HttpStatus.OK.value(), "User fetched successfully", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseStructure<List<UserRequest>>> getAllUsers(String email) {
        User adminUser = userRepository.findByEmail(email).orElseThrow(() -> new UserNotAuthorizedException("Logged in user not found."));

        if (adminUser.getRole() != Role.ADMIN){
            throw new UserNotAuthorizedException("Only Admin can view all users");
        }
        List<User> users = userRepository.findAll();

        List<UserRequest> userRequests = users.stream()
                .map(user -> {
                    UserRequest req = new UserRequest();
                    BeanUtils.copyProperties(user, req);
                    return req;
                })
                .collect(Collectors.toList());

        ResponseStructure<List<UserRequest>> rs = new ResponseStructure<>(HttpStatus.OK.value(), "Users fetched successfully", userRequests);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> updateUserDetails(Integer id, UserRequest updatedUserRequest, String email) {

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        User existingUser = userOpt.get();
        if (!existingUser.getEmail().equals(email)) {
            throw new UserNotFoundException("You are not allowed to update another user's details");
        }
        existingUser.setName(updatedUserRequest.getName());
        existingUser.setEmail(updatedUserRequest.getEmail());
        existingUser.setPhone(updatedUserRequest.getPhone());
        existingUser.setPassword(updatedUserRequest.getPassword());
        User updatedUser = userRepository.save(existingUser);
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(updatedUser, userRequest);

        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.OK.value(), "User details updated successfully", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> updateStatus(Integer id, Status status, String email) {
        User adminUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Logged-in user not found"));

        if (adminUser.getRole() != Role.ADMIN) {
            throw new UserNotAuthorizedException("Only admins can update user statuses!");
        }
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }

        User user = userOpt.get();

        if (user.getRole() == Role.ADMIN) {
            throw new UserNotAuthorizedException("Cannot update another admin's status");
        }

        user.setStatus(status);
        User updatedUser = userRepository.save(user);

        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(updatedUser, userRequest);

        ResponseStructure<UserRequest> rs = new ResponseStructure<>(
                HttpStatus.OK.value(), "Status updated successfully", userRequest);

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<String>> deleteById(Integer id, String email) {
        User adminUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Logged-in user not found"));

        if (adminUser.getRole() != Role.ADMIN) {
            throw new UserNotAuthorizedException("Only admins can delete users!");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        if (user.getRole() == Role.ADMIN) {
            throw new UserNotAuthorizedException("Cannot delete another admin!");
        }

        userRepository.delete(user);

        ResponseStructure<String> rs = new ResponseStructure<>(HttpStatus.OK.value(), "User deleted successfully", "Deleted user with ID: " + id);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseStructure<String>> logoutUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        User user = optionalUser.get();
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);

        ResponseStructure<String> rs = new ResponseStructure<>(HttpStatus.OK.value(), "User logged out successfully", "User ID " + id + " has been logged out");

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
