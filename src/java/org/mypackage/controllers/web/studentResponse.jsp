<%-- 
    Document   : teacherjsp
    Created on : Aug 28, 2015, 11:24:30 AM
    Author     : 1670676
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="studentBean" scope="request" class="org.mypackage.models.Room" />

    <head>
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
                top: -75px;
                width: 100%;
                margin-top: 0px;
                border-top: 0px;
                padding-top: 0px;
                height: 150px;
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
        <!-- Loading Bootstrap -->
        <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Loading Flat UI -->
        <link href="dist/css/flat-ui.css" rel="stylesheet">
        <link href="docs/assets/css/demo.css" rel="stylesheet">
        <style>
            .greenButton {
                background:url(https://dl.dropboxusercontent.com/u/11260538/greenbutton.png) no-repeat;
                width: 300px;
                height: 300px;
                border: none;
            }
            .redButton {
                background:url(https://dl.dropboxusercontent.com/u/11260538/redbutton.png) no-repeat;
                width: 300px;
                height: 300px;
                border: none;
            }
        </style>

        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            function refresh() {
                $.get("DataServlet", function (responseText) {
                    document.body.style.background = responseText;

                });
                $.get("PercentServlet", function (responseText) {
                    document.getElementById("percent").innerHTML = "<h3 style=\"font-size: 5vw;\">" + responseText + "% Understanding</h3>";

                });

            }
            setInterval(refresh, 1000);

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room <jsp:getProperty name="studentBean" property="roomNum" /> Student</title>
    </head>
    <body>
        <div  class="animate">
            <br>
            <br>
            <center><h3>Green Light</h3></center>
        </div>
        <div>
            <h3 align="center" style="font-size: 5vw;">Welcome to <jsp:getProperty name="studentBean" property="name" />!</h3>
            <center><div id="percent" ></div></center>
        </div>
        <div class="col-lg-12" style="background-color: #95a5a6; padding-top: 20px; padding-bottom: 20px;" >
            <center>
                <form name="Not Confused" action="StudentServlet" style="display:inline;" align="center">
                    <input type="hidden"   
                           name="id"   
                           value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
                    <input type='hidden' value="understand" name="formVal">
                    <input type="submit" value="Not Confused" name="Understand"class="btn btn-lg btn-success" style="width: 49%; height: 250px; "/>           
                </form>
                <form name="Confused" action="StudentServlet" style="display:inline;" align="center">
                    <input type="hidden"   
                           name="id"   
                           value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
                    <input type='hidden' value="confused" name="formVal">
                    <input type="submit" value="Confused" name="Confused" class="btn btn-lg btn-danger" style="width: 49%; height: 250px; "/>
                </form>
                <br>
                <form name="Question" action="StudentServlet" style="display:inline;" align="center">
                    <input type="hidden"   
                           name="id"   
                           value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
                    <label for="question">Question</label>
                    <input type="text" name="question" class="form-control" id="question" placeholder=<jsp:getProperty name="qHolder" property="string" />>
                    <input type="submit" value="Submit" name="qsubmit" class="btn btn-lg btn-primary">
                </form>
                <form name="LogOut" action="LogoutServlet" align="center">
                    <input type="submit" value="Log out" name="logout" class="btn btn-block btn-lg btn-danger"/>  
                </form>
                    <br>
                    <br>
            </center>
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
