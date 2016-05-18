$("#personal_info_form").submit(function(event)
{
    console.log("--------->");
    alert("Handler for .submit() called.");
    event.preventDefault();
});

function validateForm() {
    var startDate = document.forms["personal"]["start"].value;
    var endDate = document.forms["personal"]["end"].value;
    var firstName = document.forms["personal"]["name"].value;
    var lastName = document.forms["personal"]["last"].value;
    var address = document.forms["personal"]["address"].value;
    var city = document.forms["personal"]["city"].value;
    var country = document.forms["personal"]["country"].value;
    var email = document.forms["personal"]["email"].value;
    var confirmEmail = document.forms["personal"]["cemail"].value;
    var contactNumber = document.forms["personal"]["cnumber"].value;

    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
        alert("Not a valid e-mail address");
        return false;
    }

    if (email !== confirmEmail) {
        alert("email does not match");
        return false;
    }
    if ((firstName == "Lastname" || firstName == "") || (address == "Address" || address == "") || (city == "City" || city == "") || (city == "ZIP Code" || city == "") || (country == "Country" || country == "") || (email == "Email" || email == "") || (confirmEmail == "Confirm Email" || confirmEmail == "") || (contactNumber == "Contact Number" || contactNumber == "") || (lastName == "Firstname" || lastName == "")) {
        alert("all field are required!");
        return false;
    }
    if ((startDate == "" || endDate == "")) {
        alert("Invalid Dates!");
        return false;
    }

    if (document.personal.termsandcondition.checked == false) {
        alert('pls. agree the term and condition of this hotel');
        return false;
    }
    return true;
}

//Created / Generates the captcha function    
function DrawCaptcha()
{
    var a = Math.ceil(Math.random() * 10) + '';
    var b = Math.ceil(Math.random() * 10) + '';
    var c = Math.ceil(Math.random() * 10) + '';
    var d = Math.ceil(Math.random() * 10) + '';
    var e = Math.ceil(Math.random() * 10) + '';
    var f = Math.ceil(Math.random() * 10) + '';
    var g = Math.ceil(Math.random() * 10) + '';
    var code = a + b + c + d + e + f + g;
    document.getElementById("txtCaptcha").value = code
}

//< !--sa input that accept number only-- >
$(document).ready(function() {
    //called when key is pressed in textbox
    $("#zip").keypress(function(e)
    {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57))
        {
            //display error message
            $("#errmsg").html("Number Only").show().fadeOut("slow");
            return false;
        }
    });
    $("#cnumber").keypress(function(a)
    {
        //if the letter is not digit then display error and don't type anything
        if (a.which != 8 && a.which != 0 && (a.which < 48 || a.which > 57))
        {
            //display error message
            $("#errmsg1").html("Number Only").show().fadeOut("slow");
            return false;
        }
    });
});

