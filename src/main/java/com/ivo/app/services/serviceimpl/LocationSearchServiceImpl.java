package com.ivo.app.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.service.LocationSearchService;
import com.ivo.app.services.util.LocationSearchConstants;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {

	@Autowired
	private LocationSearchDao locationSearchDao;

	@Override
	public List<LocationSearchResponse> searchLocations(String userUUID,LocationSearchRequest locationSearchRequest,
			Pageable pageable) {
		if(!StringUtils.isEmpty(locationSearchRequest.getLongitude()) && !StringUtils.isEmpty(locationSearchRequest.getLatitude()) ) {
			return locationSearchDao.searchLocations(userUUID,locationSearchRequest, LocationSearchConstants.GEO_COORDINATES, pageable);
		}else if(!StringUtils.isEmpty(locationSearchRequest.getUserBookMarkLocationType())){
			return locationSearchDao.searchLocations(userUUID,locationSearchRequest,LocationSearchConstants.USER_LOCATIONS, pageable);
		}else{
			return null;// To Do . throw an exception to notify invalid request
		}

	}

	@Override
	public List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, LocationSearchRequest locationSearchRequest,Pageable pageable) {
		
		if(!StringUtils.isEmpty(locationSearchRequest.getLongitude()) && !StringUtils.isEmpty(locationSearchRequest.getLatitude()) ) {
			return locationSearchDao.getUserFavoriteLocation(userUUID,  locationSearchRequest, LocationSearchConstants.GEO_COORDINATES,pageable);
		}else if (!StringUtils.isEmpty(locationSearchRequest.getUserBookMarkLocationType())) {
			return locationSearchDao.getUserFavoriteLocation(userUUID,  locationSearchRequest, LocationSearchConstants.USER_LOCATIONS,pageable);
		}else {
			return null; // To Do . throw an exception to notify invalid request
		}
	}

}
