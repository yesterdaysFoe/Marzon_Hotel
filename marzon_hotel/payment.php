<?php include 'config.php'; ?>
<?php
if (!isset($_POST['submit'])) {
    $errmsg_arr = array();
    $errflag = false;

    function createRandomPassword() {
        $chars = "abcdefghijkmnopqrstuvwxyz023456789";

        srand((double) microtime() * 1000000);

        $i = 0;
        $pass = '';

        while ($i <= 5) {
            $num = rand() % 33;
            $tmp = substr($chars, $num, 1);
            $pass = $pass . $tmp;
            $i++;
        }

        return $pass;
    }

    $confirmation = createRandomPassword();
    $arival = filter_input(INPUT_POST, 'start');
    $departure = filter_input(INPUT_POST, 'end');
    $adults = filter_input(INPUT_POST, 'adult');
    $child = filter_input(INPUT_POST, 'child');
    $nroom = filter_input(INPUT_POST, 'n_room');
    $roomid = filter_input(INPUT_POST, 'rm_id');
    $result = filter_input(INPUT_POST, 'result');
    $name = filter_input(INPUT_POST, 'name');
    $last = filter_input(INPUT_POST, 'last');
    $address = filter_input(INPUT_POST, 'address');
    $city = filter_input(INPUT_POST, 'city');
    $zip = filter_input(INPUT_POST, 'zip');
    $country = filter_input(INPUT_POST, 'country');

    $email = filter_input(INPUT_POST, 'email');
    $cnumber = filter_input(INPUT_POST, 'cnumber');
    $stat = 'Active';
    $result1 = mysql_query("SELECT * FROM room where room_id='$roomid'");
    while ($row = mysql_fetch_array($result1)) {
        $rate = $row['rate'];
        $type = $row['type'];
    }
    $payable = $rate * $result * $nroom;
    $sql = "INSERT INTO reservation (arrival, departure, adults, child, result, room_id, no_room, firstname, lastname, city, zip, province, country, email, contact, password, payable, confirmation)
VALUES
('$arival','$departure','$adults','$child','$result','$roomid','$nroom','$name','$last','$city','$zip','$address','$country','$email','$cnumber','$password','$payable','$confirmation')";
    mysql_query("INSERT INTO roominventory (arrival, departure, qty_reserve, room_id, confirmation, status) VALUES ('$arival','$departure','$nroom','$roomid','$confirmation','$stat')");
    if (!mysql_query($sql, $db_con)) {
        die('Error: ' . mysql_error());
    }
}
mysql_close($db_con)
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Tamera Plaza Inn</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <!--sa poip up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />

        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="src/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                $('a[rel*=facebox]').facebox({
                    loadingImage: 'src/loading.gif',
                    closeImage: 'src/closelabel.png'
                });
            })
        </script>
    </head>

    <body>

        <!-- TOP -->
        <?php include 'top.php'; ?>

        <!-- CONTENT -->
        <div id="content">
            <div id="leftPan"></div>

            <!-- wala pa error trapping -->
            <br/>
            <div id="featured">
                <br />
                <center><h2 style="color: green;">Reservation successfully sent! 
                        <br/> Please wait for approval message sent to your email and a confirmation call!</h2>
                </center>
                <br/>
                <br/>

                <form action="https://www.sandbox.paypal.com/cgi-bin/webscr"  method="post">
                    <!-- the cmd parameter is set to _xclick for a Buy Now button -->
                    <center>
                        <table width="460" border="0" id="resevationTable" style="font-size: 14px;">
                            <tr>
                                <td colspan="2"><div align="center" class="style1">RESERVATION DETAILS </div></td>
                            </tr>
                            <tr>
                                <td colspan="2"><div align="center"></div></td>
                            </tr>
                            <tr>
                                <td width="140"><div align="right">Check In Date: </div></td>
                                <td width="304"><?php echo $arival; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">Check Out Date: </div></td>
                                <td><?php echo $departure; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">First Name: </div></td>
                                <td><?php echo $name; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">Lastname: </div></td>
                                <td><?php echo $last; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">Address: </div></td>
                                <td><?php echo $address; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">City: </div></td>
                                <td><?php echo $city; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">ZIP Code: </div></td>
                                <td><?php echo $zip; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">Country: </div></td>
                                <td><?php echo $country; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">Email: </div></td>
                                <td><?php echo $email; ?></td>
                            </tr>
                            <tr>
                                <td><div align="right">Contact Number: </div></td>
                                <td><?php echo $cnumber; ?></td>
                            </tr>

                            <tr>
                                <td><div align="right">Confirmation Code: </div></td>
                                <td ><h2 style="color: #FF6600;"><?php echo $confirmation ?></h2></td>
                            </tr>
                        </table>
                    </center>
                    <br/>
                    <center>
                        <input type="button" value="OK" onClick="window.location.href = 'index.php'">
                    </center>
                </form>

            </div>

            <div class="clear"> </div>

        </div>

        <!-- FOOTER -->
        <?php include 'footer.php'; ?>
        <!-- BOTTOM -->
        <?php include 'bottom.php'; ?>

    </body>
</html>
