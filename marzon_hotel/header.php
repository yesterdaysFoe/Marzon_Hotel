<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div id="header">

            <div id="formPan">
                <h2 class="style2">RESERVATION FORM </h2>

                <form method="post" action="testing.php" name="index" onsubmit="return validateForm()">
                    <div style="margin-top:20px; margin-left:10px;">
                        <table width="186" border="0">
                            <tr>
                                <td width="69"><div align="right">
                                        <label>Start Date : </label>
                                    </div></td>
                                <td width="101"><input type="text" class="w8em format-d-m-y highlight-days-67 range-low-today" name="start" id="sd" value="" maxlength="10" readonly="readonly" /></td>
                            </tr>
                            <tr>
                                <td><div align="right">
                                        <label>End Date : </label>
                                    </div></td>
                                <td><input type="text" class="w8em format-d-m-y highlight-days-67 range-low-today" name="end" id="ed" value="" maxlength="10" readonly="readonly" /></td>
                            </tr>
                            <tr>
                                <td><div align="right">
                                        <label>Adult : </label>
                                    </div></td>
                                <td><select name="adult" class="ed" >
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td><div align="right">
                                        <div align="right">
                                            <label>Child : </label>
                                        </div>
                                    </div></td>
                                <td><select name="child" class="ed">
                                        <option>0</option>
                                        <option>1</option>
                                        <option>2</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td><div align="right"></div></td>
                                <td><input style="height: 30px; width: 120px;" name="Input" type="image" src="images/book_now.jpg" value="Check Availability" id="button" /></td>
                            </tr>
                            <tr>
                                <td colspan="2"><div align="right"><a rel="facebox" href="modifyindex.php">Modify</a> / <a href="cancelindex.php">Cancel</a> Reservation </div></td>
                            </tr>
                        </table> 
                    </div>    
                </form>
            </div>

            <div id="mainimgPan">
                <div id="mainimg">
                    <div id="slider" class="nivoSlider" style="float:right;">
                        <img src="hotel_pics/1.jpg" alt="" />
                        <img src="hotel_pics/2.jpg" alt="" />
                        <img src="hotel_pics/3.jpg" alt="" />
                        <img src="hotel_pics/4.jpg" alt="" />   
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
