package com.ivo.app.services.dao;

import com.ivo.app.services.domain.UserLocation;

import java.util.List;

public interface UserPlacesDao {
    List<UserLocation> getUserPlaces(String userUUID);
}
