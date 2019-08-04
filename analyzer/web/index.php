<html>
    <head>
        <title>Pie Chart</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> 
        <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>      
        <link rel="stylesheet" href="/analyzer/web/css/styles.css"> 
    </head>

    <body>
        <div class='pageHeading'>
            <h1>Static Code Analyzer</h1>
        </div>        

        <div class='path'>
            <h6>
                <?php
                    $ini_array = parse_ini_file("./../../sca.properties");
                    print_r('Target: '.$ini_array['path']);
                ?>
            </h6>
        </div>

        <div class='mainContainer'>
            <div class='fileListContainer'>
                <ul class="list-group">                
                    <?php
                        $files = scandir('./');
                        sort($files); // this does the sorting
                        foreach($files as $file){
                            $absolute_path = realpath($file);                
                            print '<a href="/analyzer/web/page.php?filePath='.$absolute_path.'"'.
                            'class="list-group-item">'.$file.
                            '</a>';
                        }            
                    ?>  
                </ul>
            </div>       

            <div id="piechart"></div>       
        </div>        
        
        <script type="text/javascript">
            // Load google charts
            var JSONdata;
            $.getJSON('result.json', function(obj) {
                JSONdata = obj;
            });
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);        

            // Draw the chart and set the chart values
            function drawChart() {                
                console.log(JSONdata);

                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Issue');
                data.addColumn('number', 'Count');
                
                $.each(JSONdata, function(k, v) {
                    if (k != "WARN") {                                            
                        data.addRow([k,v]);
                    }                    
                });
                // Optional; add a title and set the width and height of the chart
                var options = {'title':'Checkstyle Report', 'is3D':true, 'width':700, 'height':400};
            
                // Display the chart inside the <div> element with id="piechart"
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }
        </script> 
    </body>
</html>
