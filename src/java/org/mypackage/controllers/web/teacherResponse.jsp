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
                <!-- Loading Bootstrap -->
        <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Loading Flat UI -->
        <link href="dist/css/flat-ui.css" rel="stylesheet">
        <link href="docs/assets/css/demo.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            function refresh(){
                $.get("DataServlet", function(responseText){
                    document.body.style.background = responseText;
                    
                });
                $.get("PercentServlet", function(responseText){
                    document.getElementById("percent").innerHTML= responseText + "% Understanding";      
                });
            }
            setInterval(refresh,1000);
            
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room <jsp:getProperty name="teacherBean" property="roomNum" /> Teacher</title>
    </head>
    <body>
        <h1 align="center">Welcome to Room  <jsp:getProperty name="teacherBean" property="roomNum" /></h1>
        <br><div id="percent"></div>
    </body>
</html>
