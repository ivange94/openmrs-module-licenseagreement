<% ui.decorateWith("appui", "standardEmrPage") %>

<openmrs:require privilege="Edit License Agreement" otherwise="/login.htm" redirect="/module/licenseagreement/manageLicenseAgreement.page"/>

<script>
    var jq = jQuery;

    jq(function() {
        function getLicenseAgreement() {
            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "getLicenseAgreement") }',
                {
                })
                .success(function(data) {
                    jq("licenseBody").val(data.licenseBody);
                })
                .error(function(xhr, status, err) {
                    alert('AJAX error ' + err);
                })
        }

        jq("#edit-license-button").on('click', function(event) {
            event.preventDefault();
            jq("#licenseBody").prop("disabled", false);
            jq("#message").hide();
        });

        jq("#save-license-button").on('click', function(event) {
            event.preventDefault();

            jq("#licenseBody").prop("disabled", true);
            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "saveLicenseAgreement") }',
                {
                    'body': jq("#licenseBody").val()
                })
                .success(function(data) {
                    jq("#licenseBody").val(data.licenseBody);
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
    <p id="message">${ui.message("licenseagreement.legal.app.update.description")}</p>
    <h3>${ui.message("licenseagreement.legal.app.label")}</h3>
    <form>
        <textarea name="licenseBody" id="licenseBody" cols="30" disabled>${ui.message("licenseagreement.legal.app.update.message.description")}</textarea><br>
        <label for="license-link">${ui.message("licenseagreement.legal.app.license.link.label")}</label>
        <input type="text" id="license-link">
        <button class="button cancel" id="cancel-edit-license-button">${ui.message("licenseagreement.legal.app.button.cancel")}</button>&nbsp;<input type="submit" class="confirm" value="${ui.message("licenseagreement.legal.app.button.update")}" id="save-license-button">
    </form>
</div>
