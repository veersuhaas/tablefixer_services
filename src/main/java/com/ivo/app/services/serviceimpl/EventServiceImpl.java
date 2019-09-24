package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private LocationSearchDao locationSearchDao;

    @Override
    public EventDetailRequest saveEvent(EventDetailRequest event, String userUUID) {

        LocationDetails locationDetails = locationSearchDao.getLocationDetailsByLocationUUID(event.getLocationUUID());

        System.out.println(locationDetails.getAddrLn1() + "," + " city" + locationDetails.getCity());

        return null;
    }
}
