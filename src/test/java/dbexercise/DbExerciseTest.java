/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexercise;

import java.sql.Connection;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nikolai
 */
public class DbExerciseTest {
    
    public DbExerciseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class DbExercise.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        DbExercise.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ex1 method, of class DbExercise.
     */
    @Test
    public void testEx1() throws Exception {
        System.out.println("ex1");
        Connection con = null;
        DbExercise instance = new DbExercise();
        ResultSet expResult = null;
        ResultSet result = instance.ex1(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
