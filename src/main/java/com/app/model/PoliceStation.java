package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Entity
@Table(name = "police_station")
public class PoliceStation extends BaseEntity implements BaseUser {

	private String name;
	private String email;
	private String password;
	private String mobileNo;
	private String altMobileNo;
	private String status;
//	@Transient
//	private String imgUtility;
//	@Lob
//	@Column(name = "profile_image", length = 10000000)
//	private byte[] image;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "station_address_id")
	@JsonProperty("policeStationAddress")
	private Addresses policeStationAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cordinate_id")
	@JsonProperty("coordinates")
	private StationCoordinates coordinates;

	public PoliceStation() {
		super();
	}

	public PoliceStation(String name, String email, String password, String mobileNo, String altMobileNo,
			String status) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.altMobileNo = altMobileNo;
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

	public String getAltMobileNo() {
		return altMobileNo;
	}

	public void setAltMobileNo(String altMobileNo) {
		this.altMobileNo = altMobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}

	public Addresses getPoliceStationAddress() {
		return policeStationAddress;
	}

	public void setPoliceStationAddress(Addresses policeStationAddress) {
		this.policeStationAddress = policeStationAddress;
	}

//	public String getImgUtility() throws UnsupportedEncodingException {
//
//		byte[] encodeBase64 = Base64.encodeBase64(getImage());
//		String base64Encoded = new String(encodeBase64, "UTF-8");
//		return base64Encoded;
//	}

	public StationCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(StationCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "PoliceStation [name=" + name + ", email=" + email + ", password=" + password + ", mobileNo=" + mobileNo
				+ ", altMobileNo=" + altMobileNo + ", status=" + status
				+ ", policeStationAddress=" + policeStationAddress + ", coordinates=" + coordinates + "]";
	}

}
