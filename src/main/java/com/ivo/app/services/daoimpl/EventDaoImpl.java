package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.domain.EventDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional
    public int updateEvent(EventDetailRequest event, String userUUID) {
        Map<String, Object> params = new HashMap<>();
        params.put("eventUUID", event.getEventUUID().toString());
        params.put("maxGuestsAllowed", event.getMaxGuestsAllowed());
        params.put("eventFromDttm", event.getEventStartTime());
        params.put("eventEndDttm", event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        params.put("paySharePrefId", event.getPayPrefId());
        params.put("eventGenderCatId", event.getEventGenderPrefId());
        params.put("eventPurposeId", event.getEventPurposeId());

        return namedParameterJdbcTemplate.update("UPDATE public.event_details_trans " +
                " SET max_guests_allowed= :maxGuestsAllowed, event_from_dttm=:eventFromDttm, event_to_dttm=:eventEndDttm, pay_share_pref_id=:paySharePrefId, " +
                " event_gender_cat_id=:eventGenderCatId, event_purpose_id=:eventPurposeId where event_uuid=:eventUUID", params);

    }

    @Override
    public boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String userUUID) {

        Map<String, Object> params = new HashMap<>();
        params.put("userUUID", userUUID);
        params.put("eventStartTime", eventStartTime);
        params.put("eventEndTime", eventEndTime);

        Integer existingEventsCount = namedParameterJdbcTemplate.queryForObject("select count(*) from event_details_trans where organizer_uuid=:userUUID and ((:eventStartTime between event_from_dttm   and event_to_dttm) or (:eventEndTime between event_from_dttm   and event_to_dttm )) and is_event_active=true ", params, Integer.class);
        if (existingEventsCount != null && existingEventsCount > 0) {
            System.out.println("isEventConflictingAnyOtherExistingEvent -->" + existingEventsCount);
            return true;
        } else {
            System.out.println("isEventConflictingAnyOtherExistingEvent -->" + existingEventsCount);
            return false;
        }
    }

    @Override
    public boolean isEventWithinUserDailyEventsLimit(String userUUID, LocalDateTime eventStartTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("userUUID", userUUID);
        params.put("eventStartTime", eventStartTime);

        int isNewEventAllowed = 0;
        try {
            isNewEventAllowed = namedParameterJdbcTemplate.queryForObject("select daily_events_limit- events_cnt as is_event_creation_allowed " +
                    " from (select userinfo.user_uuid,daily_events_limit,COUNT(*) events_cnt from user_info_ref userinfo, event_details_trans event " +
                    "where userinfo.user_uuid=event.organizer_uuid " +
                    "and organizer_uuid=:userUUID and TO_CHAR(event_from_dttm, 'YYYY-MM-DD')= TO_CHAR(:eventStartTime, 'YYYY-MM-DD') " +
                    "group by userinfo.user_uuid,daily_events_limit) as a", params, Integer.class);
            System.out.println("==)))))))))" + isNewEventAllowed);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        if (isNewEventAllowed > 0) {
            return true;
        } else return false;
    }
}

