package com.ivo.app.services.controller;

import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.request.UserLocationRequest;
import com.ivo.app.services.service.UserPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user/places")
public class UserPlacesController {

    @Autowired
    private UserPlacesService userPlacesService;

    @GetMapping(value = "/list/{userUUID}")
    public ResponseEntity<List<UserLocation>> getUserPlaces(@PathVariable(value = "userUUID") String userUUID) {
        return ResponseEntity.ok().body(userPlacesService.getUserPlaces(userUUID));
    }
    @PostMapping(value = "/create/{userUUID}")
    public ResponseEntity<UserLocationsXref> createLableForUserPlaces(@RequestBody @Valid UserLocationRequest userLocation , @PathVariable(value = "userUUID") String userUUID) {
        boolean isNameUnique =userPlacesService.checkLocationNameUniquenessByUser(userUUID, userLocation.getUserLocationName());
        if(isNameUnique==false){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already an existing location name. Please use different label name");
        }
        return ResponseEntity.ok().body(userPlacesService.createLableForMyPlaces(userLocation,userUUID,null));
    }
    @PutMapping(value = "/update/{userUUID}/{userAddrId}")
    public ResponseEntity updateLableForUserPlaces(@RequestBody UserLocationRequest userLocation , @PathVariable(value = "userUUID") String userUUID,
                                                                      @PathVariable(value = "userAddrId") Long userAddrId) {
        return ResponseEntity.ok().body(userPlacesService.updateableForMyPlaces(userLocation,userAddrId,userUUID));
    }

    @DeleteMapping(value = "/delete/{userUUID}/{userAddrId}")
    public ResponseEntity deleteLableForUserPlaces( @PathVariable(value = "userUUID") String userUUID,
                                                   @PathVariable(value = "userAddrId") Long userAddrId) {

        if(userPlacesService.deleteLableForMyPlaces(userUUID,userAddrId)>0){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no content to delete");
        }
    }
}
