package com.ivo.app.services.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
public class LocationControler {
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationSearchService locationSearchService;
	
	@PostMapping(value="/v1/favorite/{uuid}/create/{id}")
	public ResponseEntity<UserFavoriteLocationsXref> createUserFavoriteLocation(@PathVariable(value="uuid") String userUUID, @PathVariable(value="id") String restaurantUUID) throws URISyntaxException{
		
		System.out.println("userUUID:"+userUUID+",restaurantUUID:"+restaurantUUID);
		
		UserFavoriteLocationsXref userFavoriteLocationsXref =locationService.createUserFavoriteLocation(userUUID,restaurantUUID);
		return ResponseEntity.created(new URI("/v1/favorite/USERUUID/create/LOCATIONUUID4")).body(userFavoriteLocationsXref);
	}
	
	@DeleteMapping(value="/v1/favorite/{uuid}/delete/{id}")
	public  ResponseEntity<Object> deleteUserFavoriteLocation(@PathVariable(value="uuid") String userUUID, @PathVariable(value="id") String restaurantUUID) throws URISyntaxException{
		
		System.out.println("userUUID:"+userUUID+",restaurantUUID:"+restaurantUUID);
		
		long numOfRowsDeleted =locationService.deleteUserFavoriteLocation(userUUID,restaurantUUID);
		System.out.println(" deleted rows #"+numOfRowsDeleted);
		return ResponseEntity.noContent().build();
	}

	
	@PostMapping(value="v1/favorite/list/{uuid}")
	public  ResponseEntity<List<LocationSearchResponse>> getUserFavoriteLocation(@PathVariable(value="uuid") String userUUID, @RequestBody UserLocationInfo userLocationInfo, @RequestParam Integer start,@RequestParam  Integer limit){
		
		System.out.println("Getting list of favorite restaurants by userUUID:"+userUUID);
		Pageable pageable = PageRequest.of(start, limit);
		List<LocationSearchResponse> userFavoriteLocations=locationSearchService.getUserFavoriteLocation(userUUID,userLocationInfo, pageable);
		System.out.println(" User Favorite locations list size #"+userFavoriteLocations.size());
		return ResponseEntity.ok().body(userFavoriteLocations);
	}
}
