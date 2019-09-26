package com.ivo.app.services.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventDetailRequest implements Serializable {

    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String longitude;
    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String latitude;
    @NotNull
    @ApiModelProperty(required = true, example = "2019-09-29 14:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventStartTime;

    @ApiModelProperty(hidden = true)
    private LocalDateTime eventEndTime;
    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String locationUUID;
    @NotNull
    @Min(value = 1)
    @Max(value = 4)
    @ApiModelProperty(required = true)
    private Integer payPrefId;
    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 1)
    @Max(value = 10)
    private Integer eventPurposeId;
    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 1)
    @Max(value = 3)
    private Integer eventGenderPrefId;

    @NotNull
    @ApiModelProperty(required = true)
    private Integer maxGuestsAllowed;

    private String reservationUnderName;

    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 10)
    @Max(value = 24 * 60)
    private Integer eventDurationMinutes;

    @NotNull
    @ApiModelProperty(required = true)
    private Boolean isPrivate;

    //    @ApiModelProperty(hidden = true)
    private UUID eventUUID;

    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String sourceTimeZone;

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

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalDateTime eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getLocationUUID() {
        return locationUUID;
    }

    public void setLocationUUID(String locationUUID) {
        this.locationUUID = locationUUID;
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

    public Integer getEventDurationMinutes() {
        return eventDurationMinutes;
    }

    public void setEventDurationMinutes(Integer eventDurationMinutes) {
        this.eventDurationMinutes = eventDurationMinutes;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public UUID getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(UUID eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getSourceTimeZone() {
        return sourceTimeZone;
    }

    public void setSourceTimeZone(String sourceTimeZone) {
        this.sourceTimeZone = sourceTimeZone;
    }
}