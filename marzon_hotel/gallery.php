<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Marzon Hotel</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />


        <!--sa nivo slider-->
        <link rel="stylesheet" href="nivo-slider.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />

        <!--sa calendar-->	
        <script type="text/javascript" src="js/datepicker.js"></script>
        <link href="css/demo.css"       rel="stylesheet" type="text/css" />
        <link href="css/datepicker.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="js/date_selection_script.js" />

        <!--sa error trapping-->
        <script type="text/javascript">
            function validateForm()
            {
                var x = document.forms["index"]["start"].value;
                if (x == null || x == "")
                {
                    alert("you must enter your check in Date(click the calendar icon)");
                    return false;
                }
                var y = document.forms["index"]["end"].value;
                if (y == null || y == "")
                {
                    alert("you must enter your check out Date(click the calendar icon)");
                    return false;
                }
            }
        </script>
        <!--end sa nivo slider-->
        <style type="text/css">
            <!--
            .style1 {font-size: 12px}
            .style2 {
                font-size: 1.4em;
                font-weight: bold;
                color: #FFFFFF;
            }
            -->
        </style>
        <!--sa poip up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />

        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="src/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                $('a[rel*=facebox]').facebox({
                    loadingImage: 'src/loading.gif',
                    closeImage: 'src/closelabel.png'
                })
            })
        </script>
    </head>

    <body>

        <!-- TOP -->
        <?php include 'top.php'; ?>

        <!-- HEADER -->
        <?php include 'header.php'; ?>

        <!-- CONTENT -->

        <div id="content">

            <!-- SERVICES -->
            <?php include 'services.php'; ?>

            <div id="rightPan">
                <div id="fcontainer">

                    <div id="featured">

                        <ul class="hoverbox">
                            <li>
                                <h1 style="color: #FFF;">Standard Twin</h1>
                                <a href="pics/1.JPG" rel="facebox"><img src="pics/1.JPG" alt="description" /></a> 
                                <a href="pics/2.JPG" rel="facebox"><img src="pics/2.JPG" alt="description" /></a> 
                                <a href="pics/3.JPG" rel="facebox"><img src="pics/3.JPG" alt="description" /></a> 
                                <a href="pics/4.JPG" rel="facebox"><img src="pics/4.JPG" alt="description" /></a> 
                            </li>
                            <li>
                                <h1 style="color: #FFF;">Standard Queen</h1>
                                <a href="pics/5.JPG" rel="facebox"><img src="pics/5.JPG" alt="description" /></a> 
                                <a href="pics/6.JPG" rel="facebox"><img src="pics/6.JPG" alt="description" /></a> 
                                <a href="pics/7.JPG" rel="facebox"><img src="pics/7.JPG" alt="description" /></a> 
                                <a href="pics/8.JPG" rel="facebox"><img src="pics/8.JPG" alt="description" /></a> 
                                <a href="pics/9.JPG" rel="facebox"><img src="pics/9.JPG" alt="description" /></a> 
                                <a href="pics/10.JPG" rel="facebox"><img src="pics/10.JPG" alt="description" /></a> 
                            </li>
                            <li>
                                <h1 style="color: #FFF;">Standard Room</h1>
                                <a href="pics/11.JPG" rel="facebox"><img src="pics/11.JPG" alt="description" /></a> 
                                <a href="pics/12.JPG" rel="facebox"><img src="pics/12.JPG" alt="description" /></a> 
                                <a href="pics/13.JPG" rel="facebox"><img src="pics/13.JPG" alt="description" /></a> 
                                <a href="pics/14.JPG" rel="facebox"><img src="pics/14.JPG" alt="description" /></a> 
                            </li>
                            <li>
                                <h1 style="color: #FFF;">Deluxe Room</h1>
                                <a href="pics/15.JPG" rel="facebox"><img src="pics/15.JPG" alt="description" /></a> 
                                <a href="pics/16.JPG" rel="facebox"><img src="pics/16.JPG" alt="description" /></a> 
                                <a href="pics/17.JPG" rel="facebox"><img src="pics/17.JPG" alt="description" /></a> 
                                <a href="pics/18.JPG" rel="facebox"><img src="pics/18.JPG" alt="description" /></a> 
                                <a href="pics/19.JPG" rel="facebox"><img src="pics/19.JPG" alt="description" /></a> 
                            </li>
                            <li>
                                <h1 style="color: #FFF;">Family Room</h1>
                                <a href="pics/20.JPG" rel="facebox"><img src="pics/20.JPG" alt="description" /></a>
                                <a href="pics/21.JPG" rel="facebox"><img src="pics/21.JPG" alt="description" /></a>
                                <a href="pics/22.JPG" rel="facebox"><img src="pics/22.JPG" alt="description" /></a>
                                <a href="pics/23.JPG" rel="facebox"><img src="pics/23.JPG" alt="description" /></a>
                            </li>
                            <li>
                                <h1 style="color: #FFF;">Suite Room</h1>
                                <a href="pics/24.JPG" rel="facebox"><img src="pics/24.JPG" alt="description" /></a>
                                <a href="pics/25.JPG" rel="facebox"><img src="pics/25.JPG" alt="description" /></a>
                                <a href="pics/26.JPG" rel="facebox"><img src="pics/26.JPG" alt="description" /></a>
                                <a href="pics/27.JPG" rel="facebox"><img src="pics/27.JPG" alt="description" /></a>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>

        <!-- FOOTER -->
        <?php include 'footer.php'; ?>
        <!-- BOTTOM -->
        <?php include 'bottom.php'; ?>

        <script type="text/javascript" src="scripts/jquery-1.4.3.min.js"></script>
        <script type="text/javascript" src="jquery.nivo.slider.pack.js"></script>
        <script type="text/javascript">
            $(window).load(function() {
                $('#slider').nivoSlider();
            });
        </script>
    </body>
</html>
