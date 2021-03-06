package org.openmrs.module.licenseagreement.interceptor;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.licenseagreement.LicensedUser;
import org.openmrs.module.licenseagreement.api.LicenseAgreementModuleService;
import org.openmrs.ui.framework.page.PageRequest;
import org.openmrs.ui.framework.page.PageRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LicenseAgreementPageRequestMapper implements PageRequestMapper {
	
	@Autowired
	LicenseAgreementModuleService service;
	
	@Override
	public boolean mapRequest(PageRequest pageRequest) {
		if (everyPageExcept("login", "denied", "licenseagreement", "myaccount/changePassword", pageRequest)
		        && needToSignLicenseAgreement()) {
			pageRequest.setProviderNameOverride("licenseagreement");
			pageRequest.setPageName("notification");
			return true;
		}
		return false;
	}
	
	private boolean needToSignLicenseAgreement() {
		User user = Context.getAuthenticatedUser();
		LicensedUser licensedUser = service.getLicensedUser(user);
		boolean shouldlAcceptLicenseAgreement = licensedUser == null;
		if (!shouldlAcceptLicenseAgreement)
			shouldlAcceptLicenseAgreement = licensedUser.getLicenseVersionSigned() != service.getLicenseAgreement()
			        .getVersion();
		return shouldlAcceptLicenseAgreement;
	}
	
	private boolean everyPageExcept(String page1, String page2, String page3, String page4, PageRequest pageRequest) {
		String pageName = pageRequest.getPageName();
		return !pageName.equals(page1) && !pageName.equals(page2) && !pageName.equals(page3) && !pageName.equals(page4);
	}
}
