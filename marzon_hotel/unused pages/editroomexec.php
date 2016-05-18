<?php include 'config.php'; ?>
<?php

session_start();

$roomid = $_POST['roomid'];
$roomtype = $_POST['roomtype'];
$roomrate = $_POST['roomrate'];
$description = $_POST['description'];
$qty = $_POST['qty'];

mysql_query("UPDATE room SET type='$roomtype', rate='$roomrate', description='$description', qty='$qty' WHERE room_id='$roomid'");
header("location: room.php");
mysql_close($db_con);
?> 