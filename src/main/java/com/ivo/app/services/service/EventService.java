package com.ivo.app.services.service;

import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.UpdateEventRequest;

public interface EventService {
    EventDetailRequest saveEvent(EventDetailRequest event, String userUUID);

    EventDetailRequest updateEvent(UpdateEventRequest event, String userUUID);
}
