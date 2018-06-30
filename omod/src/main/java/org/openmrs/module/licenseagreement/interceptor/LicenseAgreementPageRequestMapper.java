package org.openmrs.module.licenseagreement.interceptor;

import org.openmrs.User;
import org.openmrs.api.context.Context;
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
		if (notTryingToLogin(pageRequest) && licenseAgreementNotAccepted()) {
			pageRequest.setProviderNameOverride("licenseagreement");
			pageRequest.setPageName("licenseagreement");
			return true;
		}
		return false;
	}
	
	private boolean notTryingToLogin(PageRequest pageRequest) {
		return !pageRequest.getPageName().equals("login");
	}
	
	private boolean licenseAgreementNotAccepted() {
		User user = Context.getAuthenticatedUser();
		return service.getLicensedUser(user) == null;
	}
}
