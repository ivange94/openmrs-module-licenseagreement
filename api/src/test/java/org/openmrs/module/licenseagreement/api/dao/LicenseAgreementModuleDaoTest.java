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

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.licenseagreement.Item;
import org.openmrs.module.licenseagreement.LicenseAgreement;
import org.openmrs.module.licenseagreement.LicensedUser;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * It is an integration test (extends BaseModuleContextSensitiveTest), which verifies DAO methods
 * against the in-memory H2 database. The database is initially loaded with data from
 * standardTestDataset.xml in openmrs-api. All test methods are executed in transactions, which are
 * rolled back by the end of each test method.
 */
public class LicenseAgreementModuleDaoTest extends BaseModuleContextSensitiveTest {
	
	@Autowired
	LicenseAgreementModuleDao dao;
	
	@Autowired
	UserService userService;
	
	@Before
	public void setUp() throws Exception {
		executeDataSet("LicenseAgreementModuleDaoTestDataset.xml");
	}
	
	@Test
	@Ignore("Unignore if you want to make the Item class persistable, see also Item and liquibase.xml")
	public void saveItem_shouldSaveAllPropertiesInDb() {
		//Given
		Item item = new Item();
		item.setDescription("some description");
		item.setOwner(userService.getUser(1));
		
		//When
		dao.saveItem(item);
		
		//Let's clean up the cache to be sure getItemByUuid fetches from DB and not from cache
		Context.flushSession();
		Context.clearSession();
		
		//Then
		Item savedItem = dao.getItemByUuid(item.getUuid());
		
		assertThat(savedItem, hasProperty("uuid", is(item.getUuid())));
		assertThat(savedItem, hasProperty("owner", is(item.getOwner())));
		assertThat(savedItem, hasProperty("description", is(item.getDescription())));
	}
	
	@Test
	public void getLicenseAgreement_shouldGetALicenseAgreement() {
		LicenseAgreement licenseAgreement = dao.getLicenseAgreement();
		
		assertThat(licenseAgreement.getUrl(), is("http://example.com"));
	}
	
	@Test
	public void updateLicenseAgreementShouldUpdateBodyWhenLicenseAgreementIsUpdated() {
		dao.updateLicenseAgreement("http://another.example.com");
		LicenseAgreement updated = dao.getLicenseAgreement();
		
		assertThat(updated.getUrl(), is("http://another.example.com"));
	}
	
	@Test
	public void updateLicenseAgreement_shouldUpdateVersionWhenLicenseAgreementIsUpdated() {
		Integer oldVersion = dao.getLicenseAgreement().getVersion();
		dao.updateLicenseAgreement("http://another.example.com");
		Integer newVersion = dao.getLicenseAgreement().getVersion();
		
		assertThat(newVersion, is(oldVersion + 1));
	}
	
	@Test
	public void getLicensedUser_shouldGetLicensedUserIfUserHasAcceptedLicenseAgreement() {
		User user = Context.getUserService().getUser(1);
		LicensedUser licensedUser = dao.getLicensedUser(user);
		assertThat(licensedUser, is(notNullValue()));
	}
	
	@Test
	public void getLicensedUser_shouldReturnNullIfUserHasNotAcceptedLicenseAgreement() {
		User user = Context.getUserService().getUser(2);
		assertThat(user, is(notNullValue()));
		LicensedUser licensedUser = dao.getLicensedUser(user);
		assertThat(licensedUser, is(nullValue()));
	}
	
	@Test
	public void licenseUser_shouldLicenseUser() {
		User user = Context.getUserService().getUser(1);
		dao.licenseUser(user);
	}
}
