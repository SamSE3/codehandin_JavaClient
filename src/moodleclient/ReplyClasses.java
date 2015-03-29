/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import moodleclient.DataClasses.Codehandin;
import moodleclient.DataClasses.Course;

/**
 *
 * @author SuperNova
 */
public class ReplyClasses {

    ///////////////////////////////////
    //// helper methods
    ///////////////////////////////////
    public static String readFile(File aFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(aFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException | NullPointerException ex) {
            return "file not found";
        } catch (IOException ex) {
            return "can't read from file";
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {

                }
            }
        }
    }

//    public static String arrayToString(T[] array){
//        System.out.println();
//        String a = "a";
//        return a;
//    }
    ///////////////////////////////////
    //// main method
    ///////////////////////////////////
    public static void main(String[] args) {

    }

///////////////////////////////////
    //// exception and error classes
    ///////////////////////////////////    
    /**
     * Moodle exception as a java exception
     */
    public static class MoodleDataException {

        public String exception;
        public String error;
        public String errortype;
        public String errorcode;
        public String message;
        public String debuginfo;
        public Warning[] warnings;

        @Override
        public String toString() {
            return "MoodleDataException{" + "exception=" + exception + ", error=" + error + ", errortype=" + errortype + ", errorcode=" + errorcode + ", message=" + message + ", debuginfo=" + debuginfo + '}';
        }

    }

    public static class MoodleException extends Exception {
    }

    public static class MoodleDataError {
        /* to handle no tokens, cases:
         {
         "error":"No permission to create web service token for the service codehandin.",
         "stacktrace":"* line 178 of \\login\\token.php: moodle_exception thrown\n",
         "debuginfo":"\r\nError code: cannotcreatetoken",
         "reproductionlink":"http:\/\/localhost\/"
         }

         {
         "error":"Invalid token - token not found",
         "stacktrace":"* line 69 of \\webservice\\lib.php: moodle_exception thrown\n* line 61 of \\webservice\\upload.php: call to webservice->authenticate_user()\n",
         "debuginfo":"\r\nError code: invalidtoken",
         "reproductionlink":"http:\/\/localhost\/"
         }
         */

        Warning[] warnings;
        String error;
        String stacktrace;
        String debuginfo;
        String reproductionlink;
        String errorcode;

        public String getErrorCode() {
            if (errorcode == null) {
                errorcode = debuginfo.substring(14);
            }
            return errorcode;
        }

        public boolean isTokenError() {
            if (errorcode == null) {
                errorcode = debuginfo.substring(14);
            }
            return errorcode.equals("cannotcreatetoken") || errorcode.equals("invalidtoken");
        }

        @Override
        public String toString() {
            return "MoodleDataError{" + "warnings=" + Arrays.toString(warnings) + ", error=" + error + ", stacktrace=" + stacktrace + ", debuginfo=" + debuginfo + ", reproductionlink=" + reproductionlink + ", errorcode=" + errorcode + '}';
        }

    }

//    public static class MoodleErrorException extends Exception {
//        Warning[] warnings;
//
//        @Override
//        public String toString() {
//            return "MoodleErrorException{" + "warnings=" + Arrays.toString(warnings) + '}';
//        }        
//        
//    }
    public static class Warning {

        public int id;
        public int itemid;
        public String warningcode;
        public String message;

        @Override
        public String toString() {
            return "Warning{" + "id=" + id + ", itemid=" + itemid + ", warningcode=" + warningcode + ", message=" + message + '}';
        }
    }

    ///////////////////////////////////
    //// data classes - replies from server
    ///////////////////////////////////
    public static class ReplyMoodleToken extends MoodleDataException {

        public String token;
    }

    public static class MDException extends MoodleDataException {

    }

    public static class CHIData extends MoodleDataException { // extendes exception so it can handle server errors

        //ArrayList<Warning> warnings = new ArrayList<>();
        //ArrayList<
        public Course[] courses; //= new ArrayList<>();
        public Proglang[] proglangs;

        @Override
        public String toString() {
            if (exception == null) {
                return "CHIData [" + "courses= " + Arrays.toString(courses) + "]"
                        + super.toString();
            }
            return "exception= " + exception + ", errorcode= " + errorcode + ", message= " + message + ", debuginfo= " + debuginfo;
        }
    }

    public static class Proglang {

        int id;
        String name;
    }

    public static class FileDescriptions extends MoodleDataException {

        //ArrayList<Warning> warnings = new ArrayList<>();
        ArrayList<FileDesc> files = new ArrayList<>();
        FileDesc zipfile;

        @Override
        public String toString() {
            if (exception == null) {
                return "AssignFileDescriptions ["
                        + ((files != null)
                                ? "files= " + files + "]" + ((warnings != null) ? ", " : "")
                                : "")
                        + ((warnings != null)
                                ? "warnings= " + Arrays.toString(warnings) + "]"
                                : "");
            }
            return "exception= " + exception + ", errorcode= " + errorcode + ", message= " + message + ", debuginfo= " + debuginfo;
        }

    }

    public static class FileDesc {

        String filepath;
        String fileurl;

        @Override
        public String toString() {
            return "FileDesc{" + "filepath=" + filepath + ", fileurl=" + fileurl + '}';
        }

    }

    public static class UploadReplyLegacyArray extends ArrayList<UploadReplyLegacy> {

    }

    public static class UploadReply extends MoodleDataException {
        /*
         new fields
         */

        public String out;
        public boolean succeeded;
    }

    public static class UploadReplyLegacy extends MoodleDataException {

        /*
         legacy fields
         */
        String component;
        int contextid;
        int userid;
        String filearea;
        String filename;
        String filepath;
        int itemid;
        String license;
        String author;
        String source;

        @Override
        public String toString() {
            return "UploadReplyLegacy{" + "component=" + component + ", contextid=" + contextid + ", userid=" + userid + ", filearea=" + filearea + ", filename=" + filename + ", filepath=" + filepath + ", itemid=" + itemid + ", license=" + license + ", author=" + author + ", source=" + source
                    + ", exception=" + exception + ", error=" + error + ", errortype=" + errortype + ", errorcode=" + errorcode + ", message=" + message + ", debuginfo=" + debuginfo + '}';
        }

    }

    public static class ChangeObject {

        public JsonObject changeData;
        public File zipFile;
        //ArrayList<Warning> warnings = new ArrayList<>();
        public Warning[] warnings;

        public ChangeObject(JsonObject changeData, File zipFile) {
            this.changeData = changeData;
            this.zipFile = zipFile;
        }

        @Override
        public String toString() {
            return "ChangeObject{" + "changeData=" + changeData + ", zipFile=" + zipFile + ", warnings=" + Arrays.toString(warnings) + '}';
        }

    }

    ///////////////////////////////////
    //// data classes - submission
    ///////////////////////////////////
    public static class SubmissionInfo {

        int assignmentid;
        boolean test;
        boolean submitForGrading;

        public SubmissionInfo(int assignmentid, boolean test, boolean submitForGrading) {
            this.assignmentid = assignmentid;
            this.test = test;
            this.submitForGrading = submitForGrading;
        }

    }

    ///////////////////////////////////
    //// data classes - assignment
    ///////////////////////////////////
    ///////////////////////////////////
    //// data classes - student versions
    ///////////////////////////////////
}

/*

 check that test file changes are gathered even if the checkpoint and tests are not marked as changed

 */
