<?php include 'config.php'; ?>
<?php

if (isset($_POST['yes'])) {
    $messages_id = $_POST['id'];
    //$result = mysql_query("SELECT * FROM friendlist WHERE friends_id = $member_id");
    mysql_query("DELETE FROM room WHERE room_id='$messages_id'");
    header("location: home_admin.php#4");
    exit();

    mysql_close($db_con);
}
if (isset($_POST['no'])) {

    header("location: room.php");
    exit();
}
?>