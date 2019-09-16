package com.ivo.app.services.domain;

import java.io.Serializable;

public class Cusine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092376161522119670L;
	private int tagId;
	private String tagName;
	private String tagDisplayName;
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagDisplayName() {
		return tagDisplayName;
	}
	public void setTagDisplayName(String tagDisplayName) {
		this.tagDisplayName = tagDisplayName;
	}
	
	
}
