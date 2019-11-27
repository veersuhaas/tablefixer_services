package com.ivo.app.services.controller;

import com.ivo.app.services.domain.*;
import com.ivo.app.services.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/public/create/{userUUID}")
    public ResponseEntity<EventDetailsResponse> createEvent(@RequestBody @Valid EventDetailRequest event, @PathVariable(value = "userUUID") String userUUID)  {
        EventDetailsResponse eventDetailsResponse = eventService.saveEvent(event, userUUID);
        return new ResponseEntity<>(eventDetailsResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/public/update/{userUUID}")
    public ResponseEntity<EventDetailRequest> updateEvent(@RequestBody @Valid UpdateEventRequest event, @PathVariable(value = "userUUID") @NotBlank String userUUID)  {


        EventDetailRequest eventDetailRequest = eventService.updateEvent(event, userUUID);
        return new ResponseEntity<>(eventDetailRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/public/user/{userUUID}")
    public ResponseEntity<List<PublicEventResponse>> getMyEvents(@Valid @RequestBody UserEventsServiceRequest request, @PathVariable("userUUID") String userUUID, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return new ResponseEntity<>(eventService.getMyEvents(request, userUUID, pageable), HttpStatus.OK);

    }
}
