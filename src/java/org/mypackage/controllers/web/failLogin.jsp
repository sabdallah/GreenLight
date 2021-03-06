<!DOCTYPE html>
<!--
10.3.11.101
-->
<html>  



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
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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

        <title>Welcome Page | Green Light | Welcome Page</title>  
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
                <form name="Login" action="LoginServlet" method="post">  
                    <div class=""form-group has-error"">
                         <label for="exampleInputEmail1">Email address</label>
                        <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
                    </div>
                    <div class=""form-group has-error"">
                         <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <p>ERROR: <jsp:getProperty name="error" property="string" /></p>
                    <br>
                    <input type="submit" class="btn btn-block btn-lg btn-primary" value="Login" name="Submit Button"/>  
                </form>

                <br>
                <a href="register.html" class="btn btn-block btn-lg btn-info">Register</a>  
                </br>
                </br>


            </div>
        </div>
        <div style="width: 100%; background-color: #34495E; height: 400px;">
            </br>
            </br>
            <div class="col-lg-12" style="background-color: #34495E;" >
                <div class="row">
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                        <div class="tile">
                            <img src="https://dl.dropboxusercontent.com/u/11260538/l/pencils%20%281%29.svg" alt="Compas" class="tile-image big-illustration">
                            <h3 class="tile-title">Education made simple</h3>
                            <p>Filler text. Filler text. Filler text.</p>
                        </div>
                    </div>  
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                        <div class="tile">
                            <img src="https://dl.dropboxusercontent.com/u/11260538/l/clocks.svg" alt="Compas" class="tile-image big-illustration">
                            <h3 class="tile-title">Education made simple</h3>
                            <p>100% convertable to HTML/CSS layout.</p>
                        </div>
                    </div>     
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                        <div class="tile">
                            <img src="https://dl.dropboxusercontent.com/u/11260538/l/loop.svg" alt="Compas" class="tile-image big-illustration">
                            <h3 class="tile-title">Education made simple</h3>
                            <p>100% convertable to HTML/CSS layout.</p>
                        </div>
                    </div>         

                </div>
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

