<% ui.decorateWith("appui", "standardEmrPage") %>

<script>
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("licenseagreement.legal.app.label") }", link: "${ ui.pageLink("licenseagreement", "legal") }" },
        { label: "${ ui.message("licenseagreement.signed.page.table.header.lastAccess") }", link: "${ ui.pageLink("licenseagreement", "signed") }" }
    ];

    jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "getUsers") }',
        {
        })
        .success(function(data) {
            var table = jq("#lastAccessTable tbody");

            jq.each(data, function (idx, elem) {
                table.append(
                    "<tr>" +
                        "<td>" + elem.name + "</td>" +
                        "<td>" + elem.username + "</td>" +
                        "<td>" + elem.license + "</td>" +
                        "<td>" + elem.lastAccess + "</td>" +
                    "</tr>")
            });
        })
        .error(function(xhr, status, err) {
            alert('AJAX error ' + err);
        })
</script>
<div>
    <h3>${ui.message("licenseagreement.signed.page.header")}</h3>
    <table id="lastAccessTable">
        <thead>
                <th>${ui.message("licenseagreement.signed.page.table.header.name")}</th>
                <th>${ui.message("licenseagreement.signed.page.table.header.username")}</th>
                <th>${ui.message("licenseagreement.signed.page.table.header.agreement")}</th>
                <th>${ui.message("licenseagreement.signed.page.table.header.lastAccess")}</th>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
