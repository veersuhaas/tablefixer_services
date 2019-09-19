package com.ivo.app.services.domain;

import java.io.Serializable;

public class PayPrefResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892500742965633835L;

	private Integer payPrefId;
	
	private String payPrefName;
	
	private String payPrefDisplayName;
	
	private Integer orderBy;

	public Integer getPayPrefId() {
		return payPrefId;
	}

	public void setPayPrefId(Integer payPrefId) {
		this.payPrefId = payPrefId;
	}

	public String getPayPrefName() {
		return payPrefName;
	}

	public void setPayPrefName(String payPrefName) {
		this.payPrefName = payPrefName;
	}

	public String getPayPrefDisplayName() {
		return payPrefDisplayName;
	}

	public void setPayPrefDisplayName(String payPrefDisplayName) {
		this.payPrefDisplayName = payPrefDisplayName;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	

}