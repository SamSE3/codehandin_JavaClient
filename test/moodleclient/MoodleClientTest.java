/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient;

import java.io.File;
import java.io.FileNotFoundException;
import moodleclient.DataClasses.CHITest;
import moodleclient.DataClasses.Checkpoint;
import moodleclient.DataClasses.Codehandin;
import moodleclient.ReplyClasses.*;

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
public class MoodleClientTest {

    public static final String adminToken = "97dc83e488f056ab266fc3947902b8e9",
            adminUN = "aadmin", adminPW = "aadminA!1",
            teacherToken = "876461ceb0f64ca909e22d415069e210",
            teacherUN = "ateacher", teacherPW = "ateacherA!1",
            studentToken = "d1cdd2ec305cb7db8b3f3f85c8dba560",
            studentUN = "astudent", studentPW = "astudentA!1";

    public static final String fileToUpload = "C:/Users/SuperNova/Documents/NetBeansProjects/cheeseGame/src/chessgame/king.java",
            filepath = "/chessgame/", savepath = "C:/CHIFiles/";
    
    public static int aassignmentid = 12, impossibleassignmentid = 7;

    public MoodleClientTest() {
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
     * Test of getToken method, of class MoodleClient.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
//        String result = MoodleClient.getToken(adminUN, adminPW);
//        assertEquals(adminToken, result);
//        result = MoodleClient.getToken(studentUN, studentPW);
//        assertEquals(studentToken, result);
//        result = MoodleClient.getToken(teacherUN, teacherPW);
//        assertEquals(teacherToken, result);
    }

    /**
     * Test of getAssignments method, of class MoodleClient.
     */
    @Test
    public void testGetAssignments_String() {
        System.out.println("getAssignments_String");
        CHIData cd = MoodleClient.getAssignments(adminToken);
    }

    /**
     * Test of getAssignments method, of class MoodleClient.
     */
    @Test
    public void testGetAssignments_String_boolean() {
        System.out.println("getAssignments_String_boolean");
        CHIData cd = MoodleClient.getAssignments(adminToken, false);
        
        System.out.println(cd);
//        cd = MoodleClient.getAssignments(adminToken, true);
    }

    /**
     * Test of getAssignments method, of class MoodleClient.
     */
    @Test
    public void testGetAssignments_3args() {
//        System.out.println("getAssignments_3args");
//        CHIData cd = MoodleClient.getAssignments(adminToken, true, new int[]{impossibleassignmentid});
//        System.out.println(cd);
//        if (cd.warnings == null) {
//            fail("should have warning messages");
//        } else {
//            assertEquals("", cd.warnings[0].warningcode, "noIDorPermission");
//        }
//        assertNotNull("should have warning messages", cd.warnings);
//        //cd = MoodleClient.getAssignments(adminToken, true, new int[]{assignmentid});
//        cd = MoodleClient.getAssignments(adminToken, false, new int[]{impossibleassignmentid});
//        if (cd.warnings == null) {
//            fail("should have warning messages");
//        } else {
//            assertEquals("", cd.warnings[0].warningcode, "noIDorPermission");
//        }
//        assertNotNull("", cd.warnings);
        //cd = MoodleClient.getAssignments(adminToken, false, new int[]{assignmentid});
    }

    /**
     * Test of getAssignmentFileURLs method, of class MoodleClient.
     */
    @Test
    public void testGetAssignmentFileURLs() {
//        System.out.println("getAssignmentFileURLs");
//        FileDescriptions fds = MoodleClient.getAssignmentFileURLs(adminToken, assignmentid);
    }

    @Test
    public void testGetSubmissionsInfo() {
//        System.out.println("getSubmissionsInfo");
//        MoodleClient.getSubmissionsInfo(adminToken, 12);
    }

    /**
     * Test of getAllAssignmentFiles method, of class MoodleClient.
     */
    @Test
    public void testGetAllAssignmentFiles() {
//        System.out.println("getAllAssignmentFiles");
//        ArrayList<File> arrayfiles = MoodleClient.getAllAssignmentFiles(adminToken, assignmentid, savepath);
    }

    /**
     * Test of getAssignmentFiles method, of class MoodleClient.
     */
    @Test
    public void testGetSubmissionFileURLs() {
//        System.out.println("getSubmissionFileURLs");
//        FileDescriptions fds = MoodleClient.getSubmissionFileURLs(adminToken, assignmentid, false);
    }

    /**
     * Test of getAllFiles method, of class MoodleClient.
     */
    @Test
    public void testGetAllSubmissionFiles() {
//        System.out.println("testGetAllSubmissionFiles");
//        ArrayList<File> arrayfiles = MoodleClient.getAllSubmissionFiles(adminToken, assignmentid, savepath);
    }

    /**
     * Test of uploadAssignmentFileLegacy method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadAssignmentFileLegacy() throws Exception {
//        System.out.println("uploadAssignmentFileLegacy");
//        boolean result = MoodleClient.uploadAssignmentFileLegacy(adminToken, fileToUpload, filepath, assignmentid);
    }

    /**
     * Test of uploadAssignmentFileTest method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadAssignmentFileTest() throws Exception {
//        System.out.println("uploadAssignmentFileTest");
//        boolean result = MoodleClient.uploadAssignmentFileTest(adminToken, fileToUpload, filepath, assignmentid, test);
    }

    /**
     * Test of uploadAssignmentFileCheckPoint method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadAssignmentFileCheckPoint() throws Exception {
//        System.out.println("uploadAssignmentFileCheckPoint");
//        boolean result = MoodleClient.uploadAssignmentFileCheckPoint(adminToken, fileToUpload, filepath, assignmentid, checkpoint);
    }

    /**
     * Test of uploadAssignmentFileCodehandin method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadAssignmentFileCodehandin() throws Exception {
//        System.out.println("uploadAssignmentFileCodehandin");
//        boolean result = MoodleClient.uploadAssignmentFileCodehandin(adminToken, fileToUpload, filepath, assignmentid, codehandin);
    }

    /**
     * Test of uploadSubmissionFile method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadSubmissionFile() throws Exception {
//        System.out.println("uploadSubmissionFile");
//        boolean result = MoodleClient.uploadSubmissionFile(adminToken, fileToUpload, filepath, assignmentid, submissionid, submissionInfo);
    }

    /**
     * Test of uploadSubmissionFileLegacy method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadSubmissionFileLegacy() throws Exception {
//        System.out.println("uploadSubmissionFileLegacy");
//        boolean result = MoodleClient.uploadSubmissionFileLegacy(adminToken, fileToUpload, filepath, assignmentid, submissionid);
    }

    /**
     * Test of convertToDateString method, of class MoodleClient.
     */
    @Test
    public void testConvertToDateString() {
//        System.out.println("convertToDateString");
//        fail("The test case is a prototype.");
    }

    private void clean() {
        System.out.println("clean");
        CHIData cd = MoodleClient.getAssignments(adminToken, false, new int[]{aassignmentid});
        if (cd.exception != null) {
            fail("cd throughs an exception");
        }
        Codehandin chi = cd.courses[0].getCodehandins()[0];
        chi.cleanAssignment();
        ChangeObject chio = chi.getChangeData();
        System.out.println(chio);
        try {
            MoodleClient.uploadCodehandin(adminToken, null, aassignmentid, chio.changeData.toString(), true);
        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file");
        }

    }

    private void sometest() {
        System.out.println("sometest");
        CHIData cd = MoodleClient.getAssignments(adminToken, false, new int[]{aassignmentid});
        Codehandin chi = cd.courses[0].getCodehandins()[0];
        //System.out.println(chi);
        chi.setup(savepath);
        //chi.cleanAssignment();
        //System.out.println("origional cps " + chi.getCheckpoints().size());
        Checkpoint cp0 = chi.addNewCheckpoint();
        //System.out.println("cp0 ordering on creation " + cp0.getOrdering());
        cp0.setRuntimeargs("-s");
        cp0.setDescription("a cp desc");
        cp0.setMarks(5);

//        System.out.println("cp0 " + cp0);
        //System.out.println("origional cps+1 " + chi.getCheckpoints().size());
        Checkpoint cp1 = chi.addNewCheckpoint();
        //System.out.println("cp1 ordering on creation " + cp1.getOrdering());
        cp1.setDescription("cp 2 desk");
        cp1.setRuntimeargs("-r");
        cp1.setMarks(7);
//        System.out.println("cp0 ordering " + cp0.getOrdering());
//        System.out.println("cp1 ordering " + cp1.getOrdering());
        cp0.moveCheckpointDown(); //2->3
        cp1.moveCheckpointUp(); //2->1
        //System.out.println("cp0 ordering after moving " + cp0.getOrdering());
        //System.out.println("cp1 ordering after moving " + cp1.getOrdering());
//        System.out.println("cp0 ordering " + cp0.getOrdering());
//        System.out.println("cp1 ordering " + cp1.getOrdering());
        CHITest t00 = cp0.addNewTest(), t01 = cp0.addNewTest();//, t02 = cp0.addNewTest();
        t00.setDescription("a test desc");
        t00.setGradeonly(false);
        t00.setMarks(2);
        //t00.setRuntimeargs("-s");
        t00.setInputFile("C:\\Users\\SuperNova\\Documents\\uni\\Project\\exampleTestFiles\\calc\\i.txt", true);
        t00.setOutputFile("C:\\Users\\SuperNova\\Documents\\uni\\Project\\exampleTestFiles\\calc\\o.txt", true);
        //System.out.println("t00 " + t00);

        t01.setDescription("test2 desc");
        t01.setGradeonly(true);
        t01.setMarks(2);
        //t00.setRuntimeargs("-s");
        t01.setInputFile("C:\\Users\\SuperNova\\Documents\\uni\\Project\\exampleTestFiles\\calc\\i2.txt", true);
        t01.setOutputFile("C:\\Users\\SuperNova\\Documents\\uni\\Project\\exampleTestFiles\\calc\\o2.txt", true);
        t01.setOutputerrFile("C:\\Users\\SuperNova\\Documents\\uni\\Project\\exampleTestFiles\\calc\\e2.txt", true);
//        System.out.println("t01 " + t01);
//        System.out.println("t00 ordering " + t01.getOrdering());
//        System.out.println("t01 ordering " + t01.getOrdering());
        t00.moveTestDown();
        t01.moveTestUp();
        t01.moveTestDown();
        t00.moveTestUp();
        //System.out.println("\t" + cp0);
//        System.out.println("t00 ordering " + t00.getOrdering());
//        System.out.println("t01 ordering " + t01.getOrdering());
        CHITest t03 = cp0.addNewTest();
        t03.setDescription("test0 desc");
        t03.setMarks(1);
        t03.setGradeonly(true);
        //DataClasses.Test t04 = cp0.addNewTest();
//        System.out.println("t00 ordering " + t00.getOrdering());
//        System.out.println("t01 ordering " + t01.getOrdering());
        ChangeObject chio = chi.getChangeData();
        //System.out.println(chio);
        System.out.println(chio.changeData.toString());
//        for(Entry<Integer,Checkpoint> cp: chi.checkpointsO.entrySet()){
//            System.out.println(cp.getKey());
//        }
        try {
            MoodleClient.uploadCodehandin(adminToken, chio.zipFile, aassignmentid, chio.changeData.toString(), true);
        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file");
        }
    }

    /**
     * Test of uploadCodehandin method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadCodehandin() throws Exception {
        System.out.println("uploadCodehandin");
//        clean();
//        sometest();
    }

//    /**
//     * Test of downloadAndUnzipAssignmentZip method, of class MoodleClient.
//     */
//    @Test
//    public void testDownloadAndUnzipAssignmentZip() {
//        System.out.println("downloadAndUnzipAssignmentZip");
//        CHIData cd = MoodleClient.getAssignments(adminToken, false);
//        Codehandin c = cd.courses[0].codehandins[0];
//        MoodleClient.downloadAndUnzipAssignmentZip(adminToken, assignmentid, savepath, c.getContextid());
////        c.setup(savepath);
////        HashMap<Integer, Checkpoint> cps = c.getCheckpoints();
//    }
//
//    /**
//     * Test of downloadAndUnzipGradeAssignmentZip method, of class MoodleClient.
//     */
//    @Test
//    public void testDownloadAndUnzipGradeAssignmentZip() {
//        System.out.println("downloadAndUnzipGradeAssignmentZip");
//        CHIData cd = MoodleClient.getAssignments(adminToken, false);
//        Codehandin c = cd.courses[0].codehandins[0];
//        MoodleClient.downloadAndUnzipGradeAssignmentZip(adminToken, assignmentid, savepath, c.getContextid());
//    }
    /**
     * Test of uploadSubmission method, of class MoodleClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUploadSubmission() throws Exception {
        System.out.println("uploadSubmission");
        //CHIData cd = MoodleClient.getAssignments(adminToken, false);        
        //int assignmentid = cd.courses[0].codehandins[0].getId();
        File zipFile = new File(fileToUpload);
        boolean test = false, submit = false, legacy = true;
        UploadReply result = MoodleClient.uploadSubmission(adminToken, zipFile, aassignmentid, test, submit, legacy);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of downloadAndUnzipAssignmentZip method, of class MoodleClient.
     */
    @Test
    public void testDownloadAndUnzipAssignmentZip() {
        System.out.println("downloadAndUnzipAssignmentZip");
//        String token = "";
//        int assignmentid = 0;
//        String savePath = "";
//        int contextid = 0;
//        boolean expResult = false;
//        boolean result = MoodleClient.downloadAndUnzipAssignmentZip(token, assignmentid, savePath, contextid);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadAndUnzipGradeAssignmentZip method, of class MoodleClient.
     */
    @Test
    public void testDownloadAndUnzipGradeAssignmentZip() {
        System.out.println("downloadAndUnzipGradeAssignmentZip");
//        String token = "";
//        int assignmentid = 0;
//        String savePath = "";
//        int contextid = 0;
//        boolean expResult = false;
//        boolean result = MoodleClient.downloadAndUnzipGradeAssignmentZip(token, assignmentid, savePath, contextid);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of prettyJsonStringPrint method, of class MoodleClient.
     */
    @Test
    public void testPrettyJsonStringPrint() {
//        System.out.println("prettyJsonStringPrint");
//        String uglyJSONString = "{\"courses\":[{\"id\":2,\"shortname\":\"protopic\",\"codehandins\":[{\"id\":12,\"contextid\":39,\"assignname\":\"apples\",\"intro\":\"<p>fish description<br><\\/p>\",\"duedate\":\"1424361600\",\"funcpercent\":100,\"spectestonly\":false,\"mustattemptcompile\":true,\"proglang\":\"java\",\"proglangid\":3},{\"id\":15,\"contextid\":42,\"assignname\":\"submit tester\",\"intro\":\"for testing submit button press required and submit statement acceptance<br><p><br><\\/p>\",\"duedate\":\"1426867200\",\"funcpercent\":100,\"spectestonly\":false,\"mustattemptcompile\":true,\"proglang\":\"java\",\"proglangid\":3}]}]}";
//        //String uglyJSONString = "{\"keys\":{\"assignmentid\":{\"type\":\"int\",\"allownull\":true,\"desc\":\"The assignment id to operate on\",\"required\":1,\"default\":null},\"plugindata\":{\"keys\":{\"files_filemanager\":{\"type\":\"int\",\"allownull\":true,\"desc\":\"The id of a draft area containing files for this submission.\",\"required\":1,\"default\":null},\"onlinetext_editor\":{\"keys\":{\"text\":{\"type\":\"text\",\"allownull\":true,\"desc\":\"The text for this submission.\",\"required\":1,\"default\":null},\"format\":{\"type\":\"int\",\"allownull\":true,\"desc\":\"The format for this submission\",\"required\":1,\"default\":null},\"itemid\":{\"type\":\"int\",\"allownull\":true,\"desc\":\"The draft area id for files attached to the submission\",\"required\":1,\"default\":null}},\"desc\":\"\",\"required\":1,\"default\":null}},\"desc\":\"\",\"required\":1,\"default\":null}},\"desc\":\"\",\"required\":1,\"default\":null}";
//        MoodleClient.prettyJsonStringPrint(uglyJSONString);
    }

}
