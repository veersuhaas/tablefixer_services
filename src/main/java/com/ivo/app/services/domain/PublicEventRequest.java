package com.ivo.app.services.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Digits;

public class PublicEventRequest {

    @ApiModelProperty(required = true)
    @Digits(message = "'fromLongitude' should be a decimal value", fraction = 10, integer = 10)
    private String fromLongitude;

    @ApiModelProperty(required = true)
    @Digits(message = "'fromLatitude' should be a decimal value", fraction = 10, integer = 10)
    private String fromLatitude;

    private Integer[] listGenderIds;

    private Integer intAgeStart;

    private Integer intAgeEnd;

    private Integer[] payPrefIds;

    private Integer searchRadius;

    private boolean visitor;

    public String getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(String fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public String getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(String fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public Integer[] getListGenderIds() {
        return listGenderIds;
    }

    public void setListGenderIds(Integer[] listGenderIds) {
        this.listGenderIds = listGenderIds;
    }

    public Integer getIntAgeStart() {
        return intAgeStart;
    }

    public void setIntAgeStart(Integer intAgeStart) {
        this.intAgeStart = intAgeStart;
    }

    public Integer getIntAgeEnd() {
        return intAgeEnd;
    }

    public void setIntAgeEnd(Integer intAgeEnd) {
        this.intAgeEnd = intAgeEnd;
    }

    public Integer[] getPayPrefIds() {
        return payPrefIds;
    }

    public void setPayPrefIds(Integer[] payPrefIds) {
        this.payPrefIds = payPrefIds;
    }

    public Integer getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(Integer searchRadius) {
        this.searchRadius = searchRadius;
    }

    public boolean isVisitor() {
        return visitor;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }
}
