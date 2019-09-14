package com.ivo.app.services.domain;

import java.io.Serializable;

public class LocationSearchResponse implements Serializable{
	
	private Long locationId;
	
	private String locationUUID;
	
	private String locationName;
	
	private String distance;
	
	private String phoneNumber;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationUUID() {
		return locationUUID;
	}

	public void setLocationUUID(String locationUUID) {
		this.locationUUID = locationUUID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
