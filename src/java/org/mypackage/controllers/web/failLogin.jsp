<%-- 
    Document   : failLogin
    Created on : Oct 22, 2015, 11:05:46 AM
    Author     : 1670676
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  

    <head>  
        <jsp:useBean id="error" scope="request" class="org.mypackage.models.StringHolder" />
        <style>
            .animate {
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

        <title>Welcome Page | ConfusOMeter | Welcome Page</title>  
        <meta charset="UTF-8">  
        <meta name="viewport" content="width=device-width">  
    </head>  
    <body class="animate">   
    <center><h1>ConfusOMeter</h1></center>
    <br>

    <div align="center">
        <form name="Login" action="LoginServlet">  

            <b>Username:
                <input type="text" name="username" value="" /> 
                <br>
                Password: <input type="password" name="password" value="" />
            </b>

            <input type="submit" value="Login" name="Submit Button"/>  
        </form>
        <a href="register.html">Register</a>  
        ERROR: <jsp:getProperty name="error" property="string" />

    </div>


</body>  
</html>  

