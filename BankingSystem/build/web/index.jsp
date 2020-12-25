<%-- 
    Document   : customerManagement
    Created on : Dec 24, 2020, 8:55:16 PM
    Author     : jieme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".login-form").hide();

                $(".login").click(function () {
                    $(".signup-form").hide();
                    $(".login-form").show();

                });

                $(".signup").click(function () {
                    $(".signup-form").show();
                    $(".login-form").hide();

                });
            });
        </script>
    </head>
    <body>
        <div class="home">
            <div class="right">
                <div class="centered">
                    <form action="bankingsystem.jsp" method="post">
                        <div class="title">
                            <h2>Self Services Banking System</h2>
                        </div>
                        <div class="signup">Create Account</div>
                        <div class="login">Login</div>

                        <div class="signup-form">
                            <input type="text" name="name" placeholder="Name" autocomplete="off" class="input"><br>
                            <input type="text" name="gender" placeholder="Gender" autocomplete="off" class="input"><br>
                            <input type="text" name="age" placeholder="Age" autocomplete="off" class="input"><br>
                            <input type="password" name="pin" placeholder="Enter Pin" autocomplete="off" class="input"><br>
                            <br>
                            <input type="submit" name="create" value="Create" class="btn">
                        </div>
                    </form>

                    <form action="bankingsystem.jsp" method="post">
                        <div class="login-form">

                            <input type="text" name="id" placeholder="ID" autocomplete="off" class="input"><br>
                            <input type="password" name="pin2" placeholder="Enter Pin" autocomplete="off" class="input"><br>
                            <br>
                            <input type="submit" name="login" value="Login" class="btn">
                            <span><a href="#">I Forgot my ID or Pin</a></span>
                        </div>
                    </form>
                </div>
            </div>

            <div class="left">
                <img src="https://images.pexels.com/photos/1103970/pexels-photo-1103970.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940">
            </div>
        </div>
</html>