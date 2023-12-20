package com.example.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.daos.BRUserDao;
import com.example.daos.BugRepDao;
import com.example.daos.BugRepPage;
import com.example.models.BRUser;
import com.example.models.BugReport;
import com.example.models.BugReportForm;
import com.example.models.Severity;

@Service
public class BugReportService 
{
	@Autowired
	BugRepDao BRDao;
	@Autowired
	BRUserDao BRUDao;
	@Autowired
	BugRepPage BRPage;
	
	public BugReport saveReport(BugReportForm form)
	{
		BugReport newReport = null;
		//check if username exists
		if(BRUDao.existsByUsername(form.getAuthor()))
		{
			newReport = new BugReport();
//			System.out.println("User " + form.getAuthor() + " exists.");
			BRUser author = BRUDao.getByUsername(form.getAuthor());
			Severity newSev;
			//check severity string
			switch(form.getSeverity().toUpperCase())
			{
				case "LOW":
				{
					newSev = Severity.LOW;
					break;
				}
				case "MEDIUM":
				{
					newSev = Severity.MEDIUM;
					break;
				}
				case "HIGH":
				{
					newSev = Severity.HIGH;
					break;
				}
				case "CRITICAL":
				{
					newSev = Severity.CRITICAL;
					break;
				}
				default:
				{
					newSev = Severity.CRITICAL;
				}
			}
			Date sqlDateNow = new Date(System.currentTimeMillis());
			newReport.setSeverity(newSev);
			newReport.setApplication(form.getApplication());
			newReport.setAuthor(author);
			newReport.setDescription(form.getDescription());
			newReport.setOsName(form.getOsName());
			newReport.setOsVersion(form.getOsVersion());
			newReport.setSubmitDate(sqlDateNow);
			newReport = BRDao.save(newReport);
		}
		
		return newReport;
	}
	
	public Page<BugReport> getReports(int pageNum, int size)
	{
		PageRequest pr = PageRequest.of(pageNum, size);
		return BRPage.findAll(pr);
	}

	public boolean existsById(int id) 
	{
		
		return BRDao.existsById(id);
	}

	public BugReport getById(int reportId) 
	{
		return BRDao.getReferenceById(reportId);
		
	}

	public BugReport updateReport(BugReport report) throws Exception
	{
		if(!existsById(report.getId()))
		{
			throw new Exception();
		}
		BugReport oldReport = BRDao.getReferenceById(report.getId());
		if(report.getSubmitDate() == null)
		{
			report.setSubmitDate(oldReport.getSubmitDate());
		}
		if(report.getAuthor() == null)
		{
			report.setAuthor(oldReport.getAuthor());
		}
		if(oldReport.getResolver() != null && report.getResolver() == null)
		{
			report.setResolver(oldReport.getResolver());
		}
		if(oldReport.getResolveDate() != null && report.getResolveDate() == null)
		{
			report.setResolveDate(oldReport.getResolveDate());
		}
		if(oldReport.getOsName() != null && report.getOsName() == null)
		{
			report.setOsName(oldReport.getOsName());
		}
		if(oldReport.getOsVersion() != null && report.getOsVersion() == null)
		{
			report.setOsVersion(oldReport.getOsVersion());
		}
		if(oldReport.getApplication() != null && report.getApplication() == null)
		{
			report.setApplication(oldReport.getApplication());
		}
		if(oldReport.getDescription() != null && report.getDescription() == null)
		{
			report.setDescription(oldReport.getDescription());
		}
		if(oldReport.getSeverity() != null && report.getSeverity() == null)
		{
			report.setSeverity(oldReport.getSeverity());
		}
		
		return BRDao.save(report);
	}
}
