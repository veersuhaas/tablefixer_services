package com.ivo.app.services.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ivo.app.services.entity.UserFavoriteLocationsXref;

public interface UserFavoriteLocationsXrefRepository extends CrudRepository<UserFavoriteLocationsXref, Long>{

	@Transactional
	long deleteByUserUuidAndLocationUuid(String userUuid, String locationUuid);
	
}
