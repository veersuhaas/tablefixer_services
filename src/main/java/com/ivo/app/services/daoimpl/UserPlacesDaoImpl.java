package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.UserPlacesDao;
import com.ivo.app.services.domain.UserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserPlacesDaoImpl implements UserPlacesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserLocation> getUserPlaces(String userUUID) {
        return jdbcTemplate.query(
                "SELECT user_addr_id, user_uuid userUUID, user_location_name, address_ln1 addrLn1, address_ln2 addrLn2, city, state, zip, country, " +
                        "is_active active, is_default defaultLocation, longitude, latitude " +
                        "FROM public.user_locations_xref where is_active=true",
                new BeanPropertyRowMapper<>(UserLocation.class));
    }
}
