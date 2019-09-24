package com.ivo.app.services.controller;

import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/public/create/{userUUID}")
    public ResponseEntity<EventDetailRequest> saveEvent(@RequestBody EventDetailRequest event, @PathVariable(value = "userUUID") String userUUID) throws URISyntaxException {
        EventDetailRequest eventDetailRequest = eventService.saveEvent(event, userUUID);
        return new ResponseEntity<>(eventDetailRequest, HttpStatus.CREATED);
    }
}
