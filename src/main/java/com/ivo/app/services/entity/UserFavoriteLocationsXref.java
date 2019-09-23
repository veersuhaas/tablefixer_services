package com.ivo.app.services.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_favorite_locations_xref")
public class UserFavoriteLocationsXref {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_favorite_id")
	private Long userFavoriteId;
	
	@Column(name="user_uuid")
	private String userUuid;

	@Column(name="is_active")
	private Boolean isActive =true;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="insrt_dttm")
	private Date insrtDttm;
	
	@Column(name="insrt_by")
	private String insrtBy;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updt_dttm")
	private Date updtDttm;
	
	@Column(name="updt_by")
	private String updtBy;
	
	@Column(name="location_uuid")
	private String locationUuid;

	public Long getUserFavoriteId() {
		return userFavoriteId;
	}

	public void setUserFavoriteId(Long userFavoriteId) {
		this.userFavoriteId = userFavoriteId;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getInsrtDttm() {
		return insrtDttm;
	}

	public void setInsrtDttm(Date insrtDttm) {
		this.insrtDttm = insrtDttm;
	}

	public String getInsrtBy() {
		return insrtBy;
	}

	public void setInsrtBy(String insrtBy) {
		this.insrtBy = insrtBy;
	}

	public Date getUpdtDttm() {
		return updtDttm;
	}

	public void setUpdtDttm(Date updtDttm) {
		this.updtDttm = updtDttm;
	}

	public String getUpdtBy() {
		return updtBy;
	}

	public void setUpdtBy(String updtBy) {
		this.updtBy = updtBy;
	}

	public String getLocationUuid() {
		return locationUuid;
	}

	public void setLocationUuid(String locationUuid) {
		this.locationUuid = locationUuid;
	}

}
