<%-- 
    Document   : customerManagement
    Created on : Dec 24, 2020, 8:55:16 PM
    Author     : jieme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "database.*"%>
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
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
    </head>
    <body>
        <div class="home">
            <div class="right">
                <div class="centered">
                    <form action="index.jsp" method="post">
                        <div class="title"
                             <h1>Self Services Banking System</h1>
                        </div>
                        <div class="signup">Create Account</div>
                        <div class="login">Login</div>

                        <div class="signup-form">
                            <input type="text" name="name" placeholder="Name" id="name" autocomplete="off" class="input"><br>
                            <select name="gender" placeholder="Gender" id="gender" autocomplete="off" class="input">
                                <option value="" disabled selected>Gender</option>
                                <option value="F">Female</option>
                                <option value="M">Male</option>   
                            </select><br>
                            <input type="text" name="age" placeholder="Age" id="age" autocomplete="off" class="input"><br>
                            <input type="password" name="pin" placeholder="Enter Pin" id="pin" autocomplete="off" class="input"><br>
                            <br>
                            <div class ="create">
                                <input type="submit" name="create" value="Create" class="btn">
                            </div>
                        </div>
                    </form>

                    <form action="" method="post">
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

        <jsp:useBean id="customer" class="tables.Customer" />
        <jsp:setProperty property="*" name="customer" />

        <%
            String x = request.getParameter("create");
            String y = request.getParameter("login");
            String msg = "";
            boolean flag = false;
            BankingDatabase bdb = new BankingDatabase();
            if(x!=null && x.equals("Create")){
                msg = bdb.newCustomer(customer);
                String name = request.getParameter("name");
                session.setAttribute("name",name);
            }
            else if(y!=null && y.equals("Login")){
                String id = request.getParameter("id");
                String pin = request.getParameter("pin2");
                session.setAttribute("ID",id);
                flag = bdb.login(id,pin);
                msg = ":: LOGIN - ERROR - INVALID ID OR PIN";
                if (flag == true){
                    msg = ":: LOGIN ACCOUNT - SUCCESS";
                }
            }
        %>
        <script type="text/javascript">
            var msg = "<%=msg%>";
            var flag = "<%=flag%>";
            if (msg.localeCompare("") !== 0) {
                alert(msg);
            }
            if (flag.localeCompare("true") === 0) {
                window.location.href = "bankingsystem.jsp";
            }

        </script>

    </body>
</html>
