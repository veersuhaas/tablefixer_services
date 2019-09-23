package com.ivo.app.services.controller;

import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.service.UserPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserLocationsController {

    @Autowired
    private UserPlacesService userPlacesService;

    @GetMapping(value = "/places/list/{userUUID}")
    public ResponseEntity<List<UserLocation>> getUserPlaces(@PathVariable(value = "userUUID") String userUUID) {
        return ResponseEntity.ok().body(userPlacesService.getUserPlaces(userUUID));
    }
}
