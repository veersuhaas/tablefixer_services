package com.ivo.app.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ivo.app.services.dao.ReferenceDataDao;
import com.ivo.app.services.domain.Cusine;
import com.ivo.app.services.domain.EventGenderPref;
import com.ivo.app.services.domain.EventPurpose;
import com.ivo.app.services.domain.PayPrefResponse;
import com.ivo.app.services.service.ReferenceDataService;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {
	
	@Autowired
	private ReferenceDataDao referenceDataDao;

	@Override
	@Cacheable(cacheNames="payPrefs")
	public List<PayPrefResponse> getAllGuestPayPreferences() {
		return referenceDataDao.getAllGuestPayPreferences();
	}

	@Override
	@Cacheable(cacheNames="eventGenderPrefs")
	public List<EventGenderPref> getAllEventGenderPreferences() {
		return referenceDataDao.getAllEventGenderPreferences();
	}

	@Override
	@Cacheable(cacheNames="eventPurposes")
	public List<EventPurpose> getAllEventPurposes() {
		return referenceDataDao.getAllEventPurposes();
	}

	@Override
	@Cacheable(cacheNames="cusinesList")
	public List<Cusine> getCusinesList() {
		return referenceDataDao.getCusinesList();
	}

}
