package com.group.libraryapp.controller.user;

import com.group.libraryapp.DTO.user.reponse.UserResponse;
import com.group.libraryapp.DTO.user.request.UserCreateRequest;
import com.group.libraryapp.DTO.user.request.UserUpdateRequest;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userservice;

    public UserController(UserServiceV2 userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest req) {

        userservice.saveUser(req);

    }


    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userservice.getUsers();
    }

    @PutMapping("/user")
    public void updateUsers(@RequestBody UserUpdateRequest req) {

        userservice.updateUser(req);

    }

    @DeleteMapping("/user")
    public void deleteUsers(@RequestParam String name) {

        userservice.deleteuser(name);

    }


}


















