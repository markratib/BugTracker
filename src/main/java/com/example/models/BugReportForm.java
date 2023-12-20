package com.example.models;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BugReportForm 
{
	private String severity;
	private String description;
	private String osName;
	private String osVersion;
	private String application;
	private Date submitDate;
	private String author;
	private Date resolveDate;
	private BRUser resolver;
	
	
}
