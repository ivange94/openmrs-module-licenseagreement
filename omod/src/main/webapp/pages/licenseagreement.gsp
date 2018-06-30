<% ui.decorateWith("appui", "standardEmrPage") %>

<script>
    var jq = jQuery;

    jq(function() {
        function toggleBody(status) {
            jq("#licenseBody").prop("disabled", status);
        }

        jq("#edit-license-button").on('click', function(event) {
            event.preventDefault();
            toggleBody(false)
        });

        jq("#save-license-button").on('click', function (event) {
            event.preventDefault();
            toggleBody(true);

            jq.post('${ui.actionLink("licenseagreement", "licenseAgreement", "acceptLicenseAgreement")}'

            );
        });
    });
</script>
<div>
    <h3>End User License Agreement</h3>
    <form>
        <textarea name="licenseBody" id="licenseBody" cols="30" rows="20" disabled></textarea><br>
        <button class="cancel" id="edit-license-button">Decline</button>&nbsp;<input type="submit" class="confirm" value="Accept" id="save-license-button">
    </form>
</div>
