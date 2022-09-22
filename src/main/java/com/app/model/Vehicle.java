package com.app.model;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Entity
@Table(name = "vehicle_info")
public class Vehicle extends BaseEntity {
	@Lob
	@Column(name = "profile_image", length = 10000000)
	private byte[] image;
	private String vehicleNo;
	private String type;
	private String name;
	private int capacity;
	@Transient
	private String imgUtility;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User parentUser;

	public Vehicle() {
		super();
	}

	public Vehicle(byte[] image, String vehicleNo, String type, String name, int capacity) {
		super();
		this.image = image;
		this.vehicleNo = vehicleNo;
		this.type = type;
		this.name = name;
		this.capacity = capacity;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getImgUtility() throws UnsupportedEncodingException {

		byte[] encodeBase64 = Base64.encodeBase64(getImage());
		String base64Encoded = new String(encodeBase64, "UTF-8");
		return base64Encoded;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public void setImgUtility(String imgUtility) {
		this.imgUtility = imgUtility;
	}

	@Override
	public String toString() {
		return "vehicle [image=" + Arrays.toString(image) + ", vehicleNo=" + vehicleNo + ", type=" + type + ", name="
				+ name + ", capacity=" + capacity + ", parentUser=" + parentUser + "]";
	}

}
