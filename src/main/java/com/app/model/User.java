package com.app.model;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity implements BaseUser {

	@Column(name = "user_name")
	@NotBlank(message = "Name is required")
	private String name;
	@Column(name = "email")
	@NotEmpty(message = "Email is required")
	private String email;

	@NotEmpty(message = "Password is required")
	private String password;

	@NotEmpty(message = "Phone number is required")
	@Pattern(regexp="^(\\+\\d{2}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
			message="Mobile number is invalid")
	private String mobileNo;
	@Transient
	private String imgUtility;
	@Lob
	@Column(name = "profile_image", length = 10000000)
	private byte[] image;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_address_id")
	private Addresses userAddress;

	private String status;// admin can grant and revoked user login

	@OneToMany(mappedBy = "parentUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vehicle> vehicle = new ArrayList<Vehicle>();

	public User() {
		super();
	}

	public User(String name, String email, String password, String mobileNo, String status) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.status = status;
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

	public String getImgUtility() throws UnsupportedEncodingException {

		byte[] encodeBase64 = Base64.encodeBase64(getImage());
		String base64Encoded = new String(encodeBase64, "UTF-8");
		return base64Encoded;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Addresses getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Addresses userAddress) {
		this.userAddress = userAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", mobileNo=" + mobileNo
				+ ", image=" + Arrays.toString(image) + ", userAddress=" + userAddress + ", status=" + status
				+ ", vehicle=" + vehicle + "]";
	}

}
