package com.ivo.app.services.request;

import com.ivo.app.services.domain.Address;

import java.io.Serializable;

public class UserLocationRequest extends  Address implements Serializable  {

    private String userLocationName;
    private Boolean active;
    private Boolean defaultLocation;
    private String longitude;
    private String latitude;


    public String getUserLocationName() {
        return userLocationName;
    }

    public void setUserLocationName(String userLocationName) {
        this.userLocationName = userLocationName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(Boolean defaultLocation) {
        this.defaultLocation = defaultLocation;
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
