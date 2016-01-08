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
                    document.getElementById("percent").innerHTML = "<h3>" + responseText + "% Understanding</h3>";

                });

            }
            setInterval(refresh, 1000);

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room <jsp:getProperty name="studentBean" property="roomNum" /> Student</title>
    </head>
    <body>
        <h1 align="center">Welcome to Room  <jsp:getProperty name="studentBean" property="name" /></h1>

    <center><br><div id="percent"></div></center>
        
        <div class="col-lg-12" style="background-color: #FFFFFF; padding-top: 20px; padding-bottom: 20px;" >
    <center>
        <form name="Not Confused" action="StudentServlet" style="display:inline;" align="center">
            <input type="hidden"   
                   name="id"   
                   value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
            <input type='hidden' value="understand" name="formVal">
            <input type="submit" value="Not Confused" name="Understand"class="btn btn-lg btn-success" style="width: 49%; height: 300px; "/>           
        </form>
        <form name="Confused" action="StudentServlet" style="display:inline;" align="center">
            <input type="hidden"   
                   name="id"   
                   value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
            <input type='hidden' value="confused" name="formVal">
            <input type="submit" value="Confused" name="Confused" class="btn btn-lg btn-danger" style="width: 49%; height: 300px; "/>  


        </form>
   
    </center>
        </div>
    <center>
        <form name="LogOut" action="LogoutServlet" align="center">
            <input type="submit" value="Log out" name="logout"/>  
        </form>
    </center>
</body>

</html>
