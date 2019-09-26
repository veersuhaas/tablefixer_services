package com.ivo.app.services.dao;

import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.request.UserLocationRequest;


import java.util.List;

public interface UserPlacesDao {
     List<UserLocation> getUserPlaces(String userUUID);
     UserLocationsXref createLableForMyPlaces(UserLocationRequest userLocation,String userUUID,Long userAddrId);
     int updateLableForMyPlaces(UserLocationRequest userLocation,Long userAdrId) ;
    long deleteLableForMyPlaces(String userUuid,Long userAdrId);

}
