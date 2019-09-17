package com.ivo.app.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.domain.UserLocationInfo;
import com.ivo.app.services.service.LocationSearchService;
import com.ivo.app.services.util.LocationSearchConstants;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {

	@Autowired
	private LocationSearchDao locationSearchDao;

	@Override
	public List<LocationSearchResponse> searchLocations(LocationSearchRequest locationSearchRequest,
			Pageable pageable) {
		return locationSearchDao.searchLocations(locationSearchRequest, pageable);
	}

	@Override
	public List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, UserLocationInfo userLocationInfo,Pageable pageable) {
		
		if(!StringUtils.isEmpty(userLocationInfo.getLongitude()) && !StringUtils.isEmpty(userLocationInfo.getLatitude()) ) {
			return locationSearchDao.getUserFavoriteLocation(userUUID,  userLocationInfo, LocationSearchConstants.GEO_COORDINATES,pageable);
		}else if (!StringUtils.isEmpty(userLocationInfo.getUserBookMarkLocationType())) {
			return locationSearchDao.getUserFavoriteLocation(userUUID,  userLocationInfo, LocationSearchConstants.USER_LOCATIONS,pageable);
		}else if(!StringUtils.isEmpty(userLocationInfo.getUserBookMarkLocationType())) {
			return locationSearchDao.getUserFavoriteLocation(userUUID,  userLocationInfo, LocationSearchConstants.CUSTOM_ADDRESS,pageable);
		}else {
			return null; // To Do . throw an exception to notify invalid request
		}
	}

}
