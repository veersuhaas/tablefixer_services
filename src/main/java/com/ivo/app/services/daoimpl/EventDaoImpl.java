package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.domain.EventDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
