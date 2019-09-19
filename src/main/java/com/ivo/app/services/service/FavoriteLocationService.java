package com.ivo.app.services.service;

import com.ivo.app.services.entity.UserFavoriteLocationsXref;

public interface FavoriteLocationService {

	UserFavoriteLocationsXref createUserFavoriteLocation(String userUUID, String locationUUID);

	long deleteUserFavoriteLocation(String userUUID, String locationUUID);

}
