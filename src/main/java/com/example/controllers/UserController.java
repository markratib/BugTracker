package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.BRUser;
import com.example.services.UserService;

@RestController
@RequestMapping(value="/BRuser")
public class UserController 
{
	@Autowired
	UserService uService;
	
	@CrossOrigin
	@GetMapping()
	public ResponseEntity<String> defaultFunc()
	{
		return ResponseEntity.status(200).body("This is a body :).");
	}
	
	@GetMapping(value="/byId")
	public ResponseEntity<?> getUserById(@RequestParam int userId)
	{
		BRUser user = null;
		if(uService.existById(userId))
		{
			user = uService.getUserById(userId);
			return ResponseEntity.status(200).body(user);
		}else
		{
			return ResponseEntity.status(400).body("User with ID " + userId + " not found");
		}
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<?> getAllUsers()
	{
		List<BRUser> userList = uService.getAllUsers();
		
		return ResponseEntity.status(200).body(userList);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> saveUser(@RequestBody BRUser newUser)
	{
		BRUser copy = uService.saveUser(newUser);
		if(copy == null)
		{
			return ResponseEntity.status(400).body("Something went wrong.");
		}
		
		return ResponseEntity.status(200).body(copy);
	}
}
