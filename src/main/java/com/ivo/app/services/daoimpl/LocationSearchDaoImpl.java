package com.ivo.app.services.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.util.LocationSearchConstants;

@Repository
public class LocationSearchDaoImpl implements LocationSearchDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public List<LocationSearchResponse> searchLocations(String userUUID,LocationSearchRequest locationSearchRequest, String searchStrategy,Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("UUID", userUUID);
		params.put("locationtype", locationSearchRequest.getUserBookMarkLocationType());
		params.put("radius", new Float(locationSearchRequest.getSearchRadiusMiles()));
		  List<LocationSearchResponse> list= namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_SEARCH_LOCATIONS_BY_BOOK_MARKED_COORDINATES +
		  		" limit "+pageable.getPageSize() + " offset "+(pageable.getPageNumber()-1)*pageable.getPageSize() ,params,  new BeanPropertyRowMapper<LocationSearchResponse>(LocationSearchResponse.class));
		  return list;
	}
	
	@Override
	public List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, LocationSearchRequest locationSearchRequest,
			String searchStrategy, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		System.out.println(locationSearchRequest.getLongitude());
		System.out.println(userUUID);

		System.out.println(locationSearchRequest.getLatitude());
		System.out.println(pageable.getPageSize());
		System.out.println((pageable.getPageNumber()-1)*pageable.getPageSize());
		params.put("radius", new Float(locationSearchRequest.getSearchRadiusMiles()));
		if(LocationSearchConstants.GEO_COORDINATES.equals(searchStrategy)){
			params.put("lng", Double.parseDouble(locationSearchRequest.getLongitude()));
			params.put("lat", Double.parseDouble(locationSearchRequest.getLatitude()));
			return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_USER_FAVORITE_LOCATIONS_BY_GPS_COORDINATES +" limit "+pageable.getPageSize() +" offset "+ (pageable.getPageNumber()-1)*pageable.getPageSize(),params, new BeanPropertyRowMapper<LocationSearchResponse>(LocationSearchResponse.class));
		}else if(LocationSearchConstants.USER_LOCATIONS.equals(searchStrategy)){
			params.put("userUuid", userUUID);
			params.put("userlocationtype", locationSearchRequest.getUserBookMarkLocationType().toUpperCase());
			return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_USER_FAVORITE_LOCATIONS_BY_BOOK_MARKED_COORDINATES +" limit "+pageable.getPageSize() +" offset "+ (pageable.getPageNumber()-1)*pageable.getPageSize(),params, new BeanPropertyRowMapper<LocationSearchResponse>(LocationSearchResponse.class));
		}
		  return null;
	}

}
