package com.ivo.app.services.service;

import com.ivo.app.services.domain.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    EventDetailsResponse saveEvent(EventDetailRequest event, String userUUID);

    EventDetailsResponse updateEvent(UpdateEventRequest event, String userUUID);

    List<PublicEventResponse> getMyEvents(UserEventsServiceRequest request, String userUUID, Pageable pageable);
}
