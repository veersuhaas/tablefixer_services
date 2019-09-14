package com.ivo.app.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.service.LocationSearchService;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {

	@Autowired
	LocationSearchDao locationSearchDao;
	
	@Override
	public List<LocationSearchResponse> searchLocations(LocationSearchRequest locationSearchRequest, Pageable pageable) {
		return locationSearchDao.searchLocations(locationSearchRequest, pageable) ;
	}

}
