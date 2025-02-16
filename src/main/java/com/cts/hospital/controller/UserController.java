package com.cts.hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hospital.model.User;
import com.cts.hospital.service.UserService;

@RestController
@RequestMapping("api/hithro/admin")
public class UserController {	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user){
		userService.addUser(user);
		return "User registered successfully";
	}
	
	
	@GetMapping("/getAll")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("get/{id}")
	public Optional<User> getUserById(@PathVariable long id){
		return userService.getUserById(id);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteUserById(@PathVariable long id){
		userService.deleteUserById(id);
	}
	
	@PutMapping("/update/{id}")
    public String updateUserById(@PathVariable long id, @RequestBody User userUpdateRequest) {
        Optional<User> updatedUser = userService.updateUserById(userUpdateRequest, id);
        if (updatedUser.isPresent()) {
            return "User updated successfully!";
        } else {
            return "User not found!";
        }
    }

}
