<% ui.decorateWith("appui", "standardEmrPage") %>

<script>
    var jq = jQuery;

    jq(function() {

        jq("#accept-license-button").on('click', function (event) {
            event.preventDefault();

            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "acceptLicenseAgreement") }',
                {
                    'action': 'accept',
                })
                .success(function(data) {
                    window.location.replace(data.redirectUrl);
                })
                .error(function(xhr, status, err) {
                    alert('AJAX error ' + err);
                })
        });

        jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "getLicenseAgreement") }',
            {
            })
            .success(function(data) {
                jq("#licenseBody").val(data.licenseBody);
            })
            .error(function(xhr, status, err) {
                alert('AJAX error ' + err);
            })
    });
</script>
<div>
    <h3>End User License Agreement</h3>
    <form>
        <textarea name="licenseBody" id="licenseBody" cols="30" rows="20" disabled></textarea><br>
        <a class="button cancel" id="deny-license-button" href='${ui.pageLink("licenseagreement", "denied")}'>Decline</a>&nbsp;<input type="submit" class="confirm" value="Accept" id="accept-license-button">
    </form>
</div>
