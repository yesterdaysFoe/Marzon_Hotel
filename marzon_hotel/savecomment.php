<?php include 'config.php'; ?>
<?php

$name = $_POST['name'];
$email = $_POST['email'];
$message = $_POST['message'];


$sql = mysql_query("INSERT INTO comment (name, email, content) VALUES ('$name','$email','$message')");
header("location: contact.php");
mysql_close($con)
?>

