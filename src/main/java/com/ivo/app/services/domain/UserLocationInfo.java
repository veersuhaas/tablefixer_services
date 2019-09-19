package com.ivo.app.services.domain;

import java.io.Serializable;

public class UserLocationInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409039374798512885L;

	private String longitude;
	
	private String latitude;
	
	private String userBookMarkLocationType; //Home, Office .. etc

	private boolean isGPSLocationenabled;
	
	private String coordinateReference; //GPS or LocationSearch(derived from location search)
	
	private String searchRadiusMiles;

	public String getLongitude() {
		return longitude;
	}
	
	public boolean isGPSLocationenabled() {
		return isGPSLocationenabled;
	}

	public void setGPSLocationenabled(boolean isGPSLocationenabled) {
		this.isGPSLocationenabled = isGPSLocationenabled;
	}

	public String getCoordinateReference() {
		return coordinateReference;
	}

	public void setCoordinateReference(String coordinateReference) {
		this.coordinateReference = coordinateReference;
	}

	public String getSearchRadiusMiles() {
		return searchRadiusMiles;
	}

	public void setSearchRadiusMiles(String searchRadiusMiles) {
		this.searchRadiusMiles = searchRadiusMiles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getUserBookMarkLocationType() {
		return userBookMarkLocationType;
	}

	public void setUserBookMarkLocationType(String userBookMarkLocationType) {
		this.userBookMarkLocationType = userBookMarkLocationType;
	}
	
}
