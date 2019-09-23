package com.ivo.app.services.domain;

import java.io.Serializable;

public class UserLocation extends Address implements Serializable {

    private Long userAddrId;
    private String userUUID;
    private String userLocationName;
    private Boolean active;
    private Boolean defaultLocation;
    private String longitude;
    private String latitude;

    public Long getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(Long userAddrId) {
        this.userAddrId = userAddrId;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

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
