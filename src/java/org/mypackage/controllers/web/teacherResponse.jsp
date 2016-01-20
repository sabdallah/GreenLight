<%-- 
    Document   : teacherjsp
    Created on : Aug 28, 2015, 11:24:30 AM
    Author     : 1670676
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="teacherBean" scope="request" class="org.mypackage.models.Room" />

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
    <a href="index.html"></a>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function refresh() {
            $.get("DataServlet", function (responseText) {
                document.body.style.background = responseText;

            });
            $.get("PercentServlet", function (responseText) {
                document.getElementById("percent").innerHTML = responseText + "% Understanding";
            });
            $.get("QuestionServlet", function (responseText) {
                document.getElementById("question").innerHTML = responseText;
            });
        }
        setInterval(refresh, 1000);

        function deleteQuestion(index) {
            $.get("deleteQuestion?index=" + index);
            refresh();
        }
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Room <jsp:getProperty name="teacherBean" property="roomNum" /> Teacher</title>
</head>
<body>
    <div  class="animate">
        <br>
        <br>
        <center><h3>Green Light</h3></center>
    </div>
    <div>
        <h1 align="center">Welcome to Room  <jsp:getProperty name="teacherBean" property="roomNum" /></h1>
        <h1 align="center"><br><div id="percent"></div></h1>
    </div>
    <div class="col-lg-12" style="background-color: white; padding-top: 20px; padding-bottom: 20px;">
        <h6 align="center"><br><div id="question"></div></h6>
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
