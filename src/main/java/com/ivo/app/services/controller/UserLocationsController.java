package com.ivo.app.services.controller;

import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.request.UserLocationRequest;
import com.ivo.app.services.service.UserPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user/myplaces")
public class UserLocationsController {

    @Autowired
    private UserPlacesService userPlacesService;

    @GetMapping(value = "/list/{userUUID}")
    public ResponseEntity<List<UserLocation>> getUserPlaces(@PathVariable(value = "userUUID") String userUUID) {
        return ResponseEntity.ok().body(userPlacesService.getUserPlaces(userUUID));
    }
    @PostMapping(value = "/create/{userUUID}")
    public ResponseEntity<UserLocationsXref> createLableForUserPlaces(@RequestBody UserLocationRequest userLocation , @PathVariable(value = "userUUID") String userUUID) {
        return ResponseEntity.ok().body(userPlacesService.createLableForMyPlaces(userLocation,userUUID,null));
    }
    @PutMapping(value = "/update/{userUUID}/{userAddrId}")
    public ResponseEntity updateLableForUserPlaces(@RequestBody UserLocationRequest userLocation , @PathVariable(value = "userUUID") String userUUID,
                                                                      @PathVariable(value = "userAddrId") Long userAddrId) {
        return ResponseEntity.ok().body(userPlacesService.updateableForMyPlaces(userLocation,userAddrId,userUUID));
    }
    @DeleteMapping(value = "/update/{userUUID}/{userAddrId}")
    public ResponseEntity deleteLableForUserPlaces( @PathVariable(value = "userUUID") String userUUID,
                                                   @PathVariable(value = "userAddrId") Long userAddrId) {
        return ResponseEntity.ok().body(userPlacesService.deleteLableForMyPlaces(userUUID,userAddrId));
    }
}
