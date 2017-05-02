/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexercise;

import config.DBConnector;
import config.IDBConnector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author nikolai
 */
public class DbExercise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IDBConnector dbCon = new DBConnector();
        Connection con = dbCon.getConnection();
        DbExercise dbExercise = new DbExercise();
        
        try {
            ResultSet rsEx1 = dbExercise.ex1(con);
            while (rsEx1.next()) {
                System.out.println("Name: " + rsEx1.getString(1) + "\t"
                        + " Building: " + rsEx1.getString(2) + "\t"
                        + "Budget: " + rsEx1.getDouble(3));
            }
            dbCon.closeConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbExercise.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public ResultSet ex1(Connection con) throws SQLException {
        String SQLString = "SELECT * FROM DEPARTMENT";

        PreparedStatement statement = null;
        ResultSet rs  = null;
        
        try {
            statement = con.prepareStatement(SQLString);
             rs = statement.executeQuery();
            /*while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + "\t"
                        + " Building: " + rs.getString(2) + "\t"
                        + "Budget: " + rs.getDouble(3));
            }*/
        } catch (Exception e) {
            System.out.println("Fail");
            e.printStackTrace();
        } finally{
             //con.close();
        }
        return rs;
    }
    
    private static void ex2(Connection con, String deptName) throws SQLException{
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE DEPARTMENT SET BUDGET = ? WHERE DEPT_NAME = ?");
            statement.setDouble(1, 11.11);
            statement.setString(2, deptName);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DbExercise.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             con.close();
        }
    }
    
    private static void ex5(Connection con) throws SQLException{
        //https://www.mkyong.com/jdbc/jdbc-callablestatement-stored-procedure-out-parameter-example/
        ResultSet rs = null;
        CallableStatement callSt = null;
       

        try {
         
            // prepare
            String sqlStr = "{call getInstructorInfo(?,?,?,?,?)}";
            callSt = con.prepareCall(sqlStr);
            
            callSt.setString(1,"10101");
            callSt.registerOutParameter(2,java.sql.Types.VARCHAR);
            callSt.registerOutParameter(3,java.sql.Types.VARCHAR);
            callSt.registerOutParameter(4,java.sql.Types.VARCHAR);
            callSt.registerOutParameter(5,java.sql.Types.NUMERIC);
//            callSt.registerOutParameter(2, OracleTypes.CURSOR);
            
//            rs = callSt.executeQuery();
           callSt.executeUpdate();
            
           System.out.println("id: " + callSt.getString(2));
           System.out.println("name: " + callSt.getString(3));
           System.out.println("dept name: " + callSt.getString(4));
           System.out.println("salary: " + callSt.getString(5));
          

        } catch (Exception ee) {
            System.out.println("fail");
            System.err.println(ee);
        } finally {
            callSt.close();
            con.close();
        }
    }

}
