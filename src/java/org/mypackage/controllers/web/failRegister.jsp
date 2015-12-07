<%-- 
    Document   : failRegister
    Created on : Oct 8, 2015, 7:25:34 PM
    Author     : sam
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

        <title>Register | ConfusOMeter | Register</title>  
        <meta charset="UTF-8">  
        <meta name="viewport" content="width=device-width">  
    </head>  
    <body class="animate">   
    <center><h1>Register</h1></center>
    <br>

        <form name="Register" action="RegisterServlet">  

            <b>Email:
            <input type="text" name="user" value="" /> 
            <br>
            Password: <input type="password" name="password" value=""/>
            <br>
            Confirm Password: <input type="password" name="password2" value=""/>
            </b>
            
            
            <input type="submit" value="Register" name="Register Button"/>  
        </form>
    ERROR: <jsp:getProperty name="error" property="string" />


</body>  
</html>  
