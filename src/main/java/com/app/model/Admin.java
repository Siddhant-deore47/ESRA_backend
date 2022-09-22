package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends BaseEntity implements BaseUser {

	@Column(name = "admin_name", length = 100)
	private String name;
	@Column(name = "admin_email", length = 100)
	private String email;

	private String password;

	private String mobile;

	public Admin() {
		System.out.println("in const " + getClass().getName());
	}

	public Admin(String name, String email, String password, String mobile) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Admin [name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile + "]";
	}

}
