/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
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
public class CheckpointTest {

    public CheckpointTest() {
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
     * Test of getId method, of class Checkpoint.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Checkpoint instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUploadId method, of class Checkpoint.
     */
    @Test
    public void testGetUploadId() {
        System.out.println("getUploadId");
        Checkpoint instance = null;
        String expResult = "";
        String result = instance.getUploadId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Checkpoint.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Checkpoint instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Checkpoint.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Checkpoint instance = null;
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuntimeargs method, of class Checkpoint.
     */
    @Test
    public void testGetRuntimeargs() {
        System.out.println("getRuntimeargs");
        Checkpoint instance = null;
        String expResult = "";
        String result = instance.getRuntimeargs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRuntimeargs method, of class Checkpoint.
     */
    @Test
    public void testSetRuntimeargs() {
        System.out.println("setRuntimeargs");
        String runtimeargs = "";
        Checkpoint instance = null;
        instance.setRuntimeargs(runtimeargs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdering method, of class Checkpoint.
     */
    @Test
    public void testGetOrdering() {
        System.out.println("getOrdering");
        Checkpoint instance = null;
        int expResult = 0;
        int result = instance.getOrdering();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMarks method, of class Checkpoint.
     */
    @Test
    public void testGetMarks() {
        System.out.println("getMarks");
        Checkpoint instance = null;
        int expResult = 0;
        int result = instance.getMarks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMarks method, of class Checkpoint.
     */
    @Test
    public void testSetMarks() {
        System.out.println("setMarks");
        int marks = 0;
        Checkpoint instance = null;
        instance.setMarks(marks);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTest method, of class Checkpoint.
     */
    @Test
    public void testGetTest() {
        System.out.println("getTest");
        int ordering = 0;
        Checkpoint instance = null;
        CHITest expResult = null;
        CHITest result = instance.getTest(ordering);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewTest method, of class Checkpoint.
     */
    @Test
    public void testAddNewTest() {
        System.out.println("addNewTest");
        Checkpoint instance = null;
        CHITest expResult = null;
        CHITest result = instance.addNewTest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTest method, of class Checkpoint.
     */
    @Test
    public void testRemoveTest() {
        System.out.println("removeTest");
        int ordering = 0;
        Checkpoint instance = null;
        boolean expResult = false;
        boolean result = instance.removeTest(ordering);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChanged method, of class Checkpoint.
     */
    @Test
    public void testIsChanged() {
        System.out.println("isChanged");
        Checkpoint instance = null;
        boolean expResult = false;
        boolean result = instance.isChanged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOldCheckpoint method, of class Checkpoint.
     */
    @Test
    public void testGetOldCheckpoint() {
        System.out.println("getOldCheckpoint");
        Checkpoint instance = null;
        Checkpoint expResult = null;
        Checkpoint result = instance.getOldCheckpoint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Checkpoint.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Checkpoint instance = null;
        Checkpoint expResult = null;
        Checkpoint result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJsonChange method, of class Checkpoint.
     */
    @Test
    public void testGetJsonChange() {
        System.out.println("getJsonChange");
        ArrayList<String> fileList = null;
        ArrayList<String> filePathNameList = null;
        Checkpoint instance = null;
        JsonObject expResult = null;
        JsonObject result = instance.getJsonChange(fileList, filePathNameList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJsonChangeString method, of class Checkpoint.
     */
    @Test
    public void testGetJsonChangeString() {
        System.out.println("getJsonChangeString");
        ArrayList<String> fileList = null;
        ArrayList<String> filePathNameList = null;
        Checkpoint instance = null;
        String expResult = "";
        String result = instance.getJsonChangeString(fileList, filePathNameList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Checkpoint.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Checkpoint instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBaseFolder method, of class Checkpoint.
     */
    @Test
    public void testGetBaseFolder() {
        System.out.println("getBaseFolder");
        Checkpoint instance = null;
        String expResult = "";
        String result = instance.getBaseFolder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
