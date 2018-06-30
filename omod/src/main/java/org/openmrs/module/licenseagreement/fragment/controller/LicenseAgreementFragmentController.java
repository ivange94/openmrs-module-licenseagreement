package org.openmrs.module.licenseagreement.fragment.controller;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.licenseagreement.LicensedUser;
import org.openmrs.module.licenseagreement.api.LicenseAgreementModuleService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class LicenseAgreementFragmentController {
	
	private LicenseAgreementModuleService service = Context.getService(LicenseAgreementModuleService.class);
	
	public SimpleObject acceptLicenseAgreement(@RequestParam("action") String action, UiUtils uiUtils) {
		User user = Context.getAuthenticatedUser();
		SimpleObject response = new SimpleObject();
		if ("accept".equals(action)) {
			service.licenseUser(user);
			response.put("redirectUrl", uiUtils.pageLink("referenceapplication", "home"));
		} else {
			Context.logout();
			response.put("redirectUrl", uiUtils.pageLink("referenceapplication", "login"));
		}
		return response;
	}
	
	public SimpleObject getLicenseAgreement() {
		String licenseBody = service.getLicenseAgreement().getBody();
		SimpleObject response = new SimpleObject();
		response.put("licenseBody", licenseBody);
		return response;
	}
}
