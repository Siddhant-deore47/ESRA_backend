package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coordinates_hospital")
public class HospitalCoordinates extends BaseEntity {

	private Double latitude;

	private Double longitude;

	public HospitalCoordinates() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public HospitalCoordinates(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
