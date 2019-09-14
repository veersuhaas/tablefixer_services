package com.ivo.app.services.domain;

import java.io.Serializable;

public class LocationSearchRequest implements Serializable{
	
	private Integer userId;
	
	private String UUID;
	
	private String searchBy;//Home Address or Work Address or any other
	
	private String longitude;
	
	private String latitude;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	

	
}
