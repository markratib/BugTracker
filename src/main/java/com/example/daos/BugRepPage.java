package com.example.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.models.BugReport;

public interface BugRepPage extends PagingAndSortingRepository<BugReport, Integer>
{

}
