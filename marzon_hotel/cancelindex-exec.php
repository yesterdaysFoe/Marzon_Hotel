<?php include 'config.php'; ?>
<?php

$confirmation = $_POST['confirmation'];
//$result = mysql_query("SELECT * FROM friendlist WHERE friends_id = $member_id");
mysql_query("DELETE FROM reservation WHERE confirmation='$confirmation'");
header("location: index.php");
exit();

mysql_close($db_con);
?>