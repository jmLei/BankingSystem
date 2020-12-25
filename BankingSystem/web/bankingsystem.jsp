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
        <title>Insert</title>
    </head>
    <body>
        <jsp:useBean id="customer" class="tables.Customer" />
        <jsp:setProperty property="*" name="customer" />

        <%
            BankingDatabase bdb = new BankingDatabase();
            String msg = bdb.newCustomer(customer);
            out.print(msg);
        %>
    </body>
</html>
