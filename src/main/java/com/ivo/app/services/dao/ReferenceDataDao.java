package com.ivo.app.services.dao;

import java.util.List;

import com.ivo.app.services.domain.Cusine;
import com.ivo.app.services.domain.EventGenderPref;
import com.ivo.app.services.domain.EventPurpose;
import com.ivo.app.services.domain.PayPrefResponse;

public interface ReferenceDataDao {

	List<PayPrefResponse> getAllGuestPayPreferences();

	List<EventGenderPref> getAllEventGenderPreferences();

	List<EventPurpose> getAllEventPurposes();

	List<Cusine> getCusinesList();

}
