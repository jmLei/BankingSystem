/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jieme
 */
public class BankingDatabase {

    // Connection properties
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/bankingsystem";
    private static final String username = "root";
    private static final String password = "Jiemei1052";

    // JDBC Objects
    private static Connection con;

    /**
     * Test database connection.
     */
    public String testConnection() {
        String msg = "";
        msg = ":: TEST - CONNECTING TO DATABASE";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            con.close();
            msg = ":: TEST - SUCCESSFULLY CONNECTED TO DATABASE";
        } catch (Exception e) {
            msg = ":: TEST - FAILED CONNECTED TO DATABASE";
            e.printStackTrace();
        }
        return msg;
    }

    public String test() {
        return "Test";
    }

    /**
     * Create a new customer.
     *
     * @param customer Customer class
     */
    public String newCustomer(tables.Customer customer) throws ClassNotFoundException {
        String msg = "";
        int id;
        String name = customer.getName();
        String gender = customer.getGender();
        String age = customer.getAge();
        String pin = customer.getPin();
        //Check empty input
        if (name.equals("") || gender.equals("") || age.equals("") || pin.equals("")) {
            msg = "Please Enter All Information";
        } //Check valid input
        else if (!isNumeric(age) || Integer.parseInt(age) <= 0) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID AGE";
        } else if (!isNumeric(pin) || Integer.parseInt(pin) < 0) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID PIN";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                con = DriverManager.getConnection(url, username, password); //Create the connection
                String query = "INSERT INTO customer (name, gender, age, pin) VALUES (?,?,?,?)";     //The query to run
                PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); //Create a prepared statement
                pstmt.setString(1, name);
                pstmt.setString(2, gender);
                pstmt.setString(3, age);
                pstmt.setString(4, pin);
                msg = msg + "1";
                int updated = pstmt.executeUpdate();
                if (updated == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
                        msg = ":: CREATE NEW CUSTOMER - SUCCESS! THIS IS YOUR CUSTOMER ID: " + id;
                    }
                    rs.close();  //Close the result set
                }
                pstmt.close();			                                                                //Close the statement after we are done with the statement
                con.close();            //Close the connection after we are done with everything
            } catch (Exception e) {
                msg = ":: CREATE NEW CUSTOMER - FAILED TO CONNECT TO DATABASE";
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * Login as exist customer.
     *
     * @param id customer id
     * @param pin customer pin
     */
    public static boolean login(String id, String pin) {
        boolean flag = false;
        try {
            Class.forName(driver);                                                                  //load the driver
            Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
            String query1 = "SELECT * FROM customer WHERE id = ? AND pin = ?";     //The query to run
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, id);
            pstmt1.setString(2, pin);
            ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
            if (results1.next()) {  //Check if the id exists
                System.out.println(":: LOGIN ACCOUNT - SUCCESS");
                flag = true;
            } else {

                System.out.println(":: LOGIN - ERROR - INVALID ID OR PIN");
            }
            results1.close();
            pstmt1.close(); //Close the statement after we are done with the statement
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Open a new account.
     *
     * @param id customer id
     * @param type type of account
     * @param amount initial deposit amount
     */
    public String openAccount(String id, String type, String amount) {
        System.out.println("Open account");
        String msg = "";
        int number;
        //Check empty input
        if (amount.equals("")) {
            System.out.println("Check");
            msg = "Please Enter All Information";
        } //Check valid input
        else if (!isNumeric(amount) || Float.valueOf(amount) < 0) {
            msg = ":: OPEN ACCOUNT - ERROR - INVALID AMOUNT";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query1 = "INSERT INTO account (id, balance, type, status) VALUES (?,?,?,?)";           //The query to run
                PreparedStatement pstmt1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS); //Create a prepared statement
                pstmt1.setString(1, id);
                pstmt1.setString(2, amount);
                pstmt1.setString(3, type);
                pstmt1.setString(4, "A");
                int updated = pstmt1.executeUpdate();
                if (updated == 1) {
                    ResultSet rs = pstmt1.getGeneratedKeys();
                    if (rs.next()) {
                        number = rs.getInt(1);
                        msg = ":: OPEN ACCOUNT - SUCCESS! THIS IS YOUR ACCOUNT NUMBER: " + number;
                    }
                    rs.close();
                }
                pstmt1.close();	   //Close the connection after we are done with everything		                                                                //Close the statement after we are done with the statement
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * Close an account.
     *
     * @param accNum account number
     * @param cusID customer ID
     */
    public String closeAccount(String accNum, String cusID) {
        String msg = "";
        System.out.println(":: CLOSE ACCOUNT - RUNNING");
        //Check empty input
        if (accNum.equals("")) {
            msg = "Please Enter All Information";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query1 = "SELECT * FROM account, customer WHERE account.id = customer.id AND account.number = ? AND account.status = ? AND customer.id = ?";     //The query to run
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                pstmt1.setString(1, accNum);
                pstmt1.setString(2, "A");
                pstmt1.setString(3, cusID);
                ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
                if (results1.next()) {  //Check if the id exists
                    String query2 = "UPDATE account SET balance = ?, status = ? WHERE number = ?";           //The query to run
                    PreparedStatement pstmt2 = con.prepareStatement(query2); //Create a prepared statement
                    pstmt2.setString(1, "0");
                    pstmt2.setString(2, "I");
                    pstmt2.setString(3, accNum);
                    pstmt2.executeUpdate();
                    msg = ":: CLOSE ACCOUNT - SUCCESS";
                    pstmt2.close();
                } else {
                    msg = ":: CLOSE ACCOUNT - ERROR - INVALID ACCOUNT NUMBER";
                }
                results1.close();
                pstmt1.close(); //Close the statement after we are done with the statement
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return msg;
    }

    /**
     * Deposit into an account.
     *
     * @param accNum account number
     * @param amount deposit amount
     */
    public String deposit(String accNum, String amount) {
        String msg = "";
        System.out.println(":: DEPOSIT - RUNNING");
        //Check empty input
        if (accNum.equals("") || amount.equals("")) {
            msg = "Please Enter All Information";
        } else if (!isNumeric(amount) || Float.valueOf(amount) < 0) {
            msg = ":: DEPOSIT - ERROR - INVALID AMOUNT";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query1 = "SELECT * FROM account WHERE number = ? AND status = ?";     //The query to run
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                pstmt1.setString(1, accNum);
                pstmt1.setString(2, "A");
                ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
                if (results1.next()) {  //Check if the id exists
                    float balance = 0;
                    balance = results1.getFloat("balance");
                    balance += Float.valueOf(amount);
                    String query2 = "UPDATE account SET balance = ? WHERE number = ?";           //The query to run
                    PreparedStatement pstmt2 = con.prepareStatement(query2); //Create a prepared statement
                    pstmt2.setFloat(1, balance);
                    pstmt2.setString(2, accNum);
                    pstmt2.executeUpdate();
                    msg = ":: DEPOSIT - SUCCESS";
                    pstmt2.close();
                } else {
                    msg = ":: DEPOSIT - ERROR - INVALID ACCOUNT NUMBER";
                }
                results1.close();
                pstmt1.close();			                                                                //Close the statement after we are done with the statement
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * Withdraw from an account.
     *
     * @param accNum account number
     * @param amount withdraw amount
     * @param cusID customer ID
     */
    public String withdraw(String accNum, String amount, String cusID) {
        System.out.println(":: WITHDRAW - RUNNING");
        String msg = "";
        //Check empty input
        if (accNum.equals("") || amount.equals("")) {
            msg = "Please Enter All Information";
        } else if (!isNumeric(amount) || Float.valueOf(amount) < 0) {
            msg = ":: WITHDRAW - ERROR - INVALID AMOUNT";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query1 = "SELECT * FROM account, customer WHERE account.id = customer.id AND account.number = ? AND account.status = ? AND customer.id = ?";     //The query to run
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                pstmt1.setString(1, accNum);
                pstmt1.setString(2, "A");
                pstmt1.setString(3, cusID);
                ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
                if (results1.next()) {  //Check if the id exists
                    float balance = 0;
                    balance = results1.getFloat("balance");
                    balance -= Float.valueOf(amount);
                    if (balance >= 0) {
                        String query2 = "UPDATE account SET balance = ? WHERE number = ?";           //The query to run
                        PreparedStatement pstmt2 = con.prepareStatement(query2); //Create a prepared statement
                        pstmt2.setFloat(1, balance);
                        pstmt2.setString(2, accNum);
                        pstmt2.executeUpdate();
                        msg = ":: WITHDRAW - SUCCESS";
                        pstmt2.close();
                    } else if (balance < 0) {
                        msg = ":: WITHDRAW - ERROR - NOT ENOUGH FUNDS";
                    }
                } else {
                    msg = ":: WITHDRAW - ERROR - INVALID ACCOUNT NUMBER";
                }
                results1.close();
                pstmt1.close();			                                                                //Close the statement after we are done with the statement
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * Transfer amount from source account to destination account.
     *
     * @param srcAccNum source account number
     * @param destAccNum destination account number
     * @param amount transfer amount
     * @param cusID source customer ID
     */
    public String transfer(String srcAccNum, String destAccNum, String amount, String cusID) {
        System.out.println(":: TRANSFER - RUNNING");
        String msg = "";
        //Check empty input
        if (srcAccNum.equals("") || destAccNum.equals("") || amount.equals("")) {
            msg = "Please Enter All Information";
        } else if (!isNumeric(amount) || Float.valueOf(amount) < 0) {
            msg = ":: TRANSFER - ERROR - INVALID AMOUNT";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query1 = "SELECT * FROM account, customer WHERE account.id = customer.id AND  account.number = ? AND account.status = ? AND customer.id = ?";     //The query to run
                String query2 = "SELECT * FROM account WHERE number = ? AND status = ?";
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                PreparedStatement pstmt2 = con.prepareStatement(query2);
                pstmt1.setString(1, srcAccNum);
                pstmt1.setString(2, "A");
                pstmt1.setString(3, cusID);
                pstmt2.setString(1, destAccNum);
                pstmt2.setString(2, "A");
                ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
                ResultSet results2 = pstmt2.executeQuery();
                if (results1.next() && results2.next()) {  //Check if the id exists
                    float srcBalance = 0;
                    float destBalance = 0;
                    srcBalance = results1.getFloat("balance");
                    destBalance = results2.getFloat("balance");
                    srcBalance -= Float.valueOf(amount);
                    destBalance += Float.valueOf(amount);
                    if (srcBalance >= 0) {
                        String query3 = "UPDATE account SET balance = ? WHERE number = ?";
                        String query4 = "UPDATE account SET balance = ? WHERE number = ?";
                        PreparedStatement pstmt3 = con.prepareStatement(query3); //Create a prepared statement
                        PreparedStatement pstmt4 = con.prepareStatement(query4);
                        pstmt3.setFloat(1, srcBalance);
                        pstmt3.setString(2, srcAccNum);
                        pstmt4.setFloat(1, destBalance);
                        pstmt4.setString(2, destAccNum);
                        pstmt3.executeUpdate();
                        pstmt4.executeUpdate();
                        msg = ":: TRANSFER - SUCCESS";
                        pstmt3.close();
                        pstmt4.close();
                    } else if (srcBalance < 0) {
                        msg = ":: TRANSFER - ERROR - NOT ENOUGH FUNDS";
                    }
                } else {
                    msg = ":: TRANSFER - ERROR - INVALID ACCOUNT NUMBER";
                }
                results1.close();
                results2.close();
                pstmt1.close();
                pstmt2.close();//Close the statement after we are done with the statement
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * Display account summary.
     *
     * @param cusID customer ID
     */
    public List<List<String>> accountSummary(String cusID) {
        List<List<String>> array = new ArrayList<List<String>>();
        System.out.println(":: ACCOUNT SUMMARY - RUNNING");
        System.out.println("NUMBER      BALANCE");
        System.out.println("----------- -----------");
        try {
            Class.forName(driver);                                                                  //load the driver
            Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
            String query1 = "SELECT * FROM account WHERE id = ?  AND status = ?";     //The query to run
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, cusID);
            pstmt1.setString(2, "A");
            ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
            String number = "";
            float balance = 0;
            float total = 0;
            if (results1.next()) {
                do {
                    number = results1.getString("number");
                    balance = results1.getFloat("balance");
                    total += balance;
                    array.add(new ArrayList<String>());
                    array.get(array.size()-1).add(number);
                    array.get(array.size()-1).add(String.valueOf( balance ));
                    System.out.printf("%11s ", number);
                    System.out.printf("%11s\n", balance);
                } while (results1.next());
                array.add(new ArrayList<String>());
                array.get(array.size()-1).add("");
                array.get(array.size()-1).add(String.valueOf( total ));
                System.out.println("-----------------------");
                System.out.print("TOTAL");
                System.out.printf("%18d\n", total);
                System.out.println(":: ACCOUNT SUMMARY - SUCCESS");
                System.out.print("");
                System.out.print("array");
            } else {
                System.out.println(":: ACCOUNT SUMMARY - ERROR - INVALID CUSTOMER ID");
            }
            results1.close();
            pstmt1.close();			                                                                //Close the statement after we are done with the statement
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(array.size()==0){
            array.add(new ArrayList<String>());
            array.get(0).add("");
            array.get(0).add("0");
        }
        return array;
    }

    /**
     * Display account summary.
     *
     * @param cusID customer ID
     */
    public String getName(String cusID) {
        String name = "";
        try {
            Class.forName(driver);                                                                  //load the driver
            Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
            String query1 = "SELECT name FROM customer WHERE id = ?";     //The query to run
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, cusID);
            ResultSet results1 = pstmt1.executeQuery();  //Get the result that has the id record
            if (results1.next()) {
                name = results1.getString("name");
            } 
            results1.close();
            pstmt1.close();			                                                                //Close the statement after we are done with the statement
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    /**
     * Check if a string is a number
     *
     * @param str a string to be checked
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
}
