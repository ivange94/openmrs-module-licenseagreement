package org.openmrs.module.licenseagreement;

import org.openmrs.BaseOpenmrsData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "licenseagreement.LicenseAgreement")
@Table(name = "license_agreement")
public class LicenseAgreement extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column
	private String url;
	
	@Column
	private Integer version;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
}
