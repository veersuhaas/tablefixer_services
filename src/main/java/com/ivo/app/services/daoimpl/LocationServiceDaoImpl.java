package com.ivo.app.services.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ivo.app.services.dao.LocationServiceDao;

@Repository
public class LocationServiceDaoImpl implements LocationServiceDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void createUserFavoriteLocation(String userUUID, String restaurantUUID) {
		
//		jdbcTemplate.query("INSERT INTO public.user_favorite_locations_xref (location_id, user_id, user_uuid, is_active, insrt_dttm, insrt_by, updt_dttm, updt_by) VALUES(0, 0, '', true, now(), '', '', '')");
		
	}

}
