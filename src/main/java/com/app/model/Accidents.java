package com.app.model;

import javax.persistence.*;

@Entity
@Table(name = "accident_tbl")
public class Accidents extends BaseEntity {

	private String username;

	private String vehicalNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordinates_id")
	private AccidentCoordinates coordinates;

	@Column(name = "total_passengers")
	private int passengerCount;
	@OneToOne
	@JoinColumn(name = "station_ID")
	private PoliceStation nearestPoliceStation;

	@Column(name = "accident_status")
	private String status;

	public Accidents() {
		super();
	}

	public Accidents(String username, String vehicalNo, AccidentCoordinates coordinates, int passengerCount,
			PoliceStation nearestPoliceStation) {
		super();
		this.username = username;
		this.vehicalNo = vehicalNo;
		this.coordinates = coordinates;
		this.passengerCount = passengerCount;
		this.nearestPoliceStation = nearestPoliceStation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVehicalNo() {
		return vehicalNo;
	}

	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}

	public AccidentCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(AccidentCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	public PoliceStation getNearestPoliceStation() {
		return nearestPoliceStation;
	}

	public void setNearestPoliceStation(PoliceStation nearestPoliceStation) {
		this.nearestPoliceStation = nearestPoliceStation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Accidents [username=" + username + ", vehicalNo=" + vehicalNo + ", coordinates=" + coordinates
				+ ", passengerCount=" + passengerCount + ", nearestPoliceStation=" + nearestPoliceStation + ", status="
				+ status + "]";
	}

}
