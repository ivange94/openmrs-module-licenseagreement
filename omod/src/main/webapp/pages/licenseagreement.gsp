<% ui.decorateWith("appui", "standardEmrPage") %>

<script>
    var jq = jQuery;

    jq(function() {

        jq("#accept-license-button").on('click', function (event) {
            event.preventDefault();

            if (jq("#agreement-checkbox").is(':checked')) {
                jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "acceptLicenseAgreement") }',
                    {
                        'action': 'accept',
                    })
                    .success(function (data) {
                        window.location.replace(data.redirectUrl);
                    })
                    .error(function (xhr, status, err) {
                        alert('AJAX error ' + err);
                    })
            } else {
                jq('.errorMessage').text("You need to check the checkbox to accept All Terms and Conditions.");
                jq('.errorMessage').show();
            }
        });

        jq("deny-license-button").on('click', function(event) {
            event.preventDefault();

            jq.getJSON('${ ui.actionLink("licenseagreement", "licenseAgreement", "acceptLicenseAgreement") }',
                {
                    'action': 'decline',
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
                jq("#license-url").attr('href', data.url);
            })
            .error(function(xhr, status, err) {
                alert('AJAX error ' + err);
            })
    });
</script>

<style>
.errorMessage {
    margin-top: 3px;
    margin-bottom: 5px;
    border: 1px dashed lightgrey;
    padding: 2px 2px 2px 18px;
    background-color: red;
}
</style>

<div>
    <span class="errorMessage" hidden></span>
    <h3>${ui.message("licenseagreement.licenseagreement.legal.header")}</h3>
    <form>
        <textarea name="licenseBody" id="licenseBody" disabled>${ui.message("licenseagreement.licenseagreement.legal.notification")}</textarea><br>
        <input type="checkbox" id="agreement-checkbox"> I accept All <a href="" id="license-url">Terms and Conditions</a><br><br>
        <a class="button cancel" id="deny-license-button" href='${ui.pageLink("licenseagreement", "denied")}'>${ui.message("licenseagreement.legal.app.button.decline")}</a>&nbsp;<input type="submit" class="confirm" value="${ui.message("licenseagreement.legal.app.button.accept")}" id="accept-license-button">
    </form>
</div>
