/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexercise;

import config.IDBConnector;
import config.MockDBConnector;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DbExerciseTest {

    IDBConnector con;
    
    @Mock 
    DbExercise dbExercise;
    
    DbExercise newDbExercise;
    
    
    ResultSet resultSetMock = Mockito.mock(ResultSet.class);

    @Before
    public void setUp() throws FileNotFoundException, IOException, SQLException {
        newDbExercise = new DbExercise(new MockDBConnector());
        
        con = new MockDBConnector();

        ScriptRunner runner = new ScriptRunner(con.getConnection(), false, false);
        String file = "C:\\Users\\nikolai\\Documents\\NetBeansProjects\\Testing_Db_mock\\src\\test\\java\\dbexercise\\createDrop.sql";
        runner.runScript(new BufferedReader(new FileReader(file)));

        String insertFile = "C:\\Users\\nikolai\\Documents\\NetBeansProjects\\Testing_Db_mock\\src\\test\\java\\dbexercise\\insertData.sql";
        runner.runScript(new BufferedReader(new FileReader(insertFile)));
    }

    
    @After
    public void afterClass(){
        con.closeConnection();
    }
            
    
    /**
     * Test of main method, of class DbExercise.
     */
    @Test
    public void testMain() throws SQLException {
        when(dbExercise.ex1()).thenReturn(resultSetMock);
        when(resultSetMock.getString(1)).thenReturn("aa");
        
        String testDbEx1 = dbExercise.ex1().getString(1);
        assertThat(testDbEx1, is("aa"));        
    }
    
    @Test 
    public void testConnection(){
        assertThat(con.getConnection(), is(not(CoreMatchers.nullValue())) );
    }
    
    @Test
    public void testMockDataFromDb() throws SQLException{
        ResultSet resultSet = newDbExercise.ex1();
        resultSet.next();
        String str = resultSet.getString(1);
        System.out.println(str);
        assertThat(str, is("Biology"));
    }
    
    
    

}
