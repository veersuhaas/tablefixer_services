package com.ivo.app.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.service.LocationSearchService;

@RestController
@RequestMapping(value="/location")
public class LocationSearchController {
	
	@Autowired
	private LocationSearchService locationSearchService;
	
	@PostMapping(value="/search/{userUUID}")
	public ResponseEntity<List<LocationSearchResponse>> searchLocations(@RequestBody LocationSearchRequest locationSearchRequest, @PathVariable(value="userUUID") String userUUID, @RequestParam Integer start, @RequestParam  Integer limit) {
		Pageable pageable = PageRequest.of(start, limit);
		List<LocationSearchResponse> searchLocations=locationSearchService.searchLocations(userUUID,locationSearchRequest,pageable);
		return ResponseEntity.ok().body(searchLocations);

	}
}