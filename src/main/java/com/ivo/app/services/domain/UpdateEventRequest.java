package com.ivo.app.services.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class UpdateEventRequest extends EventDetailRequest implements Serializable {

    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private UUID eventUUID;


    public UUID getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(UUID eventUUID) {
        this.eventUUID = eventUUID;
    }
}
