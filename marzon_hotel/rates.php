<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Marzon Hotel</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />


        <!--sa nivo slider-->
        <link rel="stylesheet" href="nivo-slider.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />

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
        <style type="text/css">
            <!--
            .style1 {font-size: 12px}
            .style2 {
                font-size: 1.4em;
                font-weight: bold;
                color: #FFFFFF;
            }
            .style3 {	font-size: 16px;
                      font-weight: bold;
            }
            -->
        </style>
        <!--sa poip up-->
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

        <!-- HEADER -->
        <?php include 'header.php'; ?>

        <!-- CONTENT -->

        <div id="content">

            <!-- SERVICES -->
            <?php include 'services.php'; ?>

            <div id="featured">
                <?php include 'config.php'; ?>
                <form action="personnal_info.php" method="post" onsubmit="return validateForm()" name="room">

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
                        echo '<tr>';
                        echo '<td>' . 'Maximum Child Capacity: ' . $row['max_child'] . '</td>';
                        echo '</tr>';
                        echo '<tr>';
                        echo '<td>' . 'Maximum Adult Capacity: ' . $row['max_adult'] . '</td>';
                        echo '</tr>';

                        if ($available > 0) {
                            echo '<tr>';
                            echo '<td>';
                            echo '<input name="roomid" type="image" value="' . $row["room_id"] . '" src="images/reseve.jpg" alt="Reserve" align="middle" width="60" height="30" onclick="setDifference(this.form);" />';
                            echo '</td>';
                            echo '</tr>';
                        }

                        echo '</table>';
                    }

                    mysql_close($db_con);
                    ?>

                    <input type="hidden" name="result" id="result" />
                </form>
            </div>

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
