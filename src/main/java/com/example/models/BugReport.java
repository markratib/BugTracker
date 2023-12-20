package com.example.models;


import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class BugReport 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private Severity severity;
	@Column
	private String description;
	@Column 
	String resolverNote;
	@Column
	private String osName;
	@Column
	private String osVersion;
	@Column
	private String application;
	@Column(columnDefinition = "Timestamp")
	private Date submitDate;
	//removed cascadetype from this....
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id_fk")
	private BRUser author;
	@Column(columnDefinition = "Timestamp")
	private Date resolveDate;
	//removed cascadetype from this....
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resolver_id_fk")
	private BRUser resolver;
	@Column(columnDefinition = "Timestamp")
	@UpdateTimestamp
	private Date lastUpdated;
	
}
