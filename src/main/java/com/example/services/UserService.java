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
	BRUserDao userDao;

	public boolean existById(int userId) 
	{
		return userDao.existsById(userId);
	}

	public BRUser getUserById(int userId) 
	{
		return userDao.getReferenceById(userId);
	}

	public List<BRUser> getAllUsers() 
	{
		List<BRUser> userList = userDao.findAll();
		return userList;
	}
	
	public BRUser saveUser(BRUser newUser)
	{
		if(newUser == null)
		{
			return null;
		}else
		{
			return userDao.save(newUser);
			
		}
	}
	
}
