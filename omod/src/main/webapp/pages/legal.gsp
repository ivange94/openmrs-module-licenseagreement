<% ui.decorateWith("appui", "standardEmrPage") %>
<script>
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("licenseagreement.legal.app.label") }", link: "${ ui.pageLink("licenseagreement", "legal") }" }
    ];
</script>
<div>
    <a class="button app big" href="manageLicenseAgreement.page">
        <i class="icon-group"></i>
        ${ui.message("licenseagreement.legal.app.agreement.label")}
    </a>
    <a class="button app big" href='${ui.pageLink("licenseagreement", "signed")}'>
        <i class="icon-group"></i>
        ${ui.message("licenseagreement.legal.app.last.user.access.label")}
    </a>
</div>
