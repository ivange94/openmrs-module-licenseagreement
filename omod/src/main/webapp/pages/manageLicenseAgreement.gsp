<% ui.decorateWith("appui", "standardEmrPage") %>

<openmrs:require privilege="" otherwise="/login.htm" redirect="/admin/concepts/conceptClass.form" />

<script>
    var jq = jQuery;

    jq(function() {
        function getLicenseAgreement() {
            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "getLicenseAgreement") }',
                {
                })
                .success(function(data) {
                    jq("#licenseBody").val(data.licenseBody);
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
    <p id="message">End user license agreement updated</p>
    <h3>End User License Agreement</h3>
    <form>
        <textarea name="licenseBody" id="licenseBody" cols="30" rows="20" disabled></textarea><br>
        <button class="button cancel" id="edit-license-button" href='${ui.pageLink("licenseagreement", "denied")}'>Edit</button>&nbsp;<input type="submit" class="confirm" value="Save" id="save-license-button">
    </form>
</div>
