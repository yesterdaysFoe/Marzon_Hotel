<?php
if ($_SESSION['SESS_FIRST_NAME'] == "admin") {
    echo '<ul class="menu">';
    echo '<a href="room.php" style="padding-left: 10px;">Rooms</a>';
    echo '<a href="admin_index.php" style="padding-left: 10px;">Logout</a>';
    echo '</ul>';
    
//      echo '<li class="a"><a href="viewcomment.php">1<img src="images/viewcomment.png" alt="view" /></a></li>';
//            echo '<li class="monitor"><a href="home_admin.php">2<img src="images/monitor.png" alt="monitor" /></a></li>';
//            echo '<li class="c"><a href="reports.php">3<img src="images/report.png" alt="report" /></a></li>';
//            echo '<li class="d"><a href="roominventory.php">4<img src="images/inventory.png" alt="inventory" /></a></li>';
//            echo '<li class="e"><a href="room.php">5<img src="images/maintenance.png" alt="maintenance" /></a></li>';
//            echo '<li class="f"><a href="admin_index.php"><img src="images/logout.png" alt="logout" /></a></li>';
}
?>
