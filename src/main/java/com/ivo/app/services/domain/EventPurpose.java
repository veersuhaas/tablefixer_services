package com.ivo.app.services.domain;

import java.io.Serializable;

public class EventPurpose implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5182474701018885214L;
	private int purposeId;
	private String purposeName;
	private String purposeDisplayNm;
	public int getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(int purposeId) {
		this.purposeId = purposeId;
	}
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public String getPurposeDisplayNm() {
		return purposeDisplayNm;
	}
	public void setPurposeDisplayNm(String purposeDisplayNm) {
		this.purposeDisplayNm = purposeDisplayNm;
	}
	
	
	
}
