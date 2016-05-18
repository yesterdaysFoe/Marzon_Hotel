<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Marzon Hotel</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <!--sa error trapping-->
        <script type="text/javascript">
            function validateForm()
            {

                var y = document.forms["room"]["no_rooms"].value;

                if ((y == null || y == ""))
                {
                    alert("all field are required!");
                    return false;
                }

            }
        </script>

        <!--sa minus date-->
        <script type="text/javascript">
            // Error checking kept to a minimum for brevity

            function setDifference(frm) {
                var dtElem1 = frm.elements['start'];
                var dtElem2 = frm.elements['end'];
                var resultElem = frm.elements['result'];

                // Return if no such element exists
                if (!dtElem1 || !dtElem2 || !resultElem) {
                    return;
                }

                //assuming that the delimiter for dt time picker is a '/'.
                var x = dtElem1.value;
                var y = dtElem2.value;
                var arr1 = x.split('/');
                var arr2 = y.split('/');

                // If any problem with input exists, return with an error msg
                if (!arr1 || !arr2 || arr1.length != 3 || arr2.length != 3) {
                    resultElem.value = "Invalid Input";
                    return;
                }

                var dt1 = new Date();
                dt1.setFullYear(arr1[2], arr1[1], arr1[0]);
                var dt2 = new Date();
                dt2.setFullYear(arr2[2], arr2[1], arr2[0]);

                resultElem.value = (dt2.getTime() - dt1.getTime()) / (60 * 60 * 24 * 1000);
            }
        </script>



        <!--sa input that accept number only-->
        <SCRIPT language=Javascript>
            <!--
            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }
            //-->
        </SCRIPT>

        <!--end sa nivo slider-->
        <?php
        $arival = $_POST['start'];
        $departure = $_POST['end'];
        $adults = $_POST['adult'];
        $child = $_POST['child'];
        ?>

        <style type="text/css">
            <!--
            .style2 {
                font-size: 12px;
                font-weight: bold;
            }
            -->
        </style>
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

                        </ul>
                    </p>
                    </p>
                </div>

                <div id="services">

                </div>

            </div>
            <div id="featured"><br />
                <div>
                    <form action="personnalinfo.php" method="post" onsubmit="return validateForm()" name="room">
                        <input name="start" type="hidden" value="<?php echo $arival; ?>" />
                        <input name="end" type="hidden" value="<?php echo $departure; ?>" />
                        <input name="adult" type="hidden" value="<?php echo $adults; ?>" />
                        <input name="child" type="hidden" value="<?php echo $child; ?>" />

                </div><br />
                <?php include 'config.php'; ?>
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

                $result = mysql_query("SELECT * FROM room");

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
                    }
                    $available = (int) $totalNumberOfRooms - (int) $rowww;


                    $query = mysql_query("SELECT sum(qty_reserve) FROM roominventory where arrival <= '$arival' and departure >= '$departure' and room_id='$a'");
                    while ($rows = mysql_fetch_array($query)) {
                        $inogbuwin = $rows['sum(qty_reserve)'];
                    }
                    $angavil = $row['qty'] - $inogbuwin;


                    echo '<table width="490" border="0">';
                    echo '<tr>';
                    echo '<td colspan="2">&nbsp;<span class="style2">' . $row['type'] . '<label><strong> ---- Available: ' . $available . '</span></td>';
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
                    echo '<tr>';
                    echo '<td>' . 'Maximum Child Capacity: ' . $row['max_child'] . '</td>';
                    echo '</tr>';
                    echo '<tr>';
                    echo '<td>' . 'Maximum Adult Capacity: ' . $row['max_adult'] . '</td>';
                    echo '</tr>';
                    echo '<tr>';
                    echo '<td>';
                    echo '<input name="roomid" type="image" value="' . $row["room_id"] . '" src="images/reseve.jpg" alt="Reserve" align="middle" width="60" height="30" onclick="setDifference(this.form);" />';
                    echo '</td>';
                    echo '</tr>';
                    echo '</table>';
                }

                mysql_close($db_con);
                ?>
                <input type="hidden" name="result" id="result" />
                </form>
            </div>
            <div class="clear"></div>

        </div>

        <!-- FOOTER -->
        <?php include 'footer.php'; ?>
        <!-- BOTTOM -->
        <?php include 'bottom.php'; ?>

        <script type="text/javascript" src="scripts/jquery-1.4.3.min.js"></script>
        <script type="text/javascript" src="jquery.nivo.slider.pack.js"></script>
        <script type="text/javascript">
                        $(window).load(function() {
                            $('#slider').nivoSlider();
                        });
        </script>
    </body>
</html>
