package com.ivo.app.services.domain;

import java.io.Serializable;

public class EventDetailRequest implements Serializable {

    private String longitude;
    private String latitude;
    private String eventStartTime;
    private String eventEndTime;
    private String locationName;
    private String locationUUID;
    private Integer payPrefId;
    private Integer eventPurposeId;
    private Integer eventGenderPrefId;
    private String userUUID;
    private Integer maxGuestsAllowed;
    private String reservationUnderName;
    private Float eventDurationHrs;
    private Boolean isPrivate;
    private String eventUUID;

    public String getLocationUUID() {
        return locationUUID;
    }

    public void setLocationUUID(String locationUUID) {
        this.locationUUID = locationUUID;
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

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getPayPrefId() {
        return payPrefId;
    }

    public void setPayPrefId(Integer payPrefId) {
        this.payPrefId = payPrefId;
    }

    public Integer getEventPurposeId() {
        return eventPurposeId;
    }

    public void setEventPurposeId(Integer eventPurposeId) {
        this.eventPurposeId = eventPurposeId;
    }

    public Integer getEventGenderPrefId() {
        return eventGenderPrefId;
    }

    public void setEventGenderPrefId(Integer eventGenderPrefId) {
        this.eventGenderPrefId = eventGenderPrefId;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public Integer getMaxGuestsAllowed() {
        return maxGuestsAllowed;
    }

    public void setMaxGuestsAllowed(Integer maxGuestsAllowed) {
        this.maxGuestsAllowed = maxGuestsAllowed;
    }

    public String getReservationUnderName() {
        return reservationUnderName;
    }

    public void setReservationUnderName(String reservationUnderName) {
        this.reservationUnderName = reservationUnderName;
    }

    public Float getEventDurationHrs() {
        return eventDurationHrs;
    }

    public void setEventDurationHrs(Float eventDurationHrs) {
        this.eventDurationHrs = eventDurationHrs;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }
}
