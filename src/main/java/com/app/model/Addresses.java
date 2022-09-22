package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address_table")
public class Addresses extends BaseEntity {
	@Column(name = "city", length = 40)
	private String city;
	@Column(name = "district", length = 40)
	private String district;
	@Column(name = "state", length = 40)
	private String state;
	@Column(name = "country", length = 40)
	private String country;
	@Column(name = "street_line", length = 1000)
	private String streetLine;
	@Column(name = "pincode", length = 7)
	private int pinCode;

	public Addresses() {
		super();
	}

	public Addresses(String city, String district, String state, String country, String streetLine, int pinCode) {
		super();
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.streetLine = streetLine;
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetLine() {
		return streetLine;
	}

	public void setStreetLine(String streetLine) {
		this.streetLine = streetLine;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Addresses [city=" + city + ", district=" + district + ", state=" + state + ", country=" + country
				+ ", streetLine=" + streetLine + ", pinCode=" + pinCode + "]";
	}

}
