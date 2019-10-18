package com.ivo.app.services.service;

import com.ivo.app.services.domain.EventDetailRequest;

public interface EventService {
    EventDetailRequest saveEvent(EventDetailRequest event, String userUUID);

    EventDetailRequest updateEvent(EventDetailRequest event, String userUUID);
}
