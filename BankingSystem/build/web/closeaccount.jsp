<%-- 
    Document   : closeaccount
    Created on : Jan 4, 2021, 4:06:39 PM
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
        <div class="header">
            <h1>BANKING SYSTEM</h1>
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
                <div class="page head">Close Bank Account</div>
                <div class="page body close">
                    <h3>Enter Account Information</h3>
                    <br>
                    <form action="" method ="post">
                        <label for="number">Account Number:</label>
                        <input type="text" name="number" id="number" autocomplete="off" class="input"><br>
                        <input type="submit" name="close" value="Close Account" class="btn">
                    </form>
                </div>
            </div>

            <div class="column side">

            </div>
        </div>

        <div class="footer">
            <p>Footer</p>
        </div>
    </div>

    <jsp:useBean id="account" class="tables.Account" />
    <jsp:setProperty property="*" name="account" />

    <%
        String x = request.getParameter("close");
        String msg = "";
        BankingDatabase bdb = new BankingDatabase();
        String id = (String)session.getAttribute("ID");
        String number = request.getParameter("number");
        if(x!=null && x.equals("Close Account")){
            msg = bdb.closeAccount(number,id);
            out.println("close");
        }
        out.println("id is "+ id);
        out.println("msg is "+ msg);
    %>

    <script type="text/javascript">
        var msg = "<%=msg%>";
        if (msg.localeCompare("") !== 0) {
            alert(msg);
        }
    </script>
</body>
</html>
