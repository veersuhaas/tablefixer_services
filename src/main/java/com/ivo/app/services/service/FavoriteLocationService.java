package com.ivo.app.services.service;

import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.entity.UserFavoriteLocationsXref;
import com.ivo.app.services.request.FavoriteLocationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FavoriteLocationService {

	UserFavoriteLocationsXref createUserFavoriteLocation(String userUUID, String locationUUID);

	long deleteUserFavoriteLocation(String userUUID, String locationUUID);

	List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, FavoriteLocationRequest favoriteLocationRequest,
														 Pageable pageable);

}
