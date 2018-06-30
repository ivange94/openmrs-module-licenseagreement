package org.openmrs.module.licenseagreement;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "licenseagreement.LicensedUser")
@Table(name = "licensed_user")
public class LicensedUser extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@Column(name = "license_version_signed")
	private int licenseVersionSigned;
	
	public LicensedUser() {
	}
	
	public LicensedUser(User user, Integer licenseVersionSigned) {
		this.user = user;
		this.licenseVersionSigned = licenseVersionSigned;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getLicenseVersionSigned() {
		return licenseVersionSigned;
	}
	
	public void setLicenseVersionSigned(int licenseVersionSigned) {
		this.licenseVersionSigned = licenseVersionSigned;
	}
}
