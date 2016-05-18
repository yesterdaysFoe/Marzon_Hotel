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
        <!--SA ERROR TRAPPING-->
        <script type="text/javascript">
            function validateForm1()
            {
                var a=document.forms["contact"]["name"].value;
                if (a==null || a=="")
                {
                    alert("Pls. Enter your Name");
                    return false;
                }
                var b=document.forms["contact"]["email"].value;
                if (b==null || b=="")
                {
                    alert("Pls. Enter your Email");
                    return false;
                }
                var c=document.forms["contact"]["message"].value;
                if (c==null || c=="")
                {
                    alert("Pls. Enter your Message");
                    return false;
                }
                var atpos=b.indexOf("@");
                var dotpos=b.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=b.length)
                {
                    alert("Not a valid e-mail address");
                    return false;
                }
            }
        </script>

        <!--END SA ERROR TRAPPING-->
        <style type="text/css">
            <!--
            .style2 {
                font-size: 1.4em;
                font-weight: bold;
                color: #FFFFFF;
            }
            .style3 {
                font-size: 14px;
                font-weight: bold;
            }
            -->
        </style>
        <!--sa pop up-->
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

                <div id="welcome">
                    <h1 style="color: #A77D3D;">MARZON HOTEL MAP</h1>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d3906.9761978023644!2d122.37340185!3d11.696098249999999!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sph!4v1394553283116" width="600" height="450" frameborder="0" style="border:0"></iframe>
                </div>
                <br/>
                <div class="spacerline"></div>

                <div id="featured">
                    <h1 style="color: #A77D3D;">MARZON HOTEL CONTACTS</h1>
                    <div id="conleft">
                        <span class="style3">Corporate Address</span>  <br />
                        <br />
                        <div align="justify" style="width:200px;">If you have any questions, comments or concerns about our services, please don't hesitate to contact us. We ensure that we will make your stay here an enjoyable and pleasant experience.</div><br />
                        <br />
                        E-mail:  reservation@marzonhotelkalibo.com<br />
                        Phone: +63-36-268-2188<br />
                        Fax: +63-36-268-2187<br />
                        Address: Sta. Monica, Andagao, Kalibo, Aklan, Philippines
                    </div>

                    <div id="conright">
                        <span class="style3">&nbsp;&nbsp;&nbsp;Quick Contact</span>  <br /><br />

                        <form action="savecomment.php" method="post" onsubmit="return validateForm1()" name="contact">
                            <div style="margin-left:12px;">Name:<br />
                                <input name="name" type="text" class="ed" />
                                <br />
                                Email Address:<br />
                                <input name="email" type="text" class="ed" />
                                <br />
                                Messages:<br />
                                <textarea name="message" rows="5" cols="23" class="ed"></textarea>
                                <br />
                                <input name="Input" type="submit" value="Submit" id="button1" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
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
