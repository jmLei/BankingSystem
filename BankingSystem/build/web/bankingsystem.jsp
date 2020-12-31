<%-- 
    Document   : bankingsystem
    Created on : Dec 24, 2020, 10:43:51 PM
    Author     : jieme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "database.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="header">
            <h1>Header</h1>
        </div>

        <div class="topnav">
            <a href="#">Home</a>
            <a href="#">Account</a>
            <a href="#">Deposit</a>
            <a href="#">Withdraw</a>
            <a href="#">Transfer</a>
            <a href="#">Summary</a>
        </div>

        <div  class="row">
            <div class="column side">
                <h2>Left Side</h2>

            </div>
            <div class="column middle">
                <h2>Main Content</h2>
            </div>

            <div class="column side">
                <h2>Right Side</h2>

            </div>
        </div>

        <div class="footer">
            <p>Footer</p>
        </div>
    </div>

    <jsp:useBean id="customer" class="tables.Customer" />
    <jsp:setProperty property="*" name="customer" />

    <%
            
    %>

    <script type="text/javascript">

    </script>
</body>
</html>