<% ui.decorateWith("appui", "standardEmrPage") %>

<% context.requirePrivilege("Edit License Agreement")%>

<script>
    var jq = jQuery;

    jq(function() {
        function getLicenseAgreement() {
            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "getLicenseAgreement") }',
                {
                })
                .success(function(data) {
                    jq("#license-link").val(data.url);
                })
                .error(function(xhr, status, err) {
                    alert('AJAX error ' + err);
                })
        }

        jq("#edit-license-button").on('click', function(event) {
            event.preventDefault();
            jq("#license-link").prop("disabled", false);
            jq("#message").hide();
        });

        jq("#save-license-button").on('click', function(event) {
            event.preventDefault();

            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "saveLicenseAgreement") }',
                {
                    'url': jq("#license-link").val()
                })
                .success(function(data) {
                    jq("#url").val(data.licenseBody);
                    jq("#message").show();
                })
                .error(function(xhr, status, err) {
                    alert('AJAX error ' + err);
                })
        });

        jq("#message").hide();

        getLicenseAgreement();
    });
</script>
<style>
    #message {
        margin-top: 3px;
        margin-bottom: 5px;
        border: 1px dashed lightgrey;
        padding: 2px 2px 2px 18px;
        background-color: lightyellow;
    }
</style>
<div>
    <p id="message">${ui.message("licenseagreement.legal.app.update.message")}</p>
    <h3>${ui.message("licenseagreement.legal.app.label")}</h3>
    <form>
        <textarea name="licenseBody" id="licenseBody" cols="30" disabled>${ui.message("licenseagreement.legal.app.update.message.description")}</textarea><br>
        <label for="license-link">${ui.message("licenseagreement.legal.app.license.link.label")}</label>
        <input type="text" id="license-link">
        <button class="button cancel" id="cancel-edit-license-button">${ui.message("licenseagreement.legal.app.button.cancel")}</button>&nbsp;<input type="submit" class="confirm" value="${ui.message("licenseagreement.legal.app.button.update")}" id="save-license-button">
    </form>
</div>
