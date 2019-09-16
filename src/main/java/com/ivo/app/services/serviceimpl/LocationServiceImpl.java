package com.ivo.app.services.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivo.app.services.entity.UserFavoriteLocationsXref;
import com.ivo.app.services.repository.UserFavoriteLocationsXrefRepository;
import com.ivo.app.services.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private UserFavoriteLocationsXrefRepository userFavoriteLocationsXrefRepository;

	@Override
	public UserFavoriteLocationsXref createUserFavoriteLocation(String userUUID, String locationUUID) {

		UserFavoriteLocationsXref userFavoriteLocationsXref = new UserFavoriteLocationsXref();

		userFavoriteLocationsXref.setUserUuid(userUUID);
		userFavoriteLocationsXref.setLocationUuid(locationUUID);
		userFavoriteLocationsXref.setInsrtBy(userUUID);
		userFavoriteLocationsXref.setUpdtBy(userUUID);

		return userFavoriteLocationsXrefRepository.save(userFavoriteLocationsXref);
	}

	@Override
	public long deleteUserFavoriteLocation(String userUUID, String locationUUID) {
//		UserFavoriteLocationsXref userFavoriteLocationsXref = new UserFavoriteLocationsXref();
//
//		userFavoriteLocationsXref.setUserUuid(userUUID);
//		userFavoriteLocationsXref.setLocationUuid(locationUUID);
		return userFavoriteLocationsXrefRepository.deleteByUserUuidAndLocationUuid(userUUID,  locationUUID);
	}

}
