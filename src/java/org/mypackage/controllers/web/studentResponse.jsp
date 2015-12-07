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
                    document.getElementById("percent").innerHTML = responseText + "% Understanding";

                });

            }
            setInterval(refresh, 1000);

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room <jsp:getProperty name="studentBean" property="roomNum" /> Student</title>
    </head>
    <body>
        <h1 align="center">Welcome to Room  <jsp:getProperty name="studentBean" property="roomNum" /></h1>

        <br><div id="percent">0</div>
    <center>
        <form name="Not Confused" action="StudentServlet" style="display:inline;" align="center">
            <input type="hidden"   
                   name="id"   
                   value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
            <input type='hidden' value="understand" name="formVal">
            <input type="submit" value="Not Confused" name="Understand"class="greenButton"/>  
        </form>
        <form name="Confused" action="StudentServlet" style="display:inline;" align="center">
            <input type="hidden"   
                   name="id"   
                   value=<jsp:getProperty name="studentBean" property="roomNum" /> /> 
            <input type='hidden' value="confused" name="formVal">
            <input type="submit" value="Confused" name="Confused" class="redButton"/>  
        </form>
    </center>
    <center>
        <form name="LogOut" action="LogoutServlet" align="center">
            <input type="submit" value="Log out" name="logout"/>  
        </form>
    </center>
</body>

</html>