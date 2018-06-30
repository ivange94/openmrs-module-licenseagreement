/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.licenseagreement.api.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.User;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.licenseagreement.Item;
import org.openmrs.module.licenseagreement.LicenseAgreement;
import org.openmrs.module.licenseagreement.LicensedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("licenseagreement.LicenseAgreementModuleDao")
public class LicenseAgreementModuleDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public Item saveItem(Item item) {
		getSession().saveOrUpdate(item);
		return item;
	}
	
	public LicenseAgreement updateLicenseAgreement(String licenseBody) {
		LicenseAgreement licenseAgreement = getLicenseAgreement();
		licenseAgreement.setBody(licenseBody);
		licenseAgreement.setVersion(licenseAgreement.getVersion() + 1);
		getSession().saveOrUpdate(licenseAgreement);
		return licenseAgreement;
	}
	
	public LicenseAgreement getLicenseAgreement() {
		return (LicenseAgreement) getSession().createCriteria(LicenseAgreement.class).list().get(0);
	}
	
	public LicensedUser licenseUser(User user) {
		LicensedUser licensedUser = new LicensedUser(user, getLicenseAgreement().getVersion());
		getSession().saveOrUpdate(licensedUser);
		return licensedUser;
	}
	
	public LicensedUser getLicensedUser(User user) {
		return (LicensedUser) getSession().createCriteria(LicensedUser.class).add(Restrictions.eq("user", user))
		        .uniqueResult();
	}
}
