<?php include 'config.php'; ?>
<?php

if (isset($_GET['id'])) {

    $id = $_GET['id'];
    $result = mysql_query("SELECT * FROM room WHERE room_id = $id");

    while ($row = mysql_fetch_array($result)) {
        echo "<img width=200 height=180 alt='Unable to View' src='" . $row["image"] . "'>";
    }
    echo '<form action="editpicexec.php" method="post" enctype="multipart/form-data">';

    //echo "<img width=200 height=180 alt='Unable to View' src='" . $row1["image"] . "'>";
    echo '<br>';
    echo '<input type="hidden" name="firstname" value="' . $_GET['id'] . '">';
    echo 'Select Image';
    echo '<br>';
    echo '<input type="file" name="image">' . '<br>';
    echo '<input type="submit" value="Upload">';
    echo '</form>';
}
?>
			
