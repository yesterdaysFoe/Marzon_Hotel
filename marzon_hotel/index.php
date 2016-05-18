<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Marzon Hotel Kalibo</title>
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
                var x=document.forms["index"]["start"].value;
                if (x==null || x=="")
                {
                    alert("you must enter your check in Date(click the calendar icon)");
                    return false;
                }
                var y=document.forms["index"]["end"].value;
                if (y==null || y=="")
                {
                    alert("you must enter your check out Date(click the calendar icon)");
                    return false;
                }
            }
        </script>
        <!--end sa nivo slider-->
        <style type="text/css">
        </style>

        <!--sa poip up-->
        <link href="src/facebox.css" media="screen" rel="stylesheet" type="text/css" />

        <script src="lib/jquery.js" type="text/javascript"></script>
        <script src="src/facebox.js" type="text/javascript"></script>
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                $('a[rel*=facebox]').facebox({
                    loadingImage : 'src/loading.gif',
                    closeImage   : 'src/closelabel.png'
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

                <div id="featured">
                    <h1 style="color: #A77D3D;">WELCOME TO MARZON HOTEL</h1>
                    <p>
                        At Marzon Hotel Kalibo our dedication to ideal travel experience is highly important. Our personalized service, combined with authentic and graceful surroundings of the best quality, exemplifies a home away from home for those who know and value the best. Our employees continue to build a depth of dependability, confidence and connection with our guest- a bond we will consistently uphold, now and always.
                        <br/>
                        <br/>
                        Marzon Hotel is located in Sta. Monica Andagao, Kalibo, Aklan besides Latte Cafe. It is 5 minutes drive from Kalibo International Airport and 5 minutes drive to the Kalibo town as well. A perfect place to stay- located at the quiet side of the town yet accessible to the main road. A convenient setting for your day-to-day itinerary.
                    </p>
						<a href="hotel_pics/IMG_0974_adj3f.jpg"><img alt="IMG_0974_adj3f" src="hotel_pics/IMG_0974_adj3f.jpg" width="680px" height="400"/></a>
                    <p>
						Marzon Hotel Kalibo, was established in 2009. The design was based on the idea of housing airline personnel. 
						The airline industry needed an accessible accommodation near the airport and other famous land marks in Kalibo. 
						The hotel started operating during the time wherein the Kalibo Domestic Airport became Kalibo International Airport (Pangkalibutan nga Paeuparan it Kalibo). 
						In addition to this, Tourism business in Boracay is fast flourishing and there is an increase in demand of top class facilities around the town.
						<br/>
                        <br/>
                        At present we have 25 newly built and spacious rooms that cater to diverse clientele; from airline crew, business men who frequently travel around the country, and both local and international tourist who merely come to enjoy the splendor of Kalibo.
                    </p>
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
