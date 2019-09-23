package com.ivo.app.services.service;

import com.ivo.app.services.domain.Cuisine;
import com.ivo.app.services.domain.EventGenderPref;
import com.ivo.app.services.domain.EventPurpose;
import com.ivo.app.services.domain.PayPrefResponse;

import java.util.List;

public interface ReferenceDataService {

	List<PayPrefResponse> getAllGuestPayPreferences();

	List<EventGenderPref> getAllEventGenderPreferences();

	List<EventPurpose> getAllEventPurposes();

    List<Cuisine> getCuisinesList();

}
