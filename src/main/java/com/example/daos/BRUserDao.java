package com.example.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.BRUser;

public interface BRUserDao extends JpaRepository <BRUser, Integer>
{
	
	public BRUser getByUsername(String username);
	
	public boolean existsByUsername(String username);
	
}
