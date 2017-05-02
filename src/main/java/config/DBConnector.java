package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnector implements IDBConnector{

    private static String driver = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String id = "newUser";	//Insert Your ORACLE id and password
    private static String pw = "1234";
    
    private Connection con;

    private DBConnector instance;
    public DBConnector()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);   // The connection will be released upon program 
		  					      // termination by the garbage collector	
        } catch (Exception e)
        {
            System.out.println("\n*** Remember to insert your Oracle ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }
    }
    public DBConnector getInstance()
    {
        if (instance == null)
            instance = new DBConnector();
        return instance;
    }
    //------------------
    
    public Connection getConnection()
    {
      return con;
    }

    @Override
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
