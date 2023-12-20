package com.example.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.BugReport;

public interface BugRepDao extends JpaRepository<BugReport, Integer>
{
	
}
