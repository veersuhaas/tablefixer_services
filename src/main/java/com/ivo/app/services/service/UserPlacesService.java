package com.ivo.app.services.service;

import com.ivo.app.services.domain.UserLocation;

import java.util.List;

public interface UserPlacesService {
    List<UserLocation> getUserPlaces(String userUUID);
}