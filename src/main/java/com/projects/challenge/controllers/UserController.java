package com.projects.challenge.controllers;

import com.projects.challenge.dtos.UserRequestDTO;
import com.projects.challenge.dtos.UserResponseDTO;
import com.projects.challenge.dtos.ResponseStatus;
import com.projects.challenge.models.User;
import com.projects.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    private List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable(name = "id") int id)  {
        User user = null;
        try {
            user = this.userService.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @PostMapping()
    private UserResponseDTO createUser(@RequestBody UserRequestDTO requestDTO) {
        String name = requestDTO.getName();
        String email = requestDTO.getEmail();
        String phoneNumber = requestDTO.getPhoneNumber();
        UserResponseDTO responseDTO = new UserResponseDTO();
        try {
            User user = this.userService.createUser(name, email, phoneNumber);
            responseDTO.setUser(user);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    @PutMapping("{id}")
    private UserResponseDTO updateUser(@RequestBody UserRequestDTO requestDTO, @PathVariable(name = "id") int userId) {
        String name = requestDTO.getName();
        String email = requestDTO.getEmail();
        String phoneNumber = requestDTO.getPhoneNumber();
        UserResponseDTO responseDTO = new UserResponseDTO();
        try {
            User user = this.userService.updateUser(userId, name, email, phoneNumber);
            responseDTO.setUser(user);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    @DeleteMapping("/{id}")
    private ResponseStatus deleteUser(@PathVariable(name = "id") int id) {
        ResponseStatus responseStatus;
        try {
            this.userService.deleteUser(id);
            responseStatus = ResponseStatus.SUCCESS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseStatus = ResponseStatus.FAILURE;
        }
        return responseStatus;
    }

}
