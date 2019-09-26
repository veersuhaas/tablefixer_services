package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.entity.EventDetailsEntity;
import com.ivo.app.services.repository.EventDetailsTransRepository;
import com.ivo.app.services.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        EventDetailsEntity eventDetailsEntity = setEventDetails(event, userUUID, locationDetails);

        System.out.println(locationDetails.getAddrLn1() + "," + " city" + locationDetails.getCity());

        EventDetailsEntity savedEventDetailsEntity = eventDetailsTransRepository.save(eventDetailsEntity);
        System.out.println("Event ID =" + savedEventDetailsEntity.getEventId());
        event.setEventUUID(UUID.fromString(savedEventDetailsEntity.getEventUUID()));
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
