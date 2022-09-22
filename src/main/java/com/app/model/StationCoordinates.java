package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Station_Coordinates")
public class StationCoordinates extends BaseEntity {

	private Double latitude;
	private Double longitude;

	public StationCoordinates() {
		super();
	}

	public StationCoordinates(Double latitude, Double longitude) {
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
		return "StationCoordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
