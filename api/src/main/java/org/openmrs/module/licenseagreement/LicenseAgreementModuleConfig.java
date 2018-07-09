/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.licenseagreement;

import org.springframework.stereotype.Component;

/**
 * Contains module's config.
 */
@Component("licenseagreement.LicenseAgreementModuleConfig")
public class LicenseAgreementModuleConfig {
	
	public static final String MODULE_PRIVILEGE = "License Agreement Module Privilege";
	
	public static final String EDIT_LICENSE_AGREEMENT_PRIVILEGE = "Edit License Agreement";
}
