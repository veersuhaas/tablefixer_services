package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.UserPlacesDao;
import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.repository.UserLocationsXrefReepository;
import com.ivo.app.services.request.UserLocationRequest;
import com.ivo.app.services.service.UserPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlacesServiceImpl implements UserPlacesService {

    @Autowired
    private UserPlacesDao userPlacesDao;


    @Autowired
    private UserLocationsXrefReepository userLocationsXrefReepository;

    @Override
    public List<UserLocation> getUserPlaces(String userUUID) {
        return userPlacesDao.getUserPlaces(userUUID);
    }

    @Override
    public UserLocationsXref createLableForMyPlaces(UserLocationRequest userLocation,String userUUID,
                                                    Long useerAddrId) {

        return userPlacesDao.createLableForMyPlaces(userLocation,userUUID,useerAddrId);
    }
   @Override
   public int updateableForMyPlaces(UserLocationRequest userLocation,Long userAdrId,String userUUID) {

       return userPlacesDao.updateLableForMyPlaces(userLocation,userAdrId);
   }

    @Override
    public long deleteLableForMyPlaces(String userUUID, Long useerAdrId) {
        return userLocationsXrefReepository.deleteByuserUuidAndUserAddrId(userUUID,  useerAdrId);
    }
}
