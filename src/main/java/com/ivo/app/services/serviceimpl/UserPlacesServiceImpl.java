package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.UserPlacesDao;
import com.ivo.app.services.domain.UserLocation;
import com.ivo.app.services.service.UserPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlacesServiceImpl implements UserPlacesService {

    @Autowired
    private UserPlacesDao userPlacesDao;

    @Override
    public List<UserLocation> getUserPlaces(String userUUID) {
        return userPlacesDao.getUserPlaces(userUUID);
    }
}
