package org.openmrs.module.licenseagreement.page;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.licenseagreement.api.LicenseAgreementModuleService;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.beans.factory.annotation.Autowired;

public class LicenseAgreementPageController {
	
	@Autowired
	private LicenseAgreementModuleService service;
	
	public void get(PageModel pageModel) {
		User authenticatedUser = Context.getAuthenticatedUser();
		String licenseBody = service.getLicenseAgreement().getBody();
		pageModel.addAttribute("userId", authenticatedUser.getUuid());
		pageModel.addAttribute("licenseBody", licenseBody);
	}
}
