package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.daos.BRUserDao;
import com.example.daos.BugRepDao;
import com.example.models.BRUser;
import com.example.models.BRUserRole;
import com.example.models.BugReport;
import com.example.models.Severity;

@SpringBootApplication
public class BugTracker3Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BugTracker3Application.class, args);
	}
	
	@Autowired
	BugRepDao BRDao;
	@Autowired
	BRUserDao BRUDao;
	
	@Override
	public void run(String... args) throws Exception 
	{
		System.out.println("Hello :).\nCreating BugReport Object...");
		
		Severity sev = Severity.LOW;
		String desc = "This is an example";
		String[] appList = {"app1", "app2", "app3"};
		String[] osList = {"os1", "os2", "os3"};
		String[] osvList = {"1.0", "1.1", "1.2"};
		List<BRUser> userList = new ArrayList<BRUser>();
		List<BugReport> reportList = new ArrayList<BugReport>();
		BRUser user = new BRUser("merk", "123", "me@me.com", "john", "smit", BRUserRole.ADMIN);
		BRUser dupUser1 = new BRUser("merk", "123", "me@me.com", "john", "smit", BRUserRole.ADMIN);
		BRUser dupUser2 = new BRUser("merk", "123", "me1@me.com", "joan", "smit", BRUserRole.ADMIN);
		BRUser user1 = new BRUser("merk1", "123", "me1@me.com", "john", "smit", BRUserRole.USER);
		BRUser user2 = new BRUser("merk2", "123", "me2@me.com", "bill", "smit", BRUserRole.USER);
		BRUser user3 = new BRUser("merk3", "123", "me3@me.com", "timmy", "smit", BRUserRole.MANAGER);
		BRUser user4 = new BRUser("merk4", "123", "me4@me.com", "joan", "smit", BRUserRole.MANAGER);
		
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
//		userList.add(dupUser1);
//		userList.add(dupUser2);

		for(BRUser item: userList)
		{
			BRUDao.save(item);
		}
		
		userList = BRUDao.findAll();
		Random rng = new Random();
		
//		BugReport newReport = new BugReport();
//		newReport.setSeverity(sev);
//		newReport.setDescription(desc);
//		newReport.setApplication(appList[rng.nextInt(3)]);
//		newReport.setOsName(osList[rng.nextInt(3)]);
//		newReport.setOsVersion(osvList[rng.nextInt(3)]);
//		newReport.setAuthor(user);
		for(int i = 0; i < 50; i++)
		{
			BugReport newReport = initBugReport(sev, 
					desc, 
					appList[rng.nextInt(3)], 
					osList[rng.nextInt(3)], 
					osvList[rng.nextInt(3)], 
					userList.get(rng.nextInt(userList.size())));
			
			if(newReport != null)
			{
				System.out.println("New BugReport = " + newReport.toString() + "\nSaving...");
				newReport = BRDao.save(newReport);
				System.out.println("New BugReport post save  = " + newReport.toString());
				System.out.println("Count = " + BRDao.count());
			}else
			{
				System.out.println("New Bug Report not set...");
			}
		}

		
		
	}
	
	public static BugReport initBugReport(Severity sev, String desc, String app, String os, String osv, BRUser user)
	{
		Date dateNow = new Date(System.currentTimeMillis());
//		Timestamp dateNow = new Timestamp(System.currentTimeMillis());
		BugReport newReport = new BugReport();
		newReport.setSeverity(sev);
		newReport.setDescription(desc);
		newReport.setApplication(app);
		newReport.setOsName(os);
		newReport.setOsVersion(osv);
		newReport.setSubmitDate(dateNow);
		newReport.setAuthor(user);
		return newReport;
	}
}
