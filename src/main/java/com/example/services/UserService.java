package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.daos.BRUserDao;
import com.example.models.BRUser;

@Service
public class UserService 
{
	@Autowired
	BRUserDao UserDao;

	public boolean existById(int userId) 
	{
		return UserDao.existsById(userId);
	}

	public BRUser getUserById(int userId) 
	{
		return UserDao.getReferenceById(userId);
	}

	public List<BRUser> getAllUsers() 
	{
		List<BRUser> userList = UserDao.findAll();
		return userList;
	}
	
	
}
