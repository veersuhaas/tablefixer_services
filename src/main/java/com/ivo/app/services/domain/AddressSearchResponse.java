package com.ivo.app.services.domain;

import java.io.Serializable;

public class AddressSearchResponse implements Serializable {

    private String name;
    private String county;
    private String state;
    private String zip_codes;
    private String longitude;
    private String latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_codes() {
        return zip_codes;
    }

    public void setZip_codes(String zip_codes) {
        this.zip_codes = zip_codes;
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
