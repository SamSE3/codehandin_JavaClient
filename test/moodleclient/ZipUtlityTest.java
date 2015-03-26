/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SuperNova
 */
public class ZipUtlityTest {
    
    public ZipUtlityTest() {
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
     * Test of zipFileOrFolder method, of class ZipUtlity.
     */
    @Test
    public void testZipFileOrFolder() {
        System.out.println("zipFolderOrFolder");
        ZipUtility.zipFileOrFolder("C:/Users/SuperNova/Documents/NetBeansProjects/cheeseGame", "C:/CHIFiles/zipTemp", "subID");
    }
    
}
