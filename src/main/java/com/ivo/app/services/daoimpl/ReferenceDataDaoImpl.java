package com.ivo.app.services.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ivo.app.services.dao.ReferenceDataDao;
import com.ivo.app.services.domain.Cusine;
import com.ivo.app.services.domain.EventGenderPref;
import com.ivo.app.services.domain.EventPurpose;
import com.ivo.app.services.domain.PayPrefResponse;

@Repository
public class ReferenceDataDaoImpl implements ReferenceDataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PayPrefResponse> getAllGuestPayPreferences() {
		System.out.println("=====DAO Call getAllGuestPayPreferences =>");
		return jdbcTemplate.query(
				" select pay_pref_id, pay_pref_name, pay_pref_display_name, order_by from event_pay_pref_ref order by order_by",
				new BeanPropertyRowMapper<PayPrefResponse>(PayPrefResponse.class));
		
	}

	@Override
	public List<EventGenderPref> getAllEventGenderPreferences() {
		System.out.println("=====DAO Call getAllEventGenderPreferences =>");

		return jdbcTemplate.query(
				" select gender_id, gender_name,event_display_name  from gender_catg_ref where is_active_event_type=true",
				new BeanPropertyRowMapper<EventGenderPref>(EventGenderPref.class));
	}

	@Override
	public List<EventPurpose> getAllEventPurposes() {
		System.out.println("=====DAO Call getAllEventPurposes =>");

		return jdbcTemplate.query(
				" select purpose_id,purpose_name,purpose_display_nm from event_purpose_ref where is_active=true",
				new BeanPropertyRowMapper<EventPurpose>(EventPurpose.class));
	}

	@Override
	public List<Cusine> getCusinesList() {
		return jdbcTemplate.query(
				" select tag_id,tag_name,tag_display_name from location_tags_ref where loc_type_id=1 and is_active='Y'",
				new BeanPropertyRowMapper<Cusine>(Cusine.class));
	}

}
