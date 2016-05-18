<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Marzon Hotel | Cancel Reservation</title>
        <!--sa poip up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="css/example.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="src/facebox.js" type="text/javascript"></script>
        <script src="js/custom/cancel_reservation.js" type="text/javascript"></script>
    </head>

    <body>
        <form id="form1" name="cancelpage" method="post" action="cancelindex-exec.php" onsubmit="return validateForm()">
            <label>Confirmation Number
                <input type="text" name="confirmation" id="ed" />
                <br />
                <input type="checkbox" name="cancelpolicy" value="checkbox" />
                I agree the <a rel="facebox" href="cancelationpolicy.php">cancelation policy</a> of this hotel<br />
            </label>
            <p><input name="" type="submit" value="Cancel" id="button1" />&nbsp;</p>
        </form>
    </body>
</html>
