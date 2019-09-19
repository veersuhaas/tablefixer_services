package com.ivo.app.services.dao;

import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationSearchDao {

	List<LocationSearchResponse> searchLocations(String userUUID,LocationSearchRequest locationSearchRequest, String searchStrategy,Pageable pageable);

	List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, LocationSearchRequest locationSearchRequest,
			String searchStrategy, Pageable pageable);
}
