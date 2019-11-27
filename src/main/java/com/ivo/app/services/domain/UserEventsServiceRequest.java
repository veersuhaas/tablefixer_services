package com.ivo.app.services.domain;

import javax.validation.constraints.NotEmpty;

public class UserEventsServiceRequest extends PublicEventRequest {

    @NotEmpty
    private String eventStatus;//active, inactive, all

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
}
