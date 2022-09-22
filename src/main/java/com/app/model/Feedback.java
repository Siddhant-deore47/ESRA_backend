package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_tbl")
public class Feedback extends BaseEntity {

	private String name;
	private String email;
	private String mobile;
	@Column(name = "feedback", length = 100000)
	private String comment;

	public Feedback() {
		super();
	}

	public Feedback(String name, String email, String mobile, String comment) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.comment = comment;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Feedback [name=" + name + ", email=" + email + ", mobile=" + mobile + ", comment=" + comment + "]";
	}

}
