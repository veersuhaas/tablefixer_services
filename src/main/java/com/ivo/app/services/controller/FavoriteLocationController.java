package com.ivo.app.services.controller;

import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.entity.UserFavoriteLocationsXref;
import com.ivo.app.services.service.FavoriteLocationService;
import com.ivo.app.services.service.LocationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value="/location")
public class FavoriteLocationController {
	
	@Autowired
    private FavoriteLocationService favoriteLocationService;

	@Autowired
	private LocationSearchService locationSearchService;
	
	@PostMapping(value="/v1/favorite/{userUUID}/create/{locationUUID}")
	public ResponseEntity<UserFavoriteLocationsXref> createUserFavoriteLocation(@PathVariable(value="userUUID") String userUUID, @PathVariable(value="locationUUID") String locationUUID) throws URISyntaxException{
		
		System.out.println("userUUID:"+userUUID+",locationUUID:"+locationUUID);

        UserFavoriteLocationsXref userFavoriteLocationsXref = favoriteLocationService.createUserFavoriteLocation(userUUID, locationUUID);
        return ResponseEntity.created(new URI("/v1/favorite/userUUID/create/locationUUID")).body(userFavoriteLocationsXref);
	}
	
	@DeleteMapping(value="/v1/favorite/{userUUID}/delete/{locationUUID}")
    public ResponseEntity<Object> deleteUserFavoriteLocation(@PathVariable(value = "userUUID") String userUUID, @PathVariable(value = "locationUUID") String locationUUID) {
		
		System.out.println("userUUID:"+userUUID+",locationUUID:"+locationUUID);

        long numOfRowsDeleted = favoriteLocationService.deleteUserFavoriteLocation(userUUID, locationUUID);
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
