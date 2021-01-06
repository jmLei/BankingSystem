<%-- 
    Document   : summary
    Created on : Jan 5, 2021, 4:56:15 PM
    Author     : jieme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "database.*"%>
<%@ page import ="java.util.*" %>
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
                <div class="page head">Account Summary</div>
                <p id="demo"></p>
                <div class="page body">
                    <table id="summary">
                        <tr>
                            <th></th>
                            <th>Account Number</th>
                            <th>Balance</th>
                        </tr>
                        <tr>
                            <td>today</td>
                            <td>1000</td>
                            <td>$100</td>
                        </tr>
                        <tr>
                            <td>today</td>
                            <td>1001</td>
                            <td>$100</td>
                        </tr>
                    </table>
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
        String id = (String)session.getAttribute("ID");
        List<List<String>>  tableData = new ArrayList<List<String>>();
        BankingDatabase bdb = new BankingDatabase();
        tableData = bdb.accountSummary(id);
        out.print(tableData);
    %>

    <script type="text/javascript">
        var tableData = "<%=tableData%>";  
        //var table = document.getElementById("summary");
        document.getElementById("demo").innerHTML = tableData[0][3];
        /*
        if (tableData.size() > 1) {
            document.getElementById("demo").innerHTML = tableData;
            for (int i = 0; i < tableData.size() - 1; i++) {
                var row = table.insertRow();
                var cell1 = row.insertCell(1);
                var cell2 = row.insertCell(2);
                cell1.innerHTML = tableData[i][0];
                cell2.innerHTML = tableData[i][1];
            }
        }
        var row = table.insertRow(0);
        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);
        var cell2 = row.insertCell(2);
        cell1.innerHTML = "Total";
        cell1.innerHTML = tableData[tableData.size() - 1][0];
        cell2.innerHTML = tableData[tableData.size() - 1][1];
         */
    </script>
</body>
</html>
