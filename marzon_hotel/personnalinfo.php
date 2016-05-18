<?php include 'config.php'; ?>
<?php
$arival = $_POST['start'];
$departure = $_POST['end'];
$adults = $_POST['adult'];
$child = $_POST['child'];
$roomid = $_POST['roomid'];
$results = $_POST['result'];
$result = mysql_query("SELECT * FROM room WHERE room_id='$roomid'");

while ($row1 = mysql_fetch_array($result)) {
    $roomtype = $row1['type'];
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Marzon Hotel</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <!--sa poip up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="js/custom/personal_info.js" type="text/javascript"></script>
    </head>

    <body>
        <!-- TOP -->
        <?php include 'top.php'; ?>
        <!-- CONTENT -->
        <div id="content">

            <div id="leftPan">

                <div id="services">
                    <h2>RESERVATION DETAILS </h2>
                    <p>
                        <ul>
                            Check In Date :<?php echo $arival; ?><br />
                            Check Out Date :<?php echo $departure; ?>  <br />
                            Adults : <?php echo $adults; ?><br />
                            Child :<?php echo $child; ?><br />

                            Room Type : <?php echo $roomtype; ?><br />
                            Number Of Nights : <?php echo $results; ?><br />
                        </ul>
                    </p>
                </div>
            </div>

            <div id="featured">
                <BR/>
                <center><h1 style="color: #A77D3D;">GENERAL INFORMATION</h1></center>
                <form id="personal" name="personal" action="payment.php" method="post" style="height: 89px; margin-top: -31px;" onsubmit="return validateForm()">
                    <input name="start" type="hidden" value="<?php echo $arival; ?>" />
                    <input name="end" type="hidden" value="<?php echo $departure; ?>" />
                    <input name="adult" type="hidden" value="<?php echo $adults; ?>" />
                    <input name="child" type="hidden" value="<?php echo $child; ?>" />
                    <input name="n_room" type="hidden" value="<?php echo $no_rooms; ?>" />
                    <input name="rm_id" type="hidden" value="<?php echo $roomid; ?>" />
                    <input name="rm_type" type="hidden" value="<?php echo $roomtype; ?>" />
                    <input name="result" type="hidden" value="<?php echo $results; ?>" />

                    <br /> <br /> <br /> <br />
                    <div align="center"><span class="style1"> All field mark with this symbol (<span class="style3">*</span>) are required to be fill up</span></div>
                    <br/>
                    <table width="502" border="0">
                        <tr>
                            <td width="133"><div align="right" class="style1">First Name:<span class="style3">*</span></div></td>
                            <td width="262"><input name="name" type="text" class="ed" id="name" size="35" /></td>
                            <td width="93">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Last Name: <span class="style3">*</span></div></td>
                            <td><input name="last" type="text" class="ed" id="last" size="35" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Address:<span class="style3">* </span></div></td>
                            <td><input name="address" type="text" class="ed" id="address" size="35" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">City: <span class="style3">* </span></div></td>
                            <td><input name="city" type="text" class="ed" id="city" size="35" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Country:  <span class="style3">* </span></div></td>
                            <td><input name="country" type="text" class="ed" id="country" size="35" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Zip Code: <span class="style3">* </span></div></td>
                            <td><input name="zip" type="text" class="ed" id="zip" size="25" />
                                <span id="errmsg"></span> </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Email:<span class="style3">* </span></div></td>
                            <td><input name="email" type="text" class="ed" id="email" size="35" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Retype Email:<span class="style3">*</span></div></td>
                            <td><input name="cemail" type="text" class="ed" id="cemail" size="35" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right" class="style1">Contact Number:</span><span class="style3">* </span></div></td>
                            <td><input name="cnumber" type="text" class="ed" id="cnumber" size="25" />
                                <span id="errmsg1"></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="right"></div></td>
                            <td colspan="2"><label>
                                     <input type="checkbox" name="termsandcondition" value="checkbox" />
                                    <span class="style1"><small>I agree the <a href="terms_condition.php" target="_blank" style="color: #A77D3D">terms and condition</a> of this hotel</small></span></label></td>
                        </tr>
                        <td>&nbsp;</td>
                        <tr style="margin-top: 200px;">
                            <td><div align="right"></div></td>
                            <td><input name="but" type="submit" value="Send Reservation!" /></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="clear"></div>
        </div>

        <!-- FOOTER -->
        <?php include 'footer.php'; ?>
        <!-- BOTTOM -->
        <?php include 'bottom.php'; ?>

    </body>
</html>
