package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.PublicEventDao;
import com.ivo.app.services.domain.PublicEventRequest;
import com.ivo.app.services.domain.PublicEventResponse;
import com.ivo.app.services.util.LocationSearchConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PublicEventDaoImpl implements PublicEventDao {

    private static final Logger logger = LogManager.getLogger(PublicEventDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<PublicEventResponse> getOpenEvents(PublicEventRequest request, Pageable pageable) {

        Map<String, Object> params = new HashMap<>();
        params.put("fromLatitude", Double.parseDouble(request.getFromLatitude()));
        params.put("fromLongitude", Double.parseDouble(request.getFromLongitude()));
        params.put("radius", new Float(request.getSearchRadius().toString()));

        StringBuilder query = new StringBuilder(LocationSearchConstants.QUERY_PUBLIC_OPEN_EVENTS_HEAD);


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
        query.append(LocationSearchConstants.QUERY_PUBLIC_OPEN_EVENTS_TAIL);

        List<PublicEventResponse> listPublicEventResponse = namedParameterJdbcTemplate.query(query.toString() + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize()
                , params, new BeanPropertyRowMapper<>(PublicEventResponse.class));

        return listPublicEventResponse;
    }
}
