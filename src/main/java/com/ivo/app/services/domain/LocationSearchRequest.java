package com.ivo.app.services.domain;

import javax.validation.constraints.*;
import java.io.Serializable;

public class LocationSearchRequest implements Serializable{

	private static final long serialVersionUID = -8409039374798512885L;

    private String locationNameSearchString; // Restaurant name

    private String cuisineType; // Cuisine type if any

    @Digits(message = "'longitude' should be a decimal value", fraction = 10, integer = 10)
    private String longitude;

    @Digits(message = "'latitude' should be a decimal value", fraction = 10, integer = 10)
    private String latitude;

//	private String userBookMarkLocationType; //Home, Office .. etc

    private boolean gpsLocationEnabled;

    @NotNull(message = " 'coordinateReference' cannot be null")
    @NotEmpty(message = " 'coordinateReference' cannot be empty")
    private String gpsCoordinateReference; //CURRENT_GPS_LOCATION or MY_PLACES or CUSTOM_GPS_LOCATION // To do write a custom validator to validate these 3 possibilities and the associated fields based on the value

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(500)
    private String searchRadiusMiles;

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getLocationNameSearchString() {
        return locationNameSearchString;
    }

    public void setLocationNameSearchString(String locationNameSearchString) {
        this.locationNameSearchString = locationNameSearchString;
    }

    public boolean isGpsLocationEnabled() {
        return gpsLocationEnabled;
    }

    public void setGpsLocationEnabled(boolean gpsLocationEnabled) {
        this.gpsLocationEnabled = gpsLocationEnabled;
    }

	  public String getLongitude() {
		    return longitude;
	   }

    public String getGpsCoordinateReference() {
        return gpsCoordinateReference;
    }

    public void setGpsCoordinateReference(String gpsCoordinateReference) {
        this.gpsCoordinateReference = gpsCoordinateReference;
    }

    public String getSearchRadiusMiles() {
		    return searchRadiusMiles;
	  }

    public void setSearchRadiusMiles(String searchRadiusMiles) {
      this.searchRadiusMiles = searchRadiusMiles;
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

//	public String getUserBookMarkLocationType() {
//		return userBookMarkLocationType;
//	}
//
//	public void setUserBookMarkLocationType(String userBookMarkLocationType) {
//		this.userBookMarkLocationType = userBookMarkLocationType;
//	}
}
