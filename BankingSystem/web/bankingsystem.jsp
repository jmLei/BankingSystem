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

        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
    </head>
    <body>
        <jsp:useBean id="customer" class="tables.Customer" />
        <jsp:setProperty property="*" name="customer" />

        <%
            BankingDatabase bdb = new BankingDatabase();
            String id = (String)session.getAttribute("ID");
            String name = bdb.getName(id);
        %>
        <div class="header">
            <h1>BANKING SYSTEM</h1>
            <div class="cusName"> Hi, <%=name%></div>
            <form action="index.jsp" method="post">
                <button class="logout">Logout</button>
            </form>
        </div>

        <div class="topnav">
            <a href="bankingsystem.jsp">Home</a>
            <div class="dropdown">
                <button class="dropbtn">Account
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="openaccount.jsp">Open Account</a>
                    <a href="closeaccount.jsp">Close Account</a>
                </div>
            </div>
            <a href="deposit.jsp">Deposit</a>
            <a href="withdraw.jsp">Withdraw</a>
            <a href="transfer.jsp">Transfer</a>
            <a href="summary.jsp">Summary</a>
        </div>

        <div  class="row">
            <div class="column side">
            </div>
            <div class="column middle">
                <h1>Welcome to the Self Services Banking System! </h1>
            </div>

            <div class="column side">

            </div>
        </div>

        <div class="footer">
           
        </div>
    </div>



    <script type="text/javascript">

    </script>
</body>
</html>