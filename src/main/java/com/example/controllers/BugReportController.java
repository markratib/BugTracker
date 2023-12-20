package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.BRUser;
import com.example.models.BugReport;
import com.example.models.BugReportForm;
import com.example.services.BugReportService;

@RestController
@RequestMapping(value="/bugreport")
public class BugReportController 
{
	@Autowired
	BugReportService BRService;
	
	@CrossOrigin
	@PostMapping(value="/savereport")
	public ResponseEntity<?> saveReport(@RequestBody BugReportForm form)
	{
		BugReport newReport = BRService.saveReport(form);
		if(newReport != null)
		{
			BRUser author = newReport.getAuthor();
			//remove passowrd from the return object
			author.setPassword("");
			newReport.setAuthor(author);

			return ResponseEntity.status(200).body(newReport);
		}else
		{
			return ResponseEntity.status(400).body("User " + form.getAuthor() + " does not exist.");
		}
		
	}
	
	@CrossOrigin
	@GetMapping(value = "/getreports")
	public ResponseEntity<?> getReports(@RequestParam int pageNum, @RequestParam int size)
	{
		if(pageNum < 1)
		{
			pageNum = 1;
		}
		if(size < 10)
		{
			size = 10;
		}
		Page<BugReport> page = BRService.getReports(pageNum - 1, size);
//		List<BugReport> page = BRService.getReports(pageNum, size).toList();
		System.out.println("page = " + page);
		System.out.println("page.toString = " + page.toString());
		return ResponseEntity.status(200).body(page);
//		return ResponseEntity.status(200).body(BRService.getReports(pageNum, size).toList());
//		return ResponseEntity.status(200).body("Hello :)");
	}
	
	@CrossOrigin
	@GetMapping(value = "/getreport")
	public ResponseEntity<?> getReport(@RequestParam(required = true) int reportId)
	{
		BugReport report = null;
		if(BRService.existsById(reportId))
		{
			report = BRService.getById(reportId);
			
			return ResponseEntity.status(200).body(report);
		}else
		{
			return ResponseEntity.status(400).body("Report ID " + reportId + " not found.");
		}
	}
	
	@CrossOrigin
	@PostMapping(value = "/updatereport")
	public ResponseEntity<?> updateReport(@RequestBody(required = true) BugReport report)
	{
		System.out.println("report id = " + report.getId());
		if(BRService.existsById(report.getId()))
		{
			try 
			{
				BugReport newReport = BRService.updateReport(report);
				return ResponseEntity.status(200).body(newReport);
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return ResponseEntity.status(400).body("Report with ID " + report.getId() + " not found.");
		
	}
	
	
}
