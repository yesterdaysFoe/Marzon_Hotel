<?php include 'config.php'; ?>
<?php

if (isset($_GET['id'])) {

    $messages_id = $_GET['id'];
    $result = mysql_query("SELECT * FROM amenities WHERE amenities_id = $messages_id");
    while ($row = mysql_fetch_array($result)) {
        echo '<div style="width: 280px;" align="center">';
        echo "<img alt='Unable to View' src='" . $row["pic"] . "'>";
        echo '</div>';
        echo '<div style="width: 280px;" align="justify">';
        echo $row['des'];
        echo '</div>';
    }

    mysql_close($db_con);
}
?>