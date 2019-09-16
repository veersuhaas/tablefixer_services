package com.ivo.app.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivo.app.services.domain.Cusine;
import com.ivo.app.services.domain.EventGenderPref;
import com.ivo.app.services.domain.EventPurpose;
import com.ivo.app.services.domain.PayPrefResponse;
import com.ivo.app.services.service.ReferenceDataService;

@RestController
@RequestMapping(value="/ref")
public class ReferenceDataController {
	
	@Autowired
	private ReferenceDataService referenceDataService;
	
	@GetMapping(value="/guests/paypref/list")
	public List<PayPrefResponse> getAllGuestPayPreferences(){
		return referenceDataService.getAllGuestPayPreferences();
		
	}
	
	@GetMapping(value="/guests/genderpref/list")
	public List<EventGenderPref> getAllEventGenderPreferences(){
		return referenceDataService.getAllEventGenderPreferences();
		
	}
	
	@GetMapping(value="/eventspurpose/list")
	public List<EventPurpose> getAllEventPurposes(){
		return referenceDataService.getAllEventPurposes();
		
	}
	
	@GetMapping(value="/cusine/list/top/{count}")
	public List<Cusine> getCusinesList(){
		return referenceDataService.getCusinesList(); 
		
	}
}
