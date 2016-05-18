function validateForm()
{
    var a = document.forms["cancelpage"]["confirmation"].value;
    if ((a == null || a == ""))
    {
        alert("enter your confirmation number to cancel you reservation");
        return false;
    }

    if (document.cancelpage.cancelpolicy.checked == false)
    {
        alert('pls. agree the cancelation policy of this hotel');
        return false;
    }
    else
    {
        return true;
    }
}


