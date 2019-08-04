<html>
    <head>
        <title>Report</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> 
        <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>      
        <link rel="stylesheet" href="/css/styles.css">
        <script type="text/javascript" src="/js/script.js"></script> 
        <script src =  
            "https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"> 
        </script>
    </head>

    <body>
        <div class='pageHeading'>
            <h1>Report</h1> 
        </div>
        
        <div class='path'>
            <h5 id='pathHeading'>File: </h5>
        </div>

        <table class='table'>
            <tbody>         
        <?php
            $filePath = $_GET["filePath"];            

            $file_lines = file($filePath);            
            $fileAbsolutePath = array();

            foreach ($file_lines as $line) {
                $text = "";

                if (empty($fileAbsolutePath)) {
                    preg_match("([A-Z]:.*.java)", $line, $fileAbsolutePath);  
                }

                if (preg_match("([A-Z]:.*.java)", $line))
                {
                    $text =  preg_replace("(.*([A-Z]:.*.java))", "Line", $line);
                    $lineComponent = substr($text, 0, strpos($text, " "));
                    $messageComponent = substr($text, strpos($text, " ")+1);
                    echo "<tr><td>".$lineComponent."</td>";
                    echo "<td>".$messageComponent."</td></tr>";
                }                                
            }                                    
            
            function getFilePath() {
                global $fileAbsolutePath;
                $finalPathString = str_replace("\\", "\\\\", $fileAbsolutePath[0]);
                return $finalPathString;
            }
        ?>

            </tbody>
        </table> 

        <script>
            $( window ).on( "load", function() {
                document.getElementById('pathHeading').innerHTML += 
                    "<?php print getFilePath(); ?>";
            });                       
        </script>

    </body>
</html>