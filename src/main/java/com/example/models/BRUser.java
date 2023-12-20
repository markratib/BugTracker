 package com.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class BRUser 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(unique = true)
	private String username;
	@Column
	private String password;
	@Column(unique = true)
	private String email;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private BRUserRole role;
	
	public BRUser(String username, String password, String email, String firstname, String lastname, BRUserRole role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
	}
}
