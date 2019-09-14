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

@Repository
public class LocationSearchDaoImpl implements LocationSearchDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public List<LocationSearchResponse> searchLocations(LocationSearchRequest locationSearchRequest, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", locationSearchRequest.getUserId());
		params.put("locationtype", locationSearchRequest.getSearchBy());
		  List<LocationSearchResponse> list= namedParameterJdbcTemplate.query("select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " + 
		  		" ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber " + 
		  		" from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc " + 
		  		" where " + 
		  		" userloc.user_id=usr.user_id " + 
		  		" and usr.user_id=:userid " + 
		  		" and userloc.user_location_type=:locationtype " + 
		  		" and ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " + 
		  		" ST_MakePoint(loc.lang,loc.lat)) " +  
		  		" <= 5 * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat)," + 
		  		" ST_MakePoint(loc.lang,loc.lat)) " + 
		  		" limit "+pageable.getPageSize() + " offset "+(pageable.getPageNumber()-1)*pageable.getPageSize() ,params, new BeanPropertyRowMapper<LocationSearchResponse>(LocationSearchResponse.class));
		  return list;
	}

}
