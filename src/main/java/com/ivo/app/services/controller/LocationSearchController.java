package com.ivo.app.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.service.LocationSearchService;

@RestController
@RequestMapping(value="/location")
public class LocationSearchController {
	
	@Autowired
	private LocationSearchService locationSearchService;
	
	@PostMapping(value="/search")
	public List<LocationSearchResponse> searchLocations(@RequestBody LocationSearchRequest locationSearchRequest, @RequestParam Integer start,@RequestParam  Integer limit) {
		Pageable pageable = PageRequest.of(start, limit);
		return locationSearchService.searchLocations(locationSearchRequest,pageable);
	}
}
