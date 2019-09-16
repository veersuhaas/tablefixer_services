package com.ivo.app.services.service;

import java.util.List;

import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.domain.UserLocationInfo;
import com.ivo.app.services.entity.UserFavoriteLocationsXref;

public interface LocationService {

	UserFavoriteLocationsXref createUserFavoriteLocation(String userUUID, String locationUUID);

	long deleteUserFavoriteLocation(String userUUID, String locationUUID);

}
