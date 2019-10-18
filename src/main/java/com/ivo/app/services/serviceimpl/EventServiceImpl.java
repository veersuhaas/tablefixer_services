package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.entity.EventDetailsEntity;
import com.ivo.app.services.repository.EventDetailsTransRepository;
import com.ivo.app.services.service.EventService;
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

    @Autowired
    private LocationSearchDao locationSearchDao;

    @Autowired
    private EventDetailsTransRepository eventDetailsTransRepository;

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional
    public EventDetailRequest saveEvent(EventDetailRequest event, String userUUID) {

        LocationDetails locationDetails = locationSearchDao.getLocationDetailsByLocationUUID(event.getLocationUUID());
        EventDetailRequest enrichedEvent = enrichEventDetails(event);
        if (event.getEventStartTime().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be a past date");
        }
        if (event.getEventStartTime().isAfter(LocalDateTime.now().plusDays(90))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be more than 90 days from now");
        }
        if (isEventConflictingAnyOtherExistingEvent(event.getEventStartTime(), event.getEventEndTime(), userUUID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Meeting timings conflict found");
        }

        if (!isEventWithinUserDailyEventsLimit(userUUID, event.getEventStartTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Reached Daily Maximum events");
        }

        EventDetailsEntity eventDetailsEntity = setEventDetails(event, userUUID, locationDetails);
        EventDetailsEntity savedEventDetailsEntity = null;
        try {
            savedEventDetailsEntity = eventDetailsTransRepository.save(eventDetailsEntity);
        } catch (DataIntegrityViolationException integrityException) {
            if (integrityException.getMessage().contains("event_details_trans_fk_user_uuid")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'userUUID'");
            } else if (integrityException.getMessage().contains("event_details_trans_pay_pref_id")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'payPrefId'");
            } else if (integrityException.getMessage().contains("event_details_trans_evnt_purpose_id")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'eventPurposeId'");
            } else if (integrityException.getMessage().contains("event_details_trans_gender_id")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'eventGenderPrefId'");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create Event");
        }


        System.out.println("Event ID =" + savedEventDetailsEntity.getEventId());
        event.setEventUUID(UUID.fromString(savedEventDetailsEntity.getEventUUID()));
        return event;
    }

    private boolean isEventWithinUserDailyEventsLimit(String userUUID, LocalDateTime eventStartTime) {
        return eventDao.isEventWithinUserDailyEventsLimit(userUUID, eventStartTime);
    }

    private boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String userUUID) {
        return eventDao.isEventConflictingAnyOtherExistingEvent(eventStartTime, eventEndTime, userUUID);
    }


    private EventDetailRequest enrichEventDetails(EventDetailRequest event) {
        event.setEventEndTime(event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        return event;
    }

    @Override
    public EventDetailRequest updateEvent(EventDetailRequest event, String userUUID) {
        int updateStatus = eventDao.updateEvent(event, userUUID);
        System.out.println("updateStatus= " + updateStatus);
        return event;
    }

    private EventDetailsEntity setEventDetails(EventDetailRequest event, String userUUID, LocationDetails locationDetails) {
        EventDetailsEntity eventDetailsEntity = new EventDetailsEntity();
        eventDetailsEntity.setEvent_latitude(Float.parseFloat(event.getLatitude()));
        eventDetailsEntity.setEventLongitude(Float.parseFloat(event.getLongitude()));
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
        return eventDetailsEntity;
    }
}
