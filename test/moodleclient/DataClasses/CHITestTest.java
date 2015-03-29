/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
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
public class CHITestTest {

    public CHITestTest() {
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
     * Test of setup method, of class CHITest.
     */
    @Test
    public void testSetup() {
        System.out.println("setup");
        Checkpoint parentCheckpoint = null;
        String baseFolder = "";
        HashMap<Integer, CHITest> tests = null;
        CHITest instance = null;
        JsonArray delfiles = null;
        instance.setup(parentCheckpoint, baseFolder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class CHITest.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        CHITest instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class CHITest.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        CHITest instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class CHITest.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        CHITest instance = null;
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuntimeargs method, of class CHITest.
     */
    @Test
    public void testGetRuntimeargs() {
        System.out.println("getRuntimeargs");
        CHITest instance = null;
        String expResult = "";
        String result = instance.getRuntimeargs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRuntimeargs method, of class CHITest.
     */
    @Test
    public void testSetRuntimeargs() {
        System.out.println("setRuntimeargs");
        String runtimeargs = "";
        CHITest instance = null;
        instance.setRuntimeargs(runtimeargs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdering method, of class CHITest.
     */
    @Test
    public void testGetOrdering() {
        System.out.println("getOrdering");
        CHITest instance = null;
        int expResult = 0;
        int result = instance.getOrdering();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMarks method, of class CHITest.
     */
    @Test
    public void testGetMarks() {
        System.out.println("getMarks");
        CHITest instance = null;
        int expResult = 0;
        int result = instance.getMarks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMarks method, of class CHITest.
     */
    @Test
    public void testSetMarks() {
        System.out.println("setMarks");
        int marks = 0;
        CHITest instance = null;
        instance.setMarks(marks);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGradeonly method, of class CHITest.
     */
    @Test
    public void testIsGradeonly() {
        System.out.println("isGradeonly");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.isGradeonly();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGradeonly method, of class CHITest.
     */
    @Test
    public void testSetGradeonly() {
        System.out.println("setGradeonly");
        boolean gradeonly = false;
        CHITest instance = null;
        instance.setGradeonly(gradeonly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIoastext method, of class CHITest.
     */
    @Test
    public void testIsIoastext() {
        System.out.println("isIoastext");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.isIoastext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIoastext method, of class CHITest.
     */
    @Test
    public void testSetIoastext() {
        System.out.println("setIoastext");
        boolean ioastext = false;
        CHITest instance = null;
        instance.setIoastext(ioastext);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInput method, of class CHITest.
     */
    @Test
    public void testGetInput() {
        System.out.println("getInput");
        CHITest instance = null;
        String expResult = "";
        String result = instance.getInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInput method, of class CHITest.
     */
    @Test
    public void testSetInput() {
        System.out.println("setInput");
        String input = "";
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.setInput(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class CHITest.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        CHITest instance = null;
        String expResult = "";
        String result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutput method, of class CHITest.
     */
    @Test
    public void testSetOutput() {
        System.out.println("setOutput");
        String output = "";
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.setOutput(output);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutputerr method, of class CHITest.
     */
    @Test
    public void testGetOutputerr() {
        System.out.println("getOutputerr");
        CHITest instance = null;
        String expResult = "";
        String result = instance.getOutputerr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputerr method, of class CHITest.
     */
    @Test
    public void testSetOutputerr() {
        System.out.println("setOutputerr");
        String outputerr = "";
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.setOutputerr(outputerr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInputFile method, of class CHITest.
     */
    @Test
    public void testSetInputFile() {
        System.out.println("setInputFile");
        String path = "";
        boolean copy = false;
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.setInputFile(path, copy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputFile method, of class CHITest.
     */
    @Test
    public void testSetOutputFile() {
        System.out.println("setOutputFile");
        String path = "";
        boolean copy = false;
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.setOutputFile(path, copy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputerrFile method, of class CHITest.
     */
    @Test
    public void testSetOutputerrFile() {
        System.out.println("setOutputerrFile");
        String path = "";
        boolean copy = false;
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.setOutputerrFile(path, copy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteInputFile method, of class CHITest.
     */
    @Test
    public void testDeleteInputFile() {
        System.out.println("deleteInputFile");
        boolean reallyDelete = false;
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.deleteInputFile(reallyDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOutputFile method, of class CHITest.
     */
    @Test
    public void testDeleteOutputFile() {
        System.out.println("deleteOutputFile");
        boolean reallyDelete = false;
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.deleteOutputFile(reallyDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOutputerrFile method, of class CHITest.
     */
    @Test
    public void testDeleteOutputerrFile() {
        System.out.println("deleteOutputerrFile");
        boolean reallyDelete = false;
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.deleteOutputerrFile(reallyDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChanged method, of class CHITest.
     */
    @Test
    public void testIsChanged() {
        System.out.println("isChanged");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.isChanged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class CHITest.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        CHITest instance = null;
        CHITest expResult = null;
        CHITest result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CHITest.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CHITest instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAllTestFolder method, of class CHITest.
     */
    @Test
    public void testDeleteAllTestFolder() {
        System.out.println("deleteAllTestFolder");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.deleteAllTestFolders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveTestUp method, of class CHITest.
     */
    @Test
    public void testMoveTestUp() {
        System.out.println("moveTestUp");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.moveTestUp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveTestDown method, of class CHITest.
     */
    @Test
    public void testMoveTestDown() {
        System.out.println("moveTestDown");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.moveTestDown();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJsonChange method, of class CHITest.
     */
    @Test
    public void testGetJsonChange() {
        System.out.println("getJsonChange");
        JsonArray deletedFiles = null;
        ArrayList<String> fileList = null;
        ArrayList<String> filePathNameList = null;
        CHITest instance = null;
        JsonObject expResult = null;
        JsonObject result = instance.getJsonChange(fileList, filePathNameList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJsonChangeString method, of class CHITest.
     */
    @Test
    public void testGetJsonChangeString() {
        System.out.println("getJsonChangeString");
        JsonArray deletedFiles = null;
        ArrayList<String> fileList = null;
        ArrayList<String> filePathNameList = null;
        CHITest instance = null;
        String expResult = "";
        String result = instance.getJsonChangeString(fileList, filePathNameList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAllTestFolders method, of class CHITest.
     */
    @Test
    public void testDeleteAllTestFolders() {
        System.out.println("deleteAllTestFolders");
        CHITest instance = null;
        boolean expResult = false;
        boolean result = instance.deleteAllTestFolders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setupNoFiles method, of class CHITest.
     */
    @Test
    public void testSetupNoFiles() {
        System.out.println("setupNoFiles");
        Checkpoint parentCheckpoint = null;
        CHITest instance = null;
        instance.setupNoFiles(parentCheckpoint);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setupFiles method, of class CHITest.
     */
    @Test
    public void testSetupFiles() {
        System.out.println("setupFiles");
        String baseFolder = "";
        CHITest instance = null;
        instance.setupFiles(baseFolder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class CHITest.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        CHITest instance = null;
        int expResult = 0;
        int result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increaseOrdering method, of class CHITest.
     */
    @Test
    public void testIncreaseOrdering() {
        System.out.println("increaseOrdering");
        CHITest instance = null;
        int expResult = 0;
        int result = instance.increaseOrdering();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decreaseOrdering method, of class CHITest.
     */
    @Test
    public void testDecreaseOrdering() {
        System.out.println("decreaseOrdering");
        CHITest instance = null;
        int expResult = 0;
        int result = instance.decreaseOrdering();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
