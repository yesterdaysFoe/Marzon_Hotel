<?php require_once "phpuploader/include_phpuploader.php" ?>
<?php session_start(); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>
            Form - Start uploading manually
        </title>
        <link href="demo.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript">
            function doStart()
            {
                var uploadobj = document.getElementById('myuploader');
                if (uploadobj.getqueuecount() > 0)
                {
                    uploadobj.startupload();
                }
                else
                {
                    alert("Please browse files for upload");
                }
            }
        </script>

    </head>
    <body>
        <div class="demo">     
            <h2>Change Website Header</h2>
            <p>This page enables you to changes the page header. 
                To make change take effect please make sure that you are uploading files with file name 1.jpg, 2.jpg, 3.jpg and 4.jpg, and image dimension should be 748px x 225px.</p>
            <P>Allowed file types: <span style="color:red">jpg</span></p>

            <!-- do not need enctype="multipart/form-data" -->
            <form id="form1" method="POST">
                <?php
                $uploader = new PhpUploader();
                $uploader->MaxSizeKB = 10240;
                $uploader->Name = "myuploader";
                $uploader->InsertText = "Select multiple files (Max 10M)";
                $uploader->AllowedFileExtensions = "*.jpg";
                $uploader->MultipleFilesUpload = true;
                $uploader->ManualStartUpload = true;
                $uploader->SaveDirectory="../hotel_pics";
                $uploader->MaxFilesLimit=4;
                $uploader->Render();
                ?>
                <br /><br /><br />
                <button id="submitbutton" onclick="doStart();
                                        return false;">Start Uploading Files</button>

            </form>

            <br/><br/><br/>
            <?php
            $fileguidlist = @$_POST["myuploader"];
            if ($fileguidlist) {
                $guidlist = explode("/", $fileguidlist);

                echo("<div style='font-family:Fixedsys;'>");
                echo("Uploaded ");
                echo(count($guidlist));
                echo(" files:");
                echo("</div>");
                echo("<hr/>");

                foreach ($guidlist as $fileguid) {
                    $mvcfile = $uploader->GetUploadedFile($fileguid);
                    if ($mvcfile) {
                        echo("<div style='font-family:Fixedsys;border-bottom:dashed 1px gray;padding:6px;'>");
                        echo("FileName: ");
                        echo($mvcfile->FileName);
                        echo("<br/>FileSize: ");
                        echo($mvcfile->FileSize . " b");
                        //		echo("<br/>FilePath: ");
                        //		echo($mvcfile->FilePath);
                        echo("</div>");

                        //Moves the uploaded file to a new location.
                        //$mvcfile->MoveTo("/uploads");
                        //Copys the uploaded file to a new location.
                        //$mvcfile->CopyTo("/uploads");
                        //Deletes this instance.
                        //$mvcfile->Delete();
                    }
                }
            }
            ?>

        </div>
    </body>
</html>