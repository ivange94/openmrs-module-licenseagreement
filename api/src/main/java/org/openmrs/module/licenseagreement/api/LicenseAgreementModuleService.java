/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.licenseagreement.api;

import org.openmrs.User;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.licenseagreement.LicenseAgreement;
import org.openmrs.module.licenseagreement.LicenseAgreementModuleConfig;
import org.openmrs.module.licenseagreement.Item;
import org.openmrs.module.licenseagreement.LicensedUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface LicenseAgreementModuleService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	@Authorized(LicenseAgreementModuleConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;
	
	@Authorized
	@Transactional
	LicensedUser licenseUser(User user);
	
	@Authorized
	@Transactional(readOnly = true)
	LicensedUser getLicensedUser(User user);
	
	@Authorized
	@Transactional(readOnly = true)
	LicenseAgreement getLicenseAgreement();
	
	@Authorized(LicenseAgreementModuleConfig.EDIT_LICENSE_AGREEMENT_PRIVILEGE)
	@Transactional
	LicenseAgreement updateLicenseAgreement(String licenseBody);
}
