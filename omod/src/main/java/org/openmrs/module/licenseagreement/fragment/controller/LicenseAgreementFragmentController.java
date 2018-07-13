package org.openmrs.module.licenseagreement.fragment.controller;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.licenseagreement.api.LicenseAgreementModuleService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
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
		String url = service.getLicenseAgreement().getUrl();
		SimpleObject response = new SimpleObject();
		response.put("url", url);
		return response;
	}
	
	public SimpleObject saveLicenseAgreement(@RequestParam("url") String url) {
		service.updateLicenseAgreement(url);
		return getLicenseAgreement();
	}
	
	public List<SimpleObject> getUsers() {
		List<User> users = Context.getUserService().getAllUsers();
		List<SimpleObject> result = new ArrayList<SimpleObject>();
		for (User user : users) {
			SimpleObject tmp = new SimpleObject();
			tmp.put("name", getName(user));
			tmp.put("username", user.getUsername());
			tmp.put("license", getStatus(user));
			tmp.put("lastAccess", new Date());
			result.add(tmp);
		}
		return result;
	}
	
	private String getName(User user) {
		return user.getGivenName() + " " + user.getFamilyName();
	}
	
	private String getStatus(User user) {
		boolean accepted = Context.getService(LicenseAgreementModuleService.class).hasAcceptedLicensedAgreement(user);
		return accepted ? "Accepted" : "Not Accepted";
	}
}
