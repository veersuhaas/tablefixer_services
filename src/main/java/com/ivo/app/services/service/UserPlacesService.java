package com.ivo.app.services.service;

import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.request.UserLocationRequest;

import java.util.List;

public interface UserPlacesService {
    List<UserLocation> getUserPlaces(String userUUID);
    UserLocationsXref createLableForMyPlaces(UserLocationRequest userLocation,String userUUID,
                                                    Long useerAddrId);
    int updateableForMyPlaces(UserLocationRequest userLocation,Long userAddrId,String userUUID);
    long deleteLableForMyPlaces(String userUUID, Long useerAdrId);

}