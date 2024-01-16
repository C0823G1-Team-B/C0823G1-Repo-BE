$(document).ready(function () {
    $.get("/api/user", function (data, status) {
        console.log("Get hit")
        let userMail = $("#gmail");
        console.log(status)
        console.log(data)
        if (status === "success") {
            console.log("status ok")
            document.getElementById("gmail").innerText = "Gmail: " + data.email;
            userMail.css("display", "block");
        } else {
            console.log("status not ok")
            userMail.css("display", "none");
        }
    })
})