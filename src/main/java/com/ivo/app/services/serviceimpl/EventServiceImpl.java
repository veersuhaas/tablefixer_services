package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.domain.UpdateEventRequest;
import com.ivo.app.services.entity.EventDetailsEntity;
import com.ivo.app.services.entity.UserInfoRef;
import com.ivo.app.services.repository.EventDetailsTransRepository;
import com.ivo.app.services.repository.UserInfoRefRepository;
import com.ivo.app.services.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LogManager.getLogger(EventServiceImpl.class);

    @Autowired
    private LocationSearchDao locationSearchDao;

    @Autowired
    private EventDetailsTransRepository eventDetailsTransRepository;

    @Autowired
    private UserInfoRefRepository userInfoRefRepository;

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional
    public EventDetailRequest saveEvent(EventDetailRequest event, String userUUID) {

        EventDetailRequest enrichedEvent = enrichEventDetails(event);

        validateEvent(enrichedEvent, userUUID);

        LocationDetails locationDetails = locationSearchDao.getLocationDetailsByLocationUUID(event.getLocationUUID());

        EventDetailsEntity eventDetailsEntity = setEventDetails(event, userUUID, locationDetails);
        EventDetailsEntity savedEventDetailsEntity = null;
        try {
            savedEventDetailsEntity = eventDetailsTransRepository.save(eventDetailsEntity);
        } catch (DataIntegrityViolationException integrityException) {
            if(integrityException.getMessage()!=null) {
                if (integrityException.getMessage().contains("event_details_trans_fk_user_uuid")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'userUUID'");
                } else if (integrityException.getMessage().contains("event_details_trans_pay_pref_id")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'payPrefId'");
                } else if (integrityException.getMessage().contains("event_details_trans_evnt_purpose_id")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'eventPurposeId'");
                } else if (integrityException.getMessage().contains("event_details_trans_gender_id")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'eventGenderPrefId'");
                }
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create Event");
        }
        logger.info("Event ID =" + savedEventDetailsEntity.getEventId());
        event.setEventUUID(UUID.fromString(savedEventDetailsEntity.getEventUUID()));
        return event;
    }

    private void validateEvent(EventDetailRequest event, String userUUID) {

        //Static Validataions Start

        if (event.getEventStartTime().isBefore(LocalDateTime.now())) {
            System.out.println(event.getEventStartTime()+"==="+LocalDateTime.now());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be a past date");
        }
        if (event.getEventStartTime().isAfter(LocalDateTime.now().plusDays(90))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be more than 90 days from now");
        }

        //Static Validataions End

        //Lookup Validations start

        UserInfoRef userInfoRef =userInfoRefRepository.findByuserUUID(userUUID);
        if(userInfoRef==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid 'userUUID'");
        }

        if(event.getMaxGuestsAllowed()>userInfoRef.getMaxGuestsAallowedPerEvent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Only can invite upto " + userInfoRef.getMaxGuestsAallowedPerEvent() + " guests per event");
        }

        if (isEventConflictingAnyOtherExistingEvent(event.getEventStartTime(), event.getEventEndTime(), userUUID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Meeting timings conflict found. Check the events");
        }

        if (getUserEventsCountByDate(userUUID, event.getEventStartTime())>=userInfoRef.getDailyEventsLimit()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't care more than "+userInfoRef.getDailyEventsLimit()+ " events per day");
        }
        //Lookup Validations end
    }

    private Integer getUserEventsCountByDate(String userUUID, LocalDateTime eventStartTime) {
        return eventDao.getUserEventsCountByDate(userUUID, eventStartTime);
    }

    private boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String userUUID) {
        return eventDao.isEventConflictingAnyOtherExistingEvent(eventStartTime, eventEndTime, userUUID);
    }


    private EventDetailRequest enrichEventDetails(EventDetailRequest event) {
        event.setEventEndTime(event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        return event;
    }

    @Override
    public EventDetailRequest updateEvent(UpdateEventRequest event, String userUUID) {
        int updateStatus = eventDao.updateEvent(event, userUUID);
        logger.info("updateStatus= " + updateStatus);
        return event;
    }

    private EventDetailsEntity setEventDetails(EventDetailRequest event, String userUUID, LocationDetails locationDetails) {
        EventDetailsEntity eventDetailsEntity = new EventDetailsEntity();
        eventDetailsEntity.setEventActive(true);
        eventDetailsEntity.setEventAddressLn1(locationDetails.getAddrLn1());
        eventDetailsEntity.setEventAddressLn2(locationDetails.getAddrLn2());
        eventDetailsEntity.setEventCity(locationDetails.getCity());
        eventDetailsEntity.setEventState(locationDetails.getState());
        eventDetailsEntity.setEventZip(locationDetails.getZip());
        eventDetailsEntity.setEventCountry(locationDetails.getCountry());
        eventDetailsEntity.setEventFromDttm(event.getEventStartTime());
        eventDetailsEntity.setEventToDttm(event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        eventDetailsEntity.setEventGenderCatId(event.getEventGenderPrefId());
        eventDetailsEntity.setEventPurposeId(event.getEventPurposeId());
        eventDetailsEntity.setPaySharePrefId(event.getPayPrefId());
        eventDetailsEntity.setMaxGuestsAllowed(event.getMaxGuestsAllowed());
        eventDetailsEntity.setEventUUID(UUID.randomUUID().toString());
        eventDetailsEntity.setEventLocUUID(event.getLocationUUID());
        eventDetailsEntity.setEventLocationTypeID(locationDetails.getLocationTypeId());
        eventDetailsEntity.setOrganizerUUID(userUUID);
        eventDetailsEntity.setPrivate(false);
        eventDetailsEntity.setInsrtBy(userUUID);
        eventDetailsEntity.setUpdtBy(userUUID);
        eventDetailsEntity.setEventLocationName(locationDetails.getLocName());
        eventDetailsEntity.setEventLongitude(locationDetails.getLongitude());
        eventDetailsEntity.setEventLatitude(locationDetails.getLatitude());
        eventDetailsEntity.setEventAgeBracketStartYrs(event.getEventGuestExpectedAgeStart());
        eventDetailsEntity.setEventAgeBracketEndYrs(event.getEventGuestExpectedAgeEnd());
        return eventDetailsEntity;
    }
}
