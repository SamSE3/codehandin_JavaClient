/*
 things to do
 download zip files from pathname? or from storage?
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient;

//web client classes
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//file io classes
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//collections and logging classes
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//rest client classes (not used as not true rest)
//import org.restlet.data.MediaType;
//data classes
//util classes
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
//import com.google.gson.JsonObject;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import moodleclient.DataClasses.Course;
import moodleclient.ReplyClasses.*;

/**
 * A client to connect to the Moodle Codehandin webservice the moodle_url will
 * need to be changed to that of the Moodle site.
 *
 * @author SuperNova
 */
public class MoodleClient {

    // urls
    private static final String moodle_url = "http://localhost/",
            login_script = "login/token.php",
            webservice_script = "webservice/rest/server.php",
            upload_script_legacy = "webservice/upload.php",
            upload_script = "webservice/upload.php",
            //these scripts are included in the getlinks page!
            download_script_legacy = "webservice/pluginfile.php",
            download_script = "webservice/pluginfile.php";

    // fileareas
    private static final String CODEHANDIN_TEMP_FILEAREA = "codehandintempzips", // for zips of assignment files
            CODEHANDIN_FILEAREA = "codehandin_files",
            CODEHANDIN_ZIP_FILEAREA = "codehandin_zipfiles",
            SHORT_SERIVCE_NAME = "codehandin_ws",
            COMPONENT = "assignsubmission_codehandin_submission"; //

    // functions
    private static final String fetch_assignments = "local_codehandin_webservice_fetch_assignments",
            fetch_assignment_file_list = "local_codehandin_webservice_fetch_assignment_file_list",
            fetch_submission_file_list = "local_codehandin_webservice_fetch_submission_file_list",
            set_and_test_submission = "local_codehandin_webservice_set_and_test_submission",
            update_codehandin = "local_codehandin_webservice_update_codehandin";

    /**
     * calls a function on the Moodle webserver
     *
     * @param <T> the reply class that is returned
     * @param script the location of the script to handle the request on Moodle
     * @param params a key value pair of parameters to pass to the script should
     * include {"wstoken", token}, - the token or the username and the password
     * to use {"wsfunction", update_codehandin}, - the functional to call
     * {"codehandin", dataAsJsonString} - any data that is required by the
     * function
     * @param classtype the type of reply class (must be an extension of the
     * MoodleDataException) as this enables error handling
     * @return a object representing the reply
     */
    private static <T extends MoodleDataException> T aJSONRequest(String script, String[][] params, Class<T> classtype) {

        T out = null;

        String serverurl = moodle_url + script + "?moodlewsrestformat=json";
        StringBuilder urlParameters = new StringBuilder();

        // Send request
        HttpURLConnection con = null;
        try {
            for (String[] param : params) {
                urlParameters.append('&').append(param[0]).append('=').append(URLEncoder.encode(param[1], "UTF-8"));
            }
            con = (HttpURLConnection) new URL(serverurl).openConnection();
            con.setRequestMethod("POST"); // large codehandsins may be to big for a get URL
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Language", "en-US");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDoInput(true);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters.toString());
                wr.flush();
                wr.close();
            }

            //switch()
            //Get Response
            InputStream is = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            //out = gson.fromJson(reader, classtype);
            String reply = readInReply(reader);
            //MoodleClient.prettyJsonStringPrint(reply);
            System.out.println("\treply: \t" + reply);
            Gson gson = new Gson();
            out = gson.fromJson(reply, classtype);
        } catch (ConnectException ex) {
            System.err.println("ConnectException: " + ex.getMessage());
            try {
                out = classtype.newInstance();
                out.message = ex.getMessage();
                out.exception = "ConnectException";
                //out.debuginfo = Arrays.toString(ex.getStackTrace()).replace(',', '\n');
            } catch (InstantiationException ex1) {
                Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IllegalAccessException ex1) {
                Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return out;
    }

    /**
     * reads a reply from a reader into a string
     *
     * @param replyReader the reply reader
     * @return the reply as a string
     */
    private static String readInReply(Reader replyReader) {
        char[] arr = new char[8 * 1024];
        StringBuilder buffer = new StringBuilder();
        int numCharsRead;
        try {
            while ((numCharsRead = replyReader.read(arr, 0, arr.length)) != -1) {
                buffer.append(arr, 0, numCharsRead);
            }
        } catch (IOException ex) {
            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                replyReader.close();
            } catch (IOException ex) {
                Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return buffer.toString();
    }

    /**
     * uploads a zipfile and data (as a JSON string) if dataAsJsonString is not
     * null. If dataAsJsonString is null then it assumed that server function
     * cannot handle both a file and data, if so the data should be uploaded
     * separately using the uploadData function.
     *
     * @param token the token that uniquely identifies the user
     * @param zipFile a zip file containing the assignment or submission files
     * to upload
     * @param filepath the path of the file in the file area
     * @param itemid the id of the file upload (0 for automatic assignment)
     * @param dataAsJsonString the data to upload (codehandin, checkpoint, test
     * or submission info (submit for grading & test now)
     * @return an UploadReplyLegacyArray object
     * @throws FileNotFoundException
     */
    private static UploadReplyLegacyArray uploadDataAndFiles(String token, File zipFile, String filepath, String filearea, int itemid, String dataAsJsonString, boolean assignment) throws FileNotFoundException { //testFiles or submission files are the only two files to get
        System.out.println("UPLOAD filepath " + filepath);
        UploadReplyLegacyArray out = null;
        boolean legacy = dataAsJsonString == null;
        String uploadURL = moodle_url + File.separator + (legacy ? upload_script_legacy : upload_script);

        //sendAssign ? CODEHANDIN_TEMP_FILEAREA : CODEHANDIN_TEMP_FILEAREA;
        if (!zipFile.exists()) {
            throw new FileNotFoundException("the target file does not exist");
        }
        try {
            String serverurl = uploadURL + "?token=" + URLEncoder.encode(token, "UTF-8");
            MultipartUtility multipart = new MultipartUtility(serverurl, "UTF-8");
            multipart.addHeaderField("User-Agent", "MoodleClient-Java");
            Gson gson = new Gson();
            //only add the filepath if its not null, empty or the root
            if (filepath != null) {
                if (!"".equals(filepath) && !"/".equals(filepath)) {
                    multipart.addFormField("filepath", filepath, false);
                }
            }
            multipart.addFormField("itemid", "" + itemid, false);
            if (!legacy) {// don't upload component
                multipart.addFormField("isAssign", String.valueOf(assignment), false);
                multipart.addFormField("data", dataAsJsonString, true);
            } else {
                multipart.addFormField("filearea", filearea, false);
            }
            multipart.addFilePart("uploaded_file", zipFile);
            String reply = multipart.textFinish(); //(Class<T>)
            out = gson.fromJson(reply, UploadReplyLegacyArray.class);
        } catch (ConnectException ex) {
            System.out.println("Cannot connect to the server: Connection refused " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    /**
     * uploads data to the Moodle server for file testing of assignment updating
     *
     * @param token the token that uniquely identifies the user
     * @param function the function to call on the web service
     * @param dataAsJsonString the data to upload (codehandin, checkpoint, test
     * or submission info (submit for grading & test now)
     * @return
     */
    private static UploadReply uploadData(String token, String function, String dataAsJsonString) {
        return aJSONRequest(webservice_script, new String[][]{
            {"wstoken", token},
            {"wsfunction", function},
            {"codehandin", dataAsJsonString}}, UploadReply.class);
    }

    /**
     * uploads an updated for a Codehandin assignment
     *
     * @param token the token that uniquely identifies the user
     * @param zipFile a zip file containing the assignment or submission files
     * to upload
     * @param assignmentid the id of the assignment to upload the file to
     * @param dataAsJsonString the data to upload (codehandin, checkpoint, test
     * @param legacy if true will use the separate methods to handle the file
     * upload and the codehandin assignment data upload
     * @return the reply from the server in the form of UploadReply object
     * @throws FileNotFoundException
     */
    public static UploadReply uploadCodehandin(String token, File zipFile, int assignmentid, String dataAsJsonString, boolean legacy) throws FileNotFoundException {
        System.out.println(dataAsJsonString);
        if (zipFile != null) {
            UploadReplyLegacyArray ur = uploadDataAndFiles(token, zipFile, "/", CODEHANDIN_TEMP_FILEAREA, assignmentid, legacy ? null : dataAsJsonString, true);
            System.out.println(ur);
        }
        if (legacy) {// upload data seperatly
            return uploadData(token, update_codehandin, dataAsJsonString);
        }
        return null;
    }

    /**
     *
     * uploads an updated for a Codehandin assignment
     *
     * @param token the token that uniquely identifies the user
     * @param zipFile a zip file containing the assignment or submission files
     * to upload
     * @param assignmentid the id of the assignment to upload the file to
     * @param test if the assignment should be tested or not (the results will
     * be returned if true)
     * @param submit if the assignment should be submitted for grading (if so
     * the full grading test results will be returned)
     * @param legacy if true will use the separate methods to handle the file
     * upload and the codehandin assignment data upload
     * @return the reply from the server in the form of UploadReply object
     * @throws FileNotFoundException
     */
    public static UploadReply uploadSubmission(String token, File zipFile, int assignmentid, boolean test, boolean submit, boolean legacy) throws FileNotFoundException {
        String dataAsJsonString = "{\"assignmentid\":\"" + assignmentid + "\"";
        dataAsJsonString += ",\"test\":\"" + test + "\"";
        dataAsJsonString += ",\"submit\":\"" + submit + "\"";
        System.out.println(dataAsJsonString + "}");
        if (zipFile != null) {
            UploadReplyLegacyArray ur = uploadDataAndFiles(token, zipFile, "/", "draft", 0, legacy ? null : dataAsJsonString + "}", false);
            System.out.println(ur);
            dataAsJsonString += ",\"contextid\":\"" + ur.get(0).contextid + "\"";
            dataAsJsonString += ",\"draftid\":\"" + ur.get(0).itemid + "\"";
        }
        dataAsJsonString += "}";
        System.out.println(dataAsJsonString);
        if (legacy) {// upload data seperatly
            return uploadData(token, dataAsJsonString, dataAsJsonString);
        }
        return null;
    }

    /**
     *
     * gets a token that uniquely identifies a user
     *
     * Note HTTPS does not work, even if under Home / ► Site administration / ►
     * Security / ► HTTP security everything for HTTPS logins is set to true
     *
     * @param username the user's Moodle username
     * @param password the users Moodle password
     * @return a ReplyMoodleToken object (contains the token or any errors)
     */
    public static ReplyMoodleToken getToken(String username, String password) {
        ReplyMoodleToken aTC = aJSONRequest(login_script,
                new String[][]{
                    {"username", username},
                    {"password", password},
                    {"service", SHORT_SERIVCE_NAME}}, ReplyMoodleToken.class);
        return aTC;
    }

    /**
     * get the basic details of all assignment (no checkpoint and test details)
     *
     * @param token the token that uniquely identifies the user and their PW
     * @return the details of an assignment as JsonObject
     */
    public static CHIData getAssignments(String token) {
        return getAssignments(token, true, false, new int[]{});
    }

    /**
     * get the details of all assignments with all details (includes checkpoint
     * and test details)
     *
     * @param token the token that uniquely identifies the user
     * @param getproglang if true will attach a list of supported programming languages
     * @return the details of an assignment as JsonObject
     */
    public static CHIData getAssignmentsAll(String token, boolean getproglang) {
        return getAssignments(token, false, getproglang, new int[]{});
    }

    /**
     * get the details of one or more assignment as basic (no checkpoint and
     * test details) or not basic (includes checkpoint and test details)
     *
     * @param token the token that uniquely identifies the user
     * @param basic true - minimal details, false - all details (includes
     * checkpoints and test details)
     * @param getproglang if true will attach a list of supported programming languages
     * @param assignmentids the ids of the assignments to get details for
     * @return the details of an assignment as JsonObject
     */
    public static CHIData getAssignments(String token, boolean basic, boolean getproglang, int[] assignmentids) {
        String[][] qps = new String[assignmentids.length + 4][2];
        qps[0] = new String[]{"wstoken", token};
        qps[1] = new String[]{"wsfunction", fetch_assignments};
        qps[2] = new String[]{"basic", (basic ? "1" : "0")};
        qps[3] = new String[]{"getproglangs", (getproglang ? "1" : "0")};
        for (int i = 0; i < (assignmentids.length); i++) {
            qps[4 + i] = new String[]{"assignmentids[" + i + "]", Integer.toString(assignmentids[i])};
        }
        return aJSONRequest(webservice_script, qps, CHIData.class);
    }

    /**
     * get the URLs and pathnames of all submitted files for an assignment,
     * allowing them to be downloaded using the
     * {@link #getFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * or {@link #getAllFiles(java.lang.String, int, java.lang.String)} methods
     *
     * @param token the token that uniquely identifies the user
     * @param assignmentid
     * @param createzip
     * @return a JsonArray of the fileURLs and their pathnames
     */
    public static FileDescriptions getSubmissionFileURLs(String token, int assignmentid, boolean createzip) {
        return aJSONRequest(webservice_script,
                new String[][]{
                    {"wstoken", token},
                    {"wsfunction", fetch_submission_file_list},
                    {"assignmentid", String.valueOf(assignmentid)},
                    {"createzip", createzip ? "1" : "0"}}, FileDescriptions.class);
    }

    /**
     * get the URLs and pathnames of all files for an assignment, allowing them
     * to be downloaded using the
     * {@link #getFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * or {@link #getAllFiles(java.lang.String, int, java.lang.String)} methods
     *
     * @param token the token that uniquely identifies the user
     * @param assignmentid the id of the assignment
     * @return a JsonArray of the fileURLs and their pathnames
     */
    public static FileDescriptions getAssignmentFileURLs(String token, int assignmentid) {
        return aJSONRequest(webservice_script,
                new String[][]{
                    {"wstoken", token},
                    {"wsfunction", fetch_assignment_file_list},
                    {"assignmentid", Integer.toString(assignmentid)}}, FileDescriptions.class);
    }

    /**
     *
     * downloads and unzips the files for an assignment
     *
     * @param token the token that uniquely identifies the user
     * @param assignmentid the id of the assignment
     * @param savePath the path to extract the assignment files into
     * @param contextid the context under which the files are held under
     * @return
     */
    public static boolean downloadAndUnzipAssignmentZip(String token, int assignmentid, String savePath, int contextid) {
        FileDesc fd = new FileDesc();
        fd.filepath = "/" + assignmentid + "/az/" + assignmentid + ".zip";
        fd.fileurl = moodle_url + download_script_legacy + "/" + contextid + "/" + COMPONENT + "/" + CODEHANDIN_ZIP_FILEAREA + "/" + assignmentid + "/t" + 12 + ".zip";
        downloadAndUnzipFile(token, assignmentid, fd, savePath);
        return true;
    }

    /**
     * downloads and unzips the files for an assignment (including grade only
     * files) access to this zip is restricted on the Moodle side
     *
     * @param token the token that uniquely identifies the user
     * @param assignmentid the id of the assignment
     * @param savePath the path to extract the assignment files into
     * @param contextid the context under which the files are held under
     * @return
     */
    public static boolean downloadAndUnzipGradeAssignmentZip(String token, int assignmentid, String savePath, int contextid) {
        FileDesc fd = new FileDesc();
        fd.filepath = assignmentid + "/az/" + assignmentid + ".zip";
        fd.fileurl = moodle_url + download_script_legacy + "/" + contextid + "/" + COMPONENT + "/" + CODEHANDIN_ZIP_FILEAREA + "/" + assignmentid + "/g" + 12 + ".zip";
        System.out.println(fd);
        downloadAndUnzipFile(token, assignmentid, fd, savePath);
        return true;
    }

    /**
     * download a file from the Moodle server
     *
     * @param token the token that uniquely identifies the user
     * @param fd a file description contains the base path of the file(filepath)
     * and the url to request the file from (fileurl)
     * @param savePath the path to extract the assignment files into
     *
     * @return File the downloaded file
     */
    private static File downloadFile(String token, FileDesc fd, String savePath) { //testFiles or submission files are the only two files to get

        System.out.println("filepath " + savePath + fd.filepath);
        File targetFile = new File(savePath + fd.filepath);

        File downloadFolder = targetFile.getParentFile();
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs(); // just in case make the parent directories as well
        }
        // Send request
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection con = null;
        try {
            String serverurl = fd.fileurl + "?token=" + URLEncoder.encode(token, "UTF-8"); // requires token not wstoken!!!!!!
            con = (HttpURLConnection) new URL(serverurl).openConnection();
            con.setRequestMethod("GET"); // file request should never be to long
            //con.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);//"application/x-www-form-urlencoded"
            con.setRequestProperty("Content-Language", "en-US");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDoInput(true);
            //Get Response as File
            int code = con.getResponseCode();
            if (code == 500) {
                System.err.println("server not running maybe?");
                targetFile = null;
            } else if (code == 404) {
                System.err.println("file not found or is not accessible");
                targetFile = null;
            } else if (con.getResponseCode() == 200) {
                input = con.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//             String reply = readInReply(reader);
//            System.out.println("REPLY: "+reply);
                // can't use the above to read in files ... just to it the most simple way
                output = new FileOutputStream(targetFile);
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } else {
                System.err.println("other status returned");
                targetFile = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(MoodleClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return targetFile;
    }

    /**
     * downloads a file from Moodle and unzips it
     *
     * @param token the token that uniquely identifies the user
     * @param assignmentid the id of the assignment
     * @param fd a file description contains the base path of the file(filepath)
     * and the url to request the file from (fileurl)
     * @param savePath the path to extract the assignment files into
     * @return
     */
    private static boolean downloadAndUnzipFile(String token, int assignmentid, FileDesc fd, String savePath) {
        File targetFile = downloadFile(token, fd, savePath);
        if (targetFile == null) {
            return false;
        }
        if (!targetFile.exists()) {
            return false;
        }
        return ZipUtility.unZipIt(targetFile, savePath + assignmentid + "/a/");
    }

    /**
     * download all submission files from the server (uses the zipped assignment
     * file rather than individual files)
     *
     * @todo maybe throw an error if no files can be found
     * @param token the token that uniquely identifies the user
     * @param assignmentid the id of the assignment
     * @param savePath the path to extract the assignment files into
     * @return ArrayList<File> of all downloaded files
     */
    public static ArrayList<File> getAllSubmissionFiles(String token, int assignmentid, String savePath) {
        FileDescriptions fds = getSubmissionFileURLs(token, assignmentid, false);
        ArrayList<File> files = new ArrayList();
        if (fds.files != null) {
            fds.files.stream().forEach((fd) -> {
                files.add(downloadFile(token, fd, savePath));
            });
            return files;
        }
        return null;
    }

    /**
     * download all assignment files from the server (uses the zipped assignment
     * file rather than individual files)
     *
     * @todo maybe throw an error if no files can be found
     * @param token the token that uniquely identifies the user
     * @param assignmentid the id of the assignment
     * @param savePath the path to extract the assignment files into
     * @return ArrayList<File> of all downloaded files
     */
    public static ArrayList<File> getAllAssignmentFiles(String token, int assignmentid, String savePath) {
        FileDescriptions fds = getAssignmentFileURLs(token, assignmentid);
        ArrayList<File> files = new ArrayList();
        if (fds.files != null) {
            fds.files.stream().forEach((fd) -> {
                files.add(downloadFile(token, fd, savePath));
            });
            return files;
        }
        return null;
    }

    ////////////////////////////////
    // Utilities
    ////////////////////////////////
    /**
     * convert a UTC timestamp to a date string to the systems timezone in the
     * format: "dd-MM-yyyy HH:mm:ss"
     *
     * @param timestamp the time stamp to convert
     * @return String a string representation of the date
     */
    public static String convertToDateString(String timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Integer.getInteger(timestamp) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    /**
     * prints out a supplied JSON string in a more readable form
     *
     * @param uglyJSONString the ugly JSON string to be printed
     */
    public static void prettyJsonStringPrint(String uglyJSONString) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        String prettyJsonString = gson.toJson(jp.parse(uglyJSONString));
        System.out.println(prettyJsonString);

    }

}
