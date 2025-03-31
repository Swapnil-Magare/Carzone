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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements com.carzone.service.serviceImpl.UserInerface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> registerUser(UserRequest userRequest) {
        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with this email already present!");
        }
        userRequest.setStatus(Status.INACTIVE);
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        userRepository.save(user);
        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.CREATED.value(), "User registered successfully!", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found OR Invalid Email !");
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid password !");
        }
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(user, userRequest);
        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.ACCEPTED.value(), "Login Successfully", userRequest
        );
        return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
    }


    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> getUserById(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User Not Found !!!!");
        }
        User user = userOpt.get();
        if (user.getStatus() != Status.ACTIVE) {
            throw new UserInactiveException("User is Not ACTIVE !!!!");
        }
        if (user.getId() != id) {
            throw new UserNotAuthorizedException("Not allowed to fetch other details");
        }
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(user, userRequest);
        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.OK.value(), "User fetched successfully", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
        List<User> users = userRepository.findAll();
        ResponseStructure<List<User>> rs = new ResponseStructure<>(HttpStatus.OK.value(), "All users fetched successfully", users);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> updateUserDetails(Integer id, UserRequest updatedUserRequest) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        User user = userOpt.get();
        if (user.getRole() == Role.ADMIN) {
            throw new UserNotFoundException("Admin cannot change their details through this endpoint");
        }
        user.setName(updatedUserRequest.getName());
        user.setEmail(updatedUserRequest.getEmail());
        user.setPhone(updatedUserRequest.getPhone());
        user.setPassword(updatedUserRequest.getPassword());
        User updatedUser = userRepository.save(user);
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(updatedUser, userRequest);
        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.OK.value(), "User details updated successfully", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> updateStatus(Integer id, Status status) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not Found!!!! inside admin");
        }
        User user = userOpt.get();
        if (user.getRole() == Role.ADMIN) {
            throw new UserNotFoundException("Admin cannot update another admin's status");
        }
        user.setStatus(status);
        User updatedUser = userRepository.save(user);
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(updatedUser, userRequest);
        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.OK.value(), "Status updated successfully", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> deleteById(Integer id) {
        return null;
    }


    @Override
    public ResponseEntity<ResponseStructure<UserRequest>> logoutUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User user = userOpt.get();
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(user, userRequest);
        ResponseStructure<UserRequest> rs = new ResponseStructure<>(HttpStatus.OK.value(), "Logout Successfully", userRequest);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
