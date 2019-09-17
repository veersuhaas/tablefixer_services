package com.ivo.app.services.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.domain.UserLocationInfo;

public interface LocationSearchDao {

	List<LocationSearchResponse> searchLocations(LocationSearchRequest locationSearchRequest, Pageable pageable);

	List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, UserLocationInfo userLocationInfo,
			String searchStrategy, Pageable pageable);

}
