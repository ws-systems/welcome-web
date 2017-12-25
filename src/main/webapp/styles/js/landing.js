function onLandingMatrixButtonClick(e) {
    $('button.active')
        .removeClass('active btn-secondary')
        .addClass("btn-light");
    $(e)
        .addClass('active btn-secondary')
        .removeClass("btn-light");

    $('.domain-entry').fadeIn("slow");
}

function onLandingSubdomainFieldChange(e) {
    if ($(e).val() !== "")
        $('#landing-continue').prop('disabled', false);
    else
        $('#landing-continue').prop('disabled', true);
}

function onLandingDomainChoiceSubmit(e) {
    var $subdomain = $('#subdomain-input');
    var new_url = location.protocol + "//" + $subdomain.val() + '.' + $subdomain.data("stem") + $($('button.active')[0]).data("destination");
    window.location.href = new_url;
    return false;
}