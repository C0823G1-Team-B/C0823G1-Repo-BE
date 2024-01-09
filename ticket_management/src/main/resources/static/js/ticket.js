$("#checkout_form").submit(function (e) {
    e.preventDefault();
    let form = $(this);
    let actionUrl = form.attr('action') + "?" + form.serialize();
    console.log(actionUrl);
})