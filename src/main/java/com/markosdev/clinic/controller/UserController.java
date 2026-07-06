package com.markosdev.clinic.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.markosdev.clinic.dto.CreateUserDTO;
import com.markosdev.clinic.dto.UpdateUserDTO;
import com.markosdev.clinic.entity.User;
import com.markosdev.clinic.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
		var userId = userService.createUser(createUserDTO);
		return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listUser(){
		var users = userService.listUser();
		return ResponseEntity.ok(users);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUserById(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO updateUserDTO){
		userService.updateUserById(id, updateUserDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
