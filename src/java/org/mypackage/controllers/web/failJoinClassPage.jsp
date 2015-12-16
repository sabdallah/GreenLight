<!DOCTYPE html>
<!--
10.3.11.101
-->
<html>  
    <meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">



    <head>  

        <!-- Loading Bootstrap -->
        <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Loading Flat UI -->
        <link href="dist/css/flat-ui.css" rel="stylesheet">
        <link href="docs/assets/css/demo.css" rel="stylesheet">

        <link rel="shortcut icon" href="img/favicon.ico">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
        <!--[if lt IE 9]>
          <script src="dist/js/vendor/html5shiv.js"></script>
          <script src="dist/js/vendor/respond.min.js"></script>
        <![endif]-->

        <style>
            html {
                position: relative;
                min-height: 100%;
            }
            body {
                /* Margin bottom by footer height */
                margin-bottom: 50px;
            }
            .footer {
                position: absolute;
                bottom: 0;
                width: 100%;
                /* Set the fixed height of the footer here */
                height: 50px;
                background-color: #2C3E50;
            }
            .animate {
                position: relative; 
                top: -30px;
                width: 100%;
                margin-top: 0px;
                border-top: 0px;
                padding-top: 0px;
                height: 300px;
                -webkit-animation: animate_bg 20s;
                animation: animate_bg 20s;
                -webkit-animation-iteration-count: infinite;
                animation-iteration-count: infinite;
                text-align: center;
            }

            @keyframes animate_bg {
                0%   {background:red;}
                25%  {background:yellow;}
                50% {background:green;}
                75%  {background:yellow;}
                100%   {background:red;}
            }

            @-webkit-keyframes animate_bg {
                0%   {background:red;}
                25%  {background:yellow;}
                50% {background:green;}
                75%  {background:yellow;}
                100%   {background:red;}
            }
        </style>

        <title>Join Class | Green Light | Join Class</title>  
        <meta charset="UTF-8">  
        <meta name="viewport" content="width=device-width">  
    </head>  
    <body>   
        <div  class="animate">
            <br>
            <br>
            <center><h1 class="demo-heading-note">Green Light</h1></center>
            <center><p class="lead">The Classroom of the Future</p></center>
        </div>
        <br>
        <div class="container">
            <div class="col-md-12">
                <form name="JoinClass" action="JoinClassServlet">  

                    <div class="form-group">
                        <label for="ClassID">Class Name</label>
                        <input type="text" name="classID" class="form-control" id="ClassID" placeholder="Enter class ID">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                     <p>ERROR: <jsp:getProperty name="error" property="string" /></p>
                    <br>

                    <input type="submit" class="btn btn-block btn-lg btn-primary" value="Join Class" name="Join Button"/>  
                </form>
            </div>
        </div>

        <footer class="footer">
            <div class="col-lg-12" style="background-color: #2C3E50;" >
                <div class="row">
                    <span  style="color: white;"><p><small><i>Created by Sam Abdallah and Lucas Sacherer</i></small></p></span> 
                </div>
            </div>
        </footer>


    </body>  

</html>  