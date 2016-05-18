<?php include 'config.php'; ?>
<?php

$email = $_POST['email'];
$user = $_POST['user'];
$result1 = mysql_query("SELECT * FROM user where username='$user'");
while ($row = mysql_fetch_array($result1)) {
    $password = $row['password'];
}
$mail_To = $email;
$mail_Subject = "Recovered Password from Tamera Plaza Inn";
$mail_Body = "Password: $password";
mail($mail_To, $mail_Subject, $mail_Body);

header('Location: admin_index.php');
?>