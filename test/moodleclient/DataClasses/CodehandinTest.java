/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import moodleclient.ReplyClasses;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SuperNova
 */
public class CodehandinTest {
    
    public CodehandinTest() {
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
     * Test of getAssignname method, of class Codehandin.
     */
    @Test
    public void testGetAssignname() {
        System.out.println("getAssignname");
        Codehandin instance = new Codehandin();
        String expResult = "";
        String result = instance.getAssignname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAssignname method, of class Codehandin.
     */
    @Test
    public void testSetAssignname() {
        System.out.println("setAssignname");
        String assignname = "";
        Codehandin instance = new Codehandin();
        instance.setAssignname(assignname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setup method, of class Codehandin.
     */
    @Test
    public void testSetup() {
        System.out.println("setup");
        String baseFolder = "";
        Codehandin instance = new Codehandin();
        instance.setup(baseFolder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanAssignment method, of class Codehandin.
     */
    @Test
    public void testCleanAssignment() {
        System.out.println("cleanAssignment");
        Codehandin instance = new Codehandin();
        instance.cleanAssignment();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Codehandin.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Codehandin instance = new Codehandin();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContextid method, of class Codehandin.
     */
    @Test
    public void testGetContextid() {
        System.out.println("getContextid");
        Codehandin instance = new Codehandin();
        int expResult = 0;
        int result = instance.getContextid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheckpoints method, of class Codehandin.
     */
    @Test
    public void testGetCheckpoints() {
        System.out.println("getCheckpoints");
        Codehandin instance = new Codehandin();
        HashMap<Integer, Checkpoint> expResult = null;
        HashMap<Integer, Checkpoint> result = instance.getCheckpoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntro method, of class Codehandin.
     */
    @Test
    public void testGetIntro() {
        System.out.println("getIntro");
        Codehandin instance = new Codehandin();
        String expResult = "";
        String result = instance.getIntro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIntro method, of class Codehandin.
     */
    @Test
    public void testSetIntro() {
        System.out.println("setIntro");
        String intro = "";
        Codehandin instance = new Codehandin();
        instance.setIntro(intro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDuedate method, of class Codehandin.
     */
    @Test
    public void testGetDuedate() {
        System.out.println("getDuedate");
        Codehandin instance = new Codehandin();
        int expResult = 0;
        long result = instance.getDuedate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDuedate method, of class Codehandin.
     */
    @Test
    public void testSetDuedate() {
        System.out.println("setDuedate");
        int duedate = 0;
        Codehandin instance = new Codehandin();
        instance.setDuedate(duedate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFuncpercent method, of class Codehandin.
     */
    @Test
    public void testGetFuncpercent() {
        System.out.println("getFuncpercent");
        Codehandin instance = new Codehandin();
        int expResult = 0;
        int result = instance.getFuncpercent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFuncpercent method, of class Codehandin.
     */
    @Test
    public void testSetFuncpercent() {
        System.out.println("setFuncpercent");
        int funcpercent = 0;
        Codehandin instance = new Codehandin();
        instance.setFuncpercent(funcpercent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSpectestonly method, of class Codehandin.
     */
    @Test
    public void testIsSpectestonly() {
        System.out.println("isSpectestonly");
        Codehandin instance = new Codehandin();
        boolean expResult = false;
        boolean result = instance.isSpectestonly();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpectestonly method, of class Codehandin.
     */
    @Test
    public void testSetSpectestonly() {
        System.out.println("setSpectestonly");
        boolean spectestonly = false;
        Codehandin instance = new Codehandin();
        instance.setSpectestonly(spectestonly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMustattemptcompile method, of class Codehandin.
     */
    @Test
    public void testIsMustattemptcompile() {
        System.out.println("isMustattemptcompile");
        Codehandin instance = new Codehandin();
        boolean expResult = false;
        boolean result = instance.isMustattemptcompile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMustattemptcompile method, of class Codehandin.
     */
    @Test
    public void testSetMustattemptcompile() {
        System.out.println("setMustattemptcompile");
        boolean mustattemptcompile = false;
        Codehandin instance = new Codehandin();
        instance.setMustattemptcompile(mustattemptcompile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProglangid method, of class Codehandin.
     */
    @Test
    public void testGetProglangid() {
        System.out.println("getProglangid");
        Codehandin instance = new Codehandin();
        int expResult = 0;
        int result = instance.getProglangid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProglangid method, of class Codehandin.
     */
    @Test
    public void testSetProglangid() {
        System.out.println("setProglangid");
        int proglangid = 0;
        Codehandin instance = new Codehandin();
        instance.setProglangid(proglangid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProglang method, of class Codehandin.
     */
    @Test
    public void testGetProglang() {
        System.out.println("getProglang");
        Codehandin instance = new Codehandin();
        String expResult = "";
        String result = instance.getProglang();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProglang method, of class Codehandin.
     */
    @Test
    public void testSetProglang() {
        System.out.println("setProglang");
        String proglang = "";
        Codehandin instance = new Codehandin();
        instance.setProglang(proglang);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheckpoint method, of class Codehandin.
     */
    @Test
    public void testGetCheckpoint() {
        System.out.println("getCheckpoint");
        int ordering = 0;
        Codehandin instance = new Codehandin();
        Checkpoint expResult = null;
        Checkpoint result = instance.getCheckpoint(ordering);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewCheckpoint method, of class Codehandin.
     */
    @Test
    public void testAddNewCheckpoint() {
        System.out.println("addNewCheckpoint");
        Codehandin instance = new Codehandin();
        Checkpoint expResult = null;
        Checkpoint result = instance.addNewCheckpoint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCheckpoint method, of class Codehandin.
     */
    @Test
    public void testRemoveCheckpoint() {
        System.out.println("removeCheckpoint");
        int ordering = 0;
        Codehandin instance = new Codehandin();
        boolean expResult = false;
        boolean result = instance.removeCheckpoint(ordering);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCpschanged method, of class Codehandin.
     */
    @Test
    public void testIsCpschanged() {
        System.out.println("isCpschanged");
        Codehandin instance = new Codehandin();
        boolean expResult = false;
        boolean result = instance.isCpschanged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChanged method, of class Codehandin.
     */
    @Test
    public void testIsChanged() {
        System.out.println("isChanged");
        Codehandin instance = new Codehandin();
        boolean expResult = false;
        boolean result = instance.isChanged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBaseFolder method, of class Codehandin.
     */
    @Test
    public void testGetBaseFolder() {
        System.out.println("getBaseFolder");
        Codehandin instance = new Codehandin();
        String expResult = "";
        String result = instance.getBaseFolder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Codehandin.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Codehandin instance = new Codehandin();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
