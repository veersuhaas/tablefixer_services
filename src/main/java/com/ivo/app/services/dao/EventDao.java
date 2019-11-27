package com.ivo.app.services.dao;

import com.ivo.app.services.domain.PublicEventResponse;
import com.ivo.app.services.domain.UpdateEventRequest;
import com.ivo.app.services.domain.UserEventsServiceRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface EventDao {

    int updateEvent(UpdateEventRequest event, String userUUID);

    boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime localDateTime, String userUUID, String eventUUID);

    Integer getUserEventsCountByDate(String userUUID, LocalDateTime eventStartTime,String eventUUID);

    List<PublicEventResponse> getMyEvents(UserEventsServiceRequest request, String userUUID, Pageable pageable);
}
