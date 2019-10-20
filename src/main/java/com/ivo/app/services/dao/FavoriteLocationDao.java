package com.ivo.app.services.dao;

import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.request.FavoriteLocationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FavoriteLocationDao {

	List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, FavoriteLocationRequest favoriteLocationRequest,
                                                         String searchStrategy, Pageable pageable);

}
