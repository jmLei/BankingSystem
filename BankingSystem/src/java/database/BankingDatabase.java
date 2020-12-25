package database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jiemei Lei
 */
public class BankingDatabase {

    // Connection properties
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/bankingsystem";
    private static final String username = "root";
    private static final String password = "Jiemei1052";

    // JDBC Objects
    private static Connection con;
    private static ResultSet rs;
 
    /**
	 * Test database connection.
	 */
	public static void testConnection() {
		System.out.println(":: TEST - CONNECTING TO DATABASE");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			con.close();
			System.out.println(":: TEST - SUCCESSFULLY CONNECTED TO DATABASE");
			} catch (Exception e) {
				System.out.println(":: TEST - FAILED CONNECTED TO DATABASE");
				e.printStackTrace();
			}
	  }
        
        public static void test(){
            System.out.println("Test");
        }

    /**
     * Create a new customer.
     *
     * @param customer Customer class
     */
    public String newCustomer(tables.Customer customer)throws ClassNotFoundException  {
        String msg = "";
        int id;
        String name = customer.getName();
        String gender = customer.getGender();
        String age = customer.getAge();
        String pin = customer.getPin();
        //Check valid input
        if (!gender.equals("M") && !gender.equals("F")) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID GENDER";
        } else if (!isNumeric(age)) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID AGE";
        } else if (!isNumeric(pin)) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID PIN";
        } else if (Integer.parseInt(age) <= 0) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID AGE";
        } else if (Integer.parseInt(pin) < 0) {
            msg = ":: CREATE NEW CUSTOMER - ERROR - INVALID PIN";
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query = "INSERT INTO p1.customer (name, gender, age, pin) VALUES (?,?,?,?)";     //The query to run
                PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); //Create a prepared statement
                pstmt.setString(1, name);
                pstmt.setString(2, gender);
                pstmt.setString(3, age);
                pstmt.setString(4, pin);
                int updated = pstmt.executeUpdate();
                if (updated == 1) {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
                        msg = ":: CREATE NEW CUSTOMER - SUCCESS! THIS IS YOUR CUSTOMER ID: " + id;
                    }
                    rs.close();  //Close the result set
                }
                pstmt.close();			                                                                //Close the statement after we are done with the statement
                con.close();                                                                            //Close the connection after we are done with everything
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
        System.out.println(":: LOGIN - RUNNING");
        boolean flag = false;
        try {
            Class.forName(driver);                                                                  //load the driver
            Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
            String query1 = "SELECT * FROM p1.customer WHERE id = ? AND pin = ?";     //The query to run
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
    public static void openAccount(String id, String type, String amount) {
        System.out.println(":: OPEN ACCOUNT - RUNNING");
        //Check valid input
        if (!isNumeric(amount)) {
            System.out.println(":: OPEN ACCOUNT - ERROR - INVALID AMOUNT");
        } else if (!type.equals("S") && !type.equals("C")) {
            System.out.println(":: OPEN ACCOUNT - ERROR - INVALID TYPE");
        } else if (Integer.parseInt(amount) < 0) {
            System.out.println(":: OPEN ACCOUNT - ERROR - INVALID AMOUNT");
        } else {
            try {
                Class.forName(driver);                                                                  //load the driver
                Connection con = DriverManager.getConnection(url, username, password);                  //Create the connection
                String query1 = "SELECT * FROM p1.customer WHERE ID = ?";     //The query to run
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                pstmt1.setString(1, id);
                ResultSet results = pstmt1.executeQuery();  //Get the result that has the id record
                if (results.next()) {  //Check if the id exists
                    String query2 = "INSERT INTO p1.account (id, balance, type, status) VALUES (?,?,?,?)";           //The query to run
                    PreparedStatement pstmt2 = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS); //Create a prepared statement
                    pstmt2.setString(1, id);
                    pstmt2.setString(2, amount);
                    pstmt2.setString(3, type);
                    pstmt2.setString(4, "A");
                    int updated = pstmt2.executeUpdate();
                    if (updated == 1) {
                        ResultSet rs = pstmt2.getGeneratedKeys();
                        if (rs.next()) {
                            int number = rs.getInt(1);
                            System.out.println(":: OPEN ACCOUNT - THIS IS YOUR ACCOUNT NUMBER: " + number);
                        }
                        rs.close();
                    }
                    pstmt2.close();	   //Close the connection after we are done with everything
                    System.out.println(":: OPEN ACCOUNT - SUCCESS");
                } else {
                    System.out.println(":: OPEN ACCOUNT - ERROR - INVALID ID");
                }
                pstmt1.close();			                                                                //Close the statement after we are done with the statement
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
