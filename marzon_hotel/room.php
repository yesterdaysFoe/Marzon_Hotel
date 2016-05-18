<?php
require_once('auth.php');
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Marzon Hotel</title>
        <link href="css/ble.css" rel="stylesheet" type="text/css" />
        <!--sa poip up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="src/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                $('a[rel*=facebox]').facebox({
                    loadingImage : 'src/loading.gif',
                    closeImage   : 'src/closelabel.png'
                })
            })
        </script>

        <!--sa validate from-->
        <script type="text/javascript">
            function validateForm()
            {

                var y=document.forms["login"]["user"].value;
                var a=document.forms["login"]["password"].value;
                if ((y==null || y==""))
                {
                    alert("you must enter your username");
                    return false;
                }
                if ((a==null || a==""))
                {
                    alert("you must enter your password");
                    return false;
                }
 

            }
        </script>
        <link rel="stylesheet" href="./febe/style.css" type="text/css" media="screen" charset="utf-8">

            <script src="./js/application.js" type="text/javascript" charset="utf-8"></script>
    </head>

    <body>
        <?php include 'admin_login.php'; ?>
        <div style="width:600px; margin:0 auto; position:relative; border:3px solid rgba(0,0,0,0); -webkit-border-radius:5px; -moz-border-radius:5px; border-radius:5px; -webkit-box-shadow:0 0 18px rgba(0,0,0,0.4); -moz-box-shadow:0 0 18px rgba(0,0,0,0.4); box-shadow:0 0 18px rgba(0,0,0,0.4); margin-top:10%;">
            <br /><label style="margin-left:12px;">Filter</label> <input type="text" name="filter" value="" id="filter" /><br />

            <table cellpadding="1" cellspacing="1" id="resultTable">
                <thead>
                    <tr bgcolor="#33FF00" style="margin-bottom:10px;">
                        <th>Type</th>
                        <th>Rate</th>
                        <th>Descripton</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
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

                    $result3 = mysql_query("SELECT * FROM room");


                    while ($row3 = mysql_fetch_array($result3)) {
                        echo '<tr>';
                        echo '<td>' . $row3['type'] . '</td>';
                        echo '<td>Php';
                        $klp = $row3['rate'];
                        echo formatMoney($klp, true);
                        echo '</td>';
                        echo '<td>' . $row3['description'] . '</td>';
                        echo '<td>';
                        echo'<a rel="facebox" href=editpic.php?id=' . $row3["room_id"] . '>' . '<img width=72 height=52 alt="Unable to View" src=' . $row3["image"] . '>' . '</a>';

                        echo '</td>';
                        echo '<td>' . $row3['qty'] . '</td>';
                        echo '<td>';
                        echo'<a rel="facebox" href=editroom.php?id=' . $row3["room_id"] . '>' . 'Edit' . '</a>';
                        echo ' | ';
                        echo'<a rel="facebox" href=deleteroom.php?id=' . $row3["room_id"] . '>' . 'Delete' . '</a>';
                        echo '</td>';
                        echo '</tr>';
                    }
                    ?>
                    <a rel="facebox" href="addroom.php">Add Room</a>
                </tbody>
            </table>


        </div>
    </body>
</html>
