<?php include 'config.php'; ?>
<?php
$confirm = $_POST['confirm'];
$result = mysql_query("SELECT * FROM reservation where confirmation='$confirm'");
while ($row = mysql_fetch_array($result)) {
    $arival = $row['arrival'];
    $departure = $row['departure'];
    $adults = $row['adults'];
    $child = $row['child'];
    $confirmation = $row['confirmation'];
    $firstname = $row['firstname'];
    $lastname = $row['lastname'];
    $city = $row['city'];
    $zip = $row['zip'];
    $province = $row['province'];
    $country = $row['country'];
    $email = $row['email'];
    $contact = $row['contact'];
    $username = $row['username'];
    $password = $row['password'];
    $results = $row['result'];
    $no_room = $row['no_room'];
    $room_id = $row['room_id'];
    $result = mysql_query("SELECT * FROM room WHERE room_id='$room_id'");

    while ($row1 = mysql_fetch_array($result)) {
        $roomtype = $row1['type'];
    }
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Tamera Plaza Inn</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
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
        <!--sa error trapping-->

        <script type="text/javascript">
            function validateForm()
            {

                var y=document.forms["personal"]["name"].value;
                var a=document.forms["personal"]["last"].value;
                var b=document.forms["personal"]["address"].value;
                var c=document.forms["personal"]["city"].value;
                var d=document.forms["personal"]["zip"].value;
                var e=document.forms["personal"]["country"].value;
                var f=document.forms["personal"]["email"].value;
                var g=document.forms["personal"]["cemail"].value;
                var x=document.forms["personal"]["cnumber"].value;
                var i=document.forms["personal"]["password"].value;

                var code=document.forms["personal"]["codetype"].value;
                var codetype=document.forms["personal"]["codetypecopy"].value;

                var atpos=f.indexOf("@");
                var dotpos=f.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=f.length)
                {
                    alert("Not a valid e-mail address");
                    return false;
                }

                if( codetype != code ) {
                    alert("Invalid Code Pls. try again........ thank you");
                    return false;
                }



                if( f != g ) {
                    alert("email does not match");
                    return false;
                } 
                if ((a=="Lastname" || a=="") || (b=="Address" || b=="") || (c=="City" || c=="") || (d=="ZIP Code" || d=="") || (e=="Country" || e=="") || (f=="Email" || f=="") || (g=="Confirm Email" || g=="")|| (x=="Contact Number" || x=="") || (y=="Firstname" || y=="") || (i=="Password" || i==""))
                {
                    alert("all field are required!");
                    return false;
                }
 
                if (document.personal.condition.checked == false)
                {
                    alert ('pls. agree the term and condition of this hotel');
                    return false;
                }
                else
                {
                    return true;
                }
            }
        </script>
        <script type="text/javascript">
            function validateForm1()
            {
                var r=document.forms["log"]["email"].value;
                var g=document.forms["log"]["password"].value;
                var atpos=r.indexOf("@");
                var dotpos=r.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=r.length)
                {
                    alert("Not a valid e-mail address");
                    return false;
                }  

                if ((a==null || a==""))
                {
                    alert("pls.enter your password");
                    return false;
                }
            }
        </script>

        <!--sa input that accept number only-->
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
    
                //called when key is pressed in textbox
                $("#zip").keypress(function (e)  
                { 
                    //if the letter is not digit then display error and don't type anything
                    if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
                    {
                        //display error message
                        $("#errmsg").html("Number Only").show().fadeOut("slow"); 
                        return false;
                    }	
                });
                $("#cnumber").keypress(function (a)  
                { 
                    //if the letter is not digit then display error and don't type anything
                    if( a.which!=8 && a.which!=0 && (a.which<48 || a.which>57))
                    {
                        //display error message
                        $("#errmsg1").html("Number Only").show().fadeOut("slow"); 
                        return false;
                    }	
                });

            });
        </script>

        </script>


        <script type="text/javascript">

            //Created / Generates the captcha function    
            function DrawCaptcha()
            {
                var a = Math.ceil(Math.random() * 10)+ '';
                var b = Math.ceil(Math.random() * 10)+ '';       
                var c = Math.ceil(Math.random() * 10)+ '';  
                var d = Math.ceil(Math.random() * 10)+ '';  
                var e = Math.ceil(Math.random() * 10)+ '';  
                var f = Math.ceil(Math.random() * 10)+ '';  
                var g = Math.ceil(Math.random() * 10)+ '';  
                var code = a + b +  c +  d +  e +  f +  g;
                document.getElementById("txtCaptcha").value = code
            }

    
 
        </script>
        <style type="text/css">
            <!--
            #Layer1 {
                position:absolute;
                width:249px;
                height:27px;
                z-index:1;
                top: 327px;
                margin-left:3px;
            }
            .style1 {
                font-size: 12px;
                font-weight: bold;
            }
            -->
        </style>
    </head>

    <body onload="DrawCaptcha();">

        <!-- TOP -->
        <div id="top1"><a href="index.php"><img src="images/logo.jpg" border="0" style="margin-top:27px; margin-left:20px;" /></a></div>
        <div id="top">

            <ul class="menu">
                <li class="home"><a href="index.php">Home</a></li>
                <li class="about"><a href="about.php">About</a></li>
                <li class="contacts"><a href="contact.php">Contacts</a></li>
                <li class="renting"><a href="gallery.php">GALLERY</a></li>
                <li class="selling"><a href="rates.php">RATES</a></li>


            </ul>


        </div>




        <!-- HEADER -->
        <!-- CONTENT -->
        <div id="content">

            <div id="leftPan">

                <div id="services">
                    <h2>RESERVATION DETAILS </h2>
                    <p>
                        <ul>
                            Check In Date : <?php echo $arival; ?><br />
                            Check Out Date : <?php echo $departure; ?><br />
                            Adults : <?php echo $adults; ?><br />
                            Child : <?php echo $child; ?><br />
                            Number of Rooms : <?php echo $no_room; ?><br />
                            Room Type : <?php echo $roomtype; ?><br />
                            Number Of Nights : <?php echo $results; ?><br />
                        </ul>
                    </p>
                    </p>
                </div>

                <div id="services">
                    <iframe src="https://www.facebook.com/plugins/like.php?href=tameraplazainn.x10.mx"
                            scrolling="no" frameborder="0"
                            style="border:none; width:180px; height:250px"></iframe>
                </div>



            </div><br /><br /><br />
            <div id="featured">

                <br />
                <div>
                    <form action="saveedit.php" method="post" style="margin-top: -31px;" onsubmit="return validateForm()" name="personal">
                        <input name="start" type="hidden" value="<?php echo $arival; ?>" />
                        <input name="end" type="hidden" value="<?php echo $departure; ?>" />
                        <input name="adult" type="hidden" value="<?php echo $adults; ?>" />
                        <input name="child" type="hidden" value="<?php echo $child; ?>" />
                        <input name="n_room" type="hidden" value="<?php echo $no_room; ?>" />
                        <input name="rm_id" type="hidden" value="<?php echo $room_id; ?>" />
                        <input name="confirmation" type="hidden" value="<?php echo $confirmation; ?>" />
                        <input name="result" type="hidden" value="<?php echo $results; ?>" />
                </div><br />
                <table width="502" border="0">
                    <tr>
                        <td width="143"><div align="right" class="style1">First Name:</div></td>
                        <td width="219"><input name="name" type="text" class="ed" id="name" size="40"  value="<?php echo $firstname; ?>" /></td>
                        <td width="126">&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Last Name:</div></td>
                        <td><input name="last" type="text" class="ed" id="last" size="40" value="<?php echo $lastname; ?>" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Address:</div></td>
                        <td><input name="address" type="text" class="ed" id="address" size="40" value="<?php echo $province; ?>" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">City:</div></td>
                        <td><input name="city" type="text" class="ed" id="city" size="40" value="<?php echo $city; ?>" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Country:</div></td>
                        <td><input name="country" type="text" class="ed" id="country" size="40" value="<?php echo $country; ?>" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Zip Code:</div></td>
                        <td><input name="zip" type="text" class="ed" id="zip" size="25" value="<?php echo $zip; ?>" /><span id="errmsg"></span> </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Email:</div></td>
                        <td><input name="email" type="text" class="ed" id="email" size="40" value="<?php echo $email; ?>" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Retype Email:</div></td>
                        <td><input name="cemail" type="text" class="ed" id="cemail" size="40" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Password:</div></td>
                        <td><input name="password" type="password" class="ed" id="password" size="40" value="<?php echo $password; ?>" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Contact Number:</div></td>
                        <td><input name="cnumber" type="text" class="ed" id="cnumber" size="25" value="<?php echo $contact; ?>" /><span id="errmsg1"></span></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right"></div></td>
                        <td colspan="2"><label>
                                <input type="checkbox" name="condition" value="checkbox" />
                                <span class="style1"><small>i agree the <a rel="facebox" href="terms_condition.php">terms and condition</a> of this hotel</small></span></label></td>
                    </tr>
                    <tr>
                        <td><div align="right"></div></td>
                        <td><div id="Layer1">
                                <input type="text" name="codetypecopy" id="txtCaptcha" 
                                       style="text-align:center; border:none; font-weight:bold; font-family:Modern; font-size:20px; font-size: 20px; width: 270px;" />
                                <img src="captcha.png" width="270" height="30" style="margin-top:-30px;" /></div></td>
                        <td><a href="#" onclick="DrawCaptcha();"><img src="images/refresh.png" alt="refresh" border="0" style="margin-top:5px; margin-left:5px;" /></a></td>
                    </tr>
                    <tr>
                        <td><div align="right" class="style1">Enter the Code here: </div></td>
                        <td><input name="codetype" type="text" class="ed" id="code" size="40" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div align="right"></div></td>
                        <td><input name="but" type="submit" value="Confirm" /></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>

                </form>
            </div>
            <div class="clear"></div>

        </div>

        <!-- FOOTER -->

        <div id="footer">

            <img src="images/call.jpg" alt="" width="156" height="37" />

            <p><a href="index.php">HOME</a> |<a href="about.php"> ABOUT US </a>|<a href="contact.php"> CONTACTS </a>|<a href="gallery.php"> GALLERY </a>|<a href="rates.php"> ROOM RATES </a></p>
        </div>

        <!-- BOTTOM -->

        <div id="bottom">

            <p>Copyright &copy; Tamera Plaza Inn. Designed by <a href="#" target="_blank">begie</a></p>
        </div>

    </body>
</html>
