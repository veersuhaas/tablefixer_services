package com.ivo.app.services.dao;

import com.ivo.app.services.domain.EventDetailRequest;

import java.time.LocalDateTime;

public interface EventDao {

    int updateEvent(EventDetailRequest event, String userUUID);

    boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime localDateTime, String userUUID);

    boolean isEventWithinUserDailyEventsLimit(String userUUID, LocalDateTime eventStartTime);
}
