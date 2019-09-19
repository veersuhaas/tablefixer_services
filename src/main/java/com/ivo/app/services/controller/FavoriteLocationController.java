package com.ivo.app.services.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.ivo.app.services.domain.LocationSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.domain.UserLocationInfo;
import com.ivo.app.services.entity.UserFavoriteLocationsXref;
import com.ivo.app.services.service.LocationSearchService;
import com.ivo.app.services.service.LocationService;

@RestController
@RequestMapping(value="/location")
public class FavoriteLocationController {
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationSearchService locationSearchService;
	
	@PostMapping(value="/v1/favorite/{userUUID}/create/{locationUUID}")
	public ResponseEntity<UserFavoriteLocationsXref> createUserFavoriteLocation(@PathVariable(value="userUUID") String userUUID, @PathVariable(value="locationUUID") String locationUUID) throws URISyntaxException{
		
		System.out.println("userUUID:"+userUUID+",locationUUID:"+locationUUID);
		
		UserFavoriteLocationsXref userFavoriteLocationsXref =locationService.createUserFavoriteLocation(userUUID,locationUUID);
		return ResponseEntity.created(new URI("/v1/favorite/USERUUID/create/LOCATIONUUID4")).body(userFavoriteLocationsXref);
	}
	
	@DeleteMapping(value="/v1/favorite/{userUUID}/delete/{locationUUID}")
	public  ResponseEntity<Object> deleteUserFavoriteLocation(@PathVariable(value="userUUID") String userUUID, @PathVariable(value="locationUUID") String locationUUID) throws URISyntaxException{
		
		System.out.println("userUUID:"+userUUID+",locationUUID:"+locationUUID);
		
		long numOfRowsDeleted =locationService.deleteUserFavoriteLocation(userUUID,locationUUID);
		System.out.println(" deleted rows #"+numOfRowsDeleted);
		return ResponseEntity.noContent().build();
	}

	
	@PostMapping(value="v1/favorite/list/{userUUID}")
	public  ResponseEntity<List<LocationSearchResponse>> getUserFavoriteLocation(@PathVariable(value="userUUID") String userUUID, @RequestBody LocationSearchRequest locationSearchRequest, @RequestParam Integer start, @RequestParam  Integer limit){
		
		System.out.println("Getting list of favorite restaurants by userUUID:"+userUUID);
		Pageable pageable = PageRequest.of(start, limit);
		List<LocationSearchResponse> userFavoriteLocations=locationSearchService.getUserFavoriteLocation(userUUID,locationSearchRequest, pageable);
		System.out.println(" User Favorite locations list size #"+userFavoriteLocations.size());
		return ResponseEntity.ok().body(userFavoriteLocations);
	}
}
