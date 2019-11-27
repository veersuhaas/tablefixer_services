package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.domain.PublicEventResponse;
import com.ivo.app.services.domain.UpdateEventRequest;
import com.ivo.app.services.domain.UserEventsServiceRequest;
import com.ivo.app.services.util.LocationSearchConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EventDaoImpl implements EventDao {

    private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional
    public int updateEvent(UpdateEventRequest event, String userUUID) {
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
    public boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String userUUID, String eventUUID) {

        Map<String, Object> params = new HashMap<>();
        params.put("userUUID", userUUID);
        params.put("eventStartTime", eventStartTime);
        params.put("eventEndTime", eventEndTime);

        StringBuilder query = new StringBuilder("select count(*) from event_details_trans where organizer_uuid=:userUUID and ((:eventStartTime between event_from_dttm   and event_to_dttm) or (:eventEndTime between event_from_dttm   and event_to_dttm )) and is_event_active=true ");
        if(eventUUID!=null){
            params.put("eventUUID",eventUUID);
            query.append(" and event_uuid <> :eventUUID ");
        }


        Integer existingEventsCount = namedParameterJdbcTemplate.queryForObject(query.toString(), params, Integer.class);
        if (existingEventsCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer getUserEventsCountByDate(String userUUID, LocalDateTime eventStartTime, String eventUUID) {

        Map<String, Object> params = new HashMap<>();
        params.put("userUUID", userUUID);
        params.put("eventStartTime", eventStartTime);

        StringBuilder query = new StringBuilder("select COUNT(*) events_cnt from event_details_trans  where organizer_uuid=:userUUID and TO_CHAR(event_from_dttm, 'YYYY-MM-DD')= TO_CHAR(:eventStartTime, 'YYYY-MM-DD') ");

        if(eventUUID!=null){
            params.put("eventUUID",eventUUID);
            query.append(" and event_uuid <> :eventUUID ");
        }

        return namedParameterJdbcTemplate.queryForObject(query.toString(), params, Integer.class);

    }

    @Override
    public List<PublicEventResponse> getMyEvents(UserEventsServiceRequest request, String userUUID, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("fromLatitude", Double.parseDouble(request.getFromLatitude()));
        params.put("fromLongitude", Double.parseDouble(request.getFromLongitude()));
        params.put("radius", new Float(request.getSearchRadius().toString()));
        params.put("userUUID", userUUID);

        StringBuilder query = new StringBuilder(LocationSearchConstants.QUERY_USER_EVENTS_HEAD);


        if (request.getIntAgeStart() != null && request.getIntAgeStart() > 0 && request.getIntAgeEnd() != null && request.getIntAgeEnd() > 0 && request.getIntAgeEnd() > request.getIntAgeStart()) {
            params.put("intAgeStart", request.getIntAgeStart());
            params.put("intAgeEnd", request.getIntAgeEnd());
            query.append(" and int8range(:intAgeStart,:intAgeEnd) && int8range(trans.event_age_bracket_start_yrs -1,event_age_bracket_end_yrs +1)");
        }
        if (request.getListGenderIds() != null && request.getListGenderIds().length > 0) {
            List<Integer> listGenders = Arrays.stream(request.getListGenderIds()).collect(Collectors.toList());
            params.put("listGenders", listGenders);
            query.append(" and usr.gender_id in (:listGenders)");
        }
        if (request.getPayPrefIds() != null && request.getPayPrefIds().length > 0) {
            List<Integer> listPayPrefIds = Arrays.stream(request.getPayPrefIds()).collect(Collectors.toList());
            params.put("listPayPrefIds", listPayPrefIds);
            query.append(" and trans.pay_share_pref_id in (:listPayPrefIds)");
        }

        if (request.isVisitor()) {
            query.append(" and is_visitor=true");
        }
        query.append(LocationSearchConstants.QUERY_USER_EVENTS_TAIL);

        System.out.println("==>" + query);

        List<PublicEventResponse> listPublicEventResponse = namedParameterJdbcTemplate.query(query.toString() + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize()
                , params, new BeanPropertyRowMapper<>(PublicEventResponse.class));

        return listPublicEventResponse;
    }

}

