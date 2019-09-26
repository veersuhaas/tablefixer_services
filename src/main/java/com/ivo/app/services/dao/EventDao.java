package com.ivo.app.services.dao;

import com.ivo.app.services.domain.EventDetailRequest;

public interface EventDao {

    int updateEvent(EventDetailRequest event, String userUUID);
}
