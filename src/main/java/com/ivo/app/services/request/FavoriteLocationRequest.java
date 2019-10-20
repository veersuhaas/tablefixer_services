package com.ivo.app.services.request;


import javax.validation.constraints.*;
import java.io.Serializable;

public class FavoriteLocationRequest implements Serializable{

	private static final long serialVersionUID = -8409039374798512885L;


    @Digits(message = "'longitude' should be a decimal value", fraction = 10, integer = 10)
    private String longitude;

    @Digits(message = "'latitude' should be a decimal value", fraction = 10, integer = 10)
    private String latitude;

    private String gpsCoordinateReference; //CURRENT_GPS_LOCATION or MY_PLACES or CUSTOM_GPS_LOCATION // To do write a custom validator to validate these 3 possibilities and the associated fields based on the value

    private String fromReferenceType; //CURRENT_GPS_LOCATION or MY_PLACES or CUSTOM_GPS_LOCATION // To do write a custom validator to validate these 3 possibilities and the associated fields based on the value

    private String userBookMarkLocationType; //Home, Office .. etc

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(500)
    private String searchRadiusMiles;


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

    public String getGpsCoordinateReference() {
        return gpsCoordinateReference;
    }

    public void setGpsCoordinateReference(String gpsCoordinateReference) {
        this.gpsCoordinateReference = gpsCoordinateReference;
    }

    public String getFromReferenceType() {
        return fromReferenceType;
    }

    public void setFromReferenceType(String fromReferenceType) {
        this.fromReferenceType = fromReferenceType;
    }

    public String getSearchRadiusMiles() {
        return searchRadiusMiles;
    }

    public void setSearchRadiusMiles(String searchRadiusMiles) {
        this.searchRadiusMiles = searchRadiusMiles;
    }

    public String getUserBookMarkLocationType() {
        return userBookMarkLocationType;
    }

    public void setUserBookMarkLocationType(String userBookMarkLocationType) {
        this.userBookMarkLocationType = userBookMarkLocationType;
    }
}
