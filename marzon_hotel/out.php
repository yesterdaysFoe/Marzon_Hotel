<?php include 'config.php'; ?>
<?php

if (isset($_GET['id'])) {
    $messages_id = $_GET['id'];
    $result3 = mysql_query("SELECT * FROM reservation where confirmation ='$messages_id'");

    while ($row3 = mysql_fetch_array($result3)) {
        $res = $row3['reservation_id'];
    }
    $update1 = mysql_query("UPDATE reservation SET status ='out' WHERE reservation_id ='$messages_id'");
    $update3 = mysql_query("UPDATE payment_notification SET status ='CompletedOut' WHERE item_number ='$messages_id'");
    $update2 = mysql_query("UPDATE roominventory SET status ='out' WHERE confirmation = '$messages_id'");
    header("location: home_admin.php#1");

    exit();

    mysql_close($con);
}
?>