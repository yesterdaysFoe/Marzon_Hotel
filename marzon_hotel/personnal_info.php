<?php include 'config.php'; ?>
<?php
//$arival = $_POST['start'];
//$departure = $_POST['end'];
//$adults = $_POST['adult'];
//$child = $_POST['child'];
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

        <!--sa calendar-->	
        <script type="text/javascript" src="js/datepicker.js"></script>
        <link href="css/demo.css"       rel="stylesheet" type="text/css" />
        <link href="css/datepicker.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/date_selection_script.js" />
        <!--sa error trapping-->
        <script type="text/javascript">
            function validateForm()
            {
                var x = document.forms["index"]["start"].value;
                if (x == null || x == "")
                {
                    alert("you must enter your check in Date(click the calendar icon)");
                    return false;
                }
                var y = document.forms["index"]["end"].value;
                if (y == null || y == "")
                {
                    alert("you must enter your check out Date(click the calendar icon)");
                    return false;
                }
            }
        </script>
        <!--end sa nivo slider-->
        <!--SA ERROR TRAPPING-->
        <script type="text/javascript">
            function validateForm1()
            {
                var a = document.forms["contact"]["name"].value;
                if (a == null || a == "")
                {
                    alert("Pls. Enter your Name");
                    return false;
                }
                var b = document.forms["contact"]["email"].value;
                if (b == null || b == "")
                {
                    alert("Pls. Enter your Email");
                    return false;
                }
                var c = document.forms["contact"]["message"].value;
                if (c == null || c == "")
                {
                    alert("Pls. Enter your Message");
                    return false;
                }
                var atpos = b.indexOf("@");
                var dotpos = b.lastIndexOf(".");
                if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= b.length)
                {
                    alert("Not a valid e-mail address");
                    return false;
                }
            }
        </script>

        <!--END SA ERROR TRAPPING-->
        <style type="text/css">
            <!--
            .style2 {
                font-size: 1.4em;
                font-weight: bold;
                color: #FFFFFF;
            }
            .style3 {
                font-size: 14px;
                font-weight: bold;
            }
            -->
        </style>
        <!--sa pop up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />

        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="src/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                $('a[rel*=facebox]').facebox({
                    loadingImage: 'src/loading.gif',
                    closeImage: 'src/closelabel.png'
                })
            })
        </script>
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

                    </p>
                </div>
            </div>

            <div id="featured">
                <BR/>
                <center><h1 style="color: #A77D3D;">GENERAL INFORMATION</h1></center>
                <form id="personal" name="personal" action="payment.php" method="post" style="height: 89px; margin-top: -31px;" onsubmit="return validateForm()">
                    <!--<input name="start" type="hidden" value="<?php echo $arival; ?>" />-->
                    <!--<input name="end" type="hidden" value="<?php echo $departure; ?>" />-->
                    <input name="adult" type="hidden" value="<?php echo $adults; ?>" />
                    <input name="child" type="hidden" value="<?php echo $child; ?>" />
                    <input name="n_room" type="hidden" value="<?php echo $no_rooms; ?>" />
                    <input name="rm_id" type="hidden" value="<?php echo $roomid; ?>" />
                    <input name="rm_type" type="hidden" value="<?php echo $roomtype; ?>" />
                    <input name="result" type="hidden" value="<?php echo $results; ?>" />
                    <?php

                    function formatMoney($number, $fractional = false) {
                        if ($fractional) {
                            $number = sprintf('%.2f', $number);
                        }
                        while (true) {
                            $replaced = preg_replace('/(-?\d+)(\d\d\d)/', '$1,$2', $number);
                            if ($replaced != $number) {
                                $number = $replaced;
                            } else {
                                break;
                            }
                        }
                        return $number;
                    }

                    $result = mysql_query("SELECT * FROM room WHERE room_id='$roomid'");

                    while ($row = mysql_fetch_array($result)) {
                        $a = $row['room_id'];
                        $totalRoomCount = mysql_query("SELECT * FROM rooms WHERE room_type_id='$a'");
                        $totalNumberOfRooms = mysql_num_rows($totalRoomCount);
                        $date = date('Y-m-d');
                        $notAvailableRoom = mysql_query("SELECT count(distinct r.id) AS coo FROM `room_status` rs 
                                                    INNER JOIN rooms r ON rs.room_id = r.id
                                                    INNER JOIN room rm ON r.room_type_id = rm.room_id
                                                    WHERE rs.start_date = '$date' AND rm.room_id = '$a'
                                                    GROUP BY r.id");
                        $rowww = 0;
                        while ($count = mysql_fetch_array($notAvailableRoom)) {
                            $rowww = $count['coo'];
                            echo $rowww;
                        }
                        $available = (int) $totalNumberOfRooms - (int) $rowww;
                        echo '<br></br>';
                        echo '<table width="490" border="0">';
                        echo '<tr>';
                        echo '<td colspan="2">&nbsp;<span class="style2">' . $row['type'] . '<label><strong> ---- Available: ' . $available . ' </strong></label>' . '</span></td>';
                        echo '</tr>';
                        echo '<tr>';
                        echo '<td width="150" rowspan="5">' . '<img width=150 height=105 alt="Unable to View" src="' . $row["image"] . '"/>' . '</td>';
                        echo '<td width="340">';
                        $rt = $row['rate'];
                        echo 'Php ';
                        echo formatMoney($rt, true);
                        echo '</td>';
                        echo '</tr>';
                        echo '<tr>';
                        echo '<td>' . 'Room Description: ' . $row['description'] . '</td>';
                        echo '</tr>';
                        echo '</table>';
                    }

                    mysql_close($db_con);
                    ?>

                    <br /> <br />
                    <div align="center"><span class="style1"> All field mark with this symbol (<span class="style3">*</span>) are required to be fill up</span></div>
                    <br/>
                    <table width="502" border="0">
                        <tr>
                            <td width="69"><div align="right">
                                    <label>Start Date : </label>
                                </div></td>
                            <td width="101"><input type="text" class="w8em format-d-m-y highlight-days-67 range-low-today" name="start" id="sd" value="" maxlength="10" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td><div align="right">
                                    <label>End Date : </label>
                                </div></td>
                            <td><input type="text" class="w8em format-d-m-y highlight-days-67 range-low-today" name="end" id="ed" value="" maxlength="10" readonly="readonly" /></td>
                        </tr>
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
