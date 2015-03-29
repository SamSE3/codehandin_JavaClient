/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.util.ArrayList;
import static moodleclient.ReplyClasses.readFile;

/**
 *
 * @author SuperNova
 */
public class CHITest {

    // core fields
    private int id;

    private String description;
    private String runtimeargs;
    private int ordering;
    private int marks;
    private boolean gradeonly;
    private boolean ioastext;
    private int status;
    private String input;
    private String output;
    private String outputerr;

    // local fields
    private Checkpoint parentCheckpoint;
    private File inputFile, outputFile, outputerrFile;
//    private String oldIFilename, oldOFilename, oldEFilename;
    String baseFolder, basePath; //baseFolderOld,

    private Long inputFileLastModified, outputFileLastModified, outputerrFileLastModified; // so file modificaitons can be checked to see if a reupload is needed
    private boolean inputFileExistsOnMoodle = false, outputFileExistsOnMoodle = false, outputerrFileExistsOnMoodle = false;

    //HashMap<Integer, CHITest> tests;
    //private String[] vars = new String[]{"description", "runtimeargs", "ordering", "marks", "gradeonly", "ioastext", "input", "output", "outputerr"};
    private final Boolean[] changedVars = new Boolean[]{false, false, false, false, false, false, false, false, false};
    private boolean changed = false;

    public CHITest(Checkpoint parentCheckpoint, String baseFolder) {
        this.parentCheckpoint = parentCheckpoint;
        this.gradeonly = false;
        this.ordering = parentCheckpoint.testsO.size();
        changedVars[2] = true;
        changed = true;
        this.basePath = null;
        this.baseFolder = baseFolder;
        status = 0;
    }

    /**
     * constructor for cloning only
     *
     * @param id
     * @param description
     * @param runtimeargs
     * @param ordering
     * @param marks
     * @param gradeonly
     * @param ioastext
     * @param input
     * @param output
     * @param outputerr
     */
    private CHITest(Checkpoint parentCheckpoint, String description, String runtimeargs, int ordering, int marks, boolean gradeonly, boolean ioastext, String input, String output, String outputerr) {
        this.parentCheckpoint = parentCheckpoint;
        this.description = description;
        this.runtimeargs = runtimeargs;
        this.ordering = ordering;
        this.marks = marks;
        this.gradeonly = gradeonly;
        this.ioastext = ioastext;
        this.input = input;
        this.output = output;
        this.outputerr = outputerr;
    }

    /**
     * associate the downloaded files with this test
     *
     * @param parentCheckpoint
     * @param baseFolder
     * @param tests
     */
    public void setup(Checkpoint parentCheckpoint, String baseFolder) {
        this.parentCheckpoint = parentCheckpoint;
        setupFiles(baseFolder);
    }

    public void setupNoFiles(Checkpoint parentCheckpoint) {
        this.parentCheckpoint = parentCheckpoint;
    }

    public void setupFiles(String baseFolder) {
        this.baseFolder = baseFolder;
        if (id == 0) { // should never occur as setup should be called before tests are created
        } else {
            basePath = parentCheckpoint.getId() + "/" + id + "/";
            File inputBase = new File(this.baseFolder + this.basePath);
            if (inputBase.exists()) {
                File[] inputFiles = inputBase.listFiles();
                if (inputFiles.length > 0) {
                    inputFile = inputFiles[0];
                    inputFileLastModified = inputFile.lastModified();
                    inputFileExistsOnMoodle = true;
                }
            }
            File outputBase = new File(this.baseFolder + "o/");
            if (outputBase.exists()) {
                File[] outputFiles = outputBase.listFiles();
                if (outputFiles.length > 0) {
                    outputFile = outputFiles[0];
                    outputFileLastModified = outputFile.lastModified();
                    outputFileExistsOnMoodle = true;
                }
            }
            File outputerrBase = new File(this.baseFolder + "e/");
            if (outputerrBase.exists()) {
                File[] outputerrFiles = outputerrBase.listFiles();
                if (outputerrFiles.length > 0) {
                    outputerrFile = outputerrFiles[0];
                    outputerrFileLastModified = outputerrFile.lastModified();
                    outputerrFileExistsOnMoodle = true;
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        changedVars[0] = true;
        changed = true;
    }

    public String getRuntimeargs() {
        return runtimeargs;
    }

    public void setRuntimeargs(String runtimeargs) {
        this.runtimeargs = runtimeargs;
        changedVars[1] = true;
        changed = true;
    }

    public int getOrdering() {
        return ordering;
    }

//        public void setOrdering(int ordering) {
//            this.ordering = ordering;
//            changedVars[2] = true;
//            changed = true;
//        }
    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
        changedVars[3] = true;
        changed = true;
    }

    public boolean isGradeonly() {
        return gradeonly;
    }

    public void setGradeonly(boolean gradeonly) {
        this.gradeonly = gradeonly;
        changedVars[4] = true;
        changed = true;
    }

    public boolean isIoastext() {
        return ioastext;
    }

    public void setIoastext(boolean ioastext) {
        this.ioastext = ioastext;
        changedVars[5] = true;
        changed = true;
    }

    public String getInput() {
        return input;
    }

    public boolean setInput(String input) {
        if (ioastext) {
            this.input = input;
            changedVars[6] = true;
            changed = true;
            return true;
        }
        return false;
    }

    public String getOutput() {
        return output;
    }

    public boolean setOutput(String output) {
        if (ioastext) {
            this.output = output;
            changedVars[7] = true;
            changed = true;
            return true;
        }
        return false;
    }

    public String getOutputerr() {
        return outputerr;
    }

    public boolean setOutputerr(String outputerr) {
        if (ioastext) {
            this.outputerr = outputerr;
            changedVars[8] = true;
            changed = true;
            return true;
        }
        return false;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public File getOutputerrFile() {
        return outputerrFile;
    }

    public String getInputFileContents() {
        return readFile(inputFile);
    }

    public String getOutputFileContents() {
        return readFile(outputFile);
    }

    public String getOutputerrFileContents() {
        return readFile(outputerrFile);
    }

    /**
     * sets the input file for this test
     *
     * @param path the path to the input file
     * @param copy
     * @return true if the file path is the same that exists or if the path
     * points to a file that exists and can be read otherwise returns false
     */
    public boolean setInputFile(String path, boolean copy) {
        File source = new File(path);
        if (!source.isFile() || !source.canRead()) {
            return false;
        }
        if (inputFile != null) {
            if (source.getPath().equals(inputFile.getPath())) { // set to same file
                return true;
            }
        }
        changed = true;
        input = source.getName();
        inputFile = source;
        return false;
    }

    /**
     * set the output file for this test
     *
     * @param path the path to the input file
     * @param copy
     * @return true if the file path is the same that exists or if the path
     * points to a file that exists and can be read otherwise returns false
     */
    public boolean setOutputFile(String path, boolean copy) {
        File source = new File(path);
        if (!source.isFile() || !source.canRead()) {
            return false;
        }
        if (outputFile != null) {
            if (source.getPath().equals(outputFile.getPath())) { // set to same file
                return true;
            }
        }
        changed = true;
        output = source.getName();
        outputFile = source;
        return true;
    }

    /**
     * set the outputerr file for this test
     *
     * @param path the path to the input file
     * @param copy
     * @return true if the file path is the same that exists or if the path
     * points to a file that exists and can be read otherwise returns false
     */
    public boolean setOutputerrFile(String path, boolean copy) {
        File source = new File(path);
        if (!source.isFile() || !source.canRead()) {
            return false;
        }
        if (outputerrFile != null) {
            if (source.getPath().equals(outputerrFile.getPath())) { // set to same file
                return true;
            }
        }
        changed = true;
        outputerr = source.getName();
        outputerrFile = source;
        return true;
    }

    public boolean deleteInputFile(boolean reallyDelete) {
        boolean retval = true;
        if (reallyDelete) {
            retval = inputFile.delete();
        }
        if (inputFileExistsOnMoodle) { // only mark deletions if they originally existed on moodle
            changed = true;
            input = "1";
        }
        return retval;
    }

    public boolean deleteOutputFile(boolean reallyDelete) {
        boolean retval = true;
        if (reallyDelete) {
            retval = outputFile.delete();
        }
        if (outputFileExistsOnMoodle) {
            changed = true;
            output = "1";
        }
        return retval;
    }

    public boolean deleteOutputerrFile(boolean reallyDelete) {
        boolean retval = true;
        if (reallyDelete) {
            retval = outputerrFile.delete();
        }
        if (outputerrFileExistsOnMoodle) {
            changed = true;
            outputerr = "1";
        }
        return retval;
    }

    public boolean isChanged() {
        return changed;
    }

    /**
     * turns this test into it's JsonObject equivalent with any unchanged fields
     * removed
     *
     * @param fileList a list of the file paths under the base directory (used
     * to zip files)
     * @param filePathNameList a list of the full file paths (used to zip files)
     * @return this object as a JsonObject
     */
    public JsonObject getJsonChange(ArrayList<String> fileList, ArrayList<String> filePathNameList) {

        JsonObject result = new JsonObject();
        if (changed) {
            result.add("id", new JsonPrimitive((id == 0) ? "n" + ordering : "" + id));
            result.add("ioastext", new JsonPrimitive(ioastext));

            if (changedVars[2]) {
                result.add("ordering", new JsonPrimitive(ordering));
            }
            if (changedVars[0]) {
                result.add("description", new JsonPrimitive(description));
            }
            if (changedVars[1]) {
                result.add("runtimeargs", new JsonPrimitive(runtimeargs));
            }
            if (changedVars[3]) {
                result.add("marks", new JsonPrimitive(marks));
            }
            if (changedVars[4]) {
                result.add("gradeonly", new JsonPrimitive(gradeonly));
            }
            if (changedVars[5]) {
                result.add("ioastext", new JsonPrimitive(ioastext));
            }
            if (ioastext) {
                if (changedVars[6]) {
                    result.add("input", new JsonPrimitive(input));
                }
                if (changedVars[7]) {
                    result.add("output", new JsonPrimitive(output));
                }
                if (changedVars[8]) {
                    result.add("outputerr", new JsonPrimitive(outputerr));
                }
                if (changedVars[6] && changedVars[7]) {
                    if (changedVars[8]) {
                        result.add("status", new JsonPrimitive(3));
                    } else {
                        result.add("status", new JsonPrimitive(1));
                    }
                } else if (changedVars[6] && changedVars[8]) {
                    result.add("status", new JsonPrimitive(2));
                } else {
                    result.add("status", new JsonPrimitive(0));
                }
            } else {

            }
            String newbasePath = (gradeonly ? "g/" : "t/") + (basePath == null
                    ? parentCheckpoint.getUploadId() + "/" + ((id == 0) ? "n" + ordering + "/" : "")
                    : basePath); // if the basePath is set (origional test then use that otherwise make one on the fly) 

            boolean ie = inputFileExistsOnMoodle, oe = outputFileExistsOnMoodle, ee = outputerrFileExistsOnMoodle;

            if (input != null) {
                if (input.charAt(0) != '1') {
                    fileList.add(newbasePath + "i/" + input);
                    filePathNameList.add(inputFile.getPath());
                } else { // says the input file will be deleted so it will not exists
                    ie = false;
                }
                result.add("input", new JsonPrimitive(input));
            } else if (inputFileExistsOnMoodle) {
                if (inputFile != null) {
                    if (inputFile.lastModified() != inputFileLastModified) {
                        fileList.add(newbasePath + "i/" + input);
                        filePathNameList.add(inputFile.getPath());
                        result.add("input", new JsonPrimitive(inputFile.getName()));
                    }
                }
            }

            if (output != null) {
                if (output.charAt(0) != '1') {
                    fileList.add(newbasePath + "i/" + output);
                    filePathNameList.add(outputFile.getPath());
                } else { // says the output file will be deleted so it will not exists
                    oe = false;
                }
                result.add("output", new JsonPrimitive(output));
            } else if (outputFileExistsOnMoodle) {
                if (outputFile != null) {
                    if (outputFile.lastModified() != outputFileLastModified) {
                        fileList.add(newbasePath + "i/" + output);
                        filePathNameList.add(outputFile.getPath());
                        result.add("output", new JsonPrimitive(outputFile.getName()));
                    }
                }
            }

            if (outputerr != null) {
                if (outputerr.charAt(0) != '1') {
                    fileList.add(newbasePath + "i/" + outputerr);
                    filePathNameList.add(outputerrFile.getPath());
                } else { // says the outputerr file will be deleted so it will not exists
                    ee = false;
                }
                result.add("outputerr", new JsonPrimitive(outputerr));
            } else if (outputerrFileExistsOnMoodle) {
                if (outputerrFile != null) {
                    if (outputerrFile.lastModified() != outputerrFileLastModified) {
                        fileList.add(newbasePath + "i/" + outputerr);
                        filePathNameList.add(outputerrFile.getPath());
                        result.add("outputerr", new JsonPrimitive(outputerrFile.getName()));
                    }
                }
            }

            // statuses 0 i|o|e|oe    1 io  2 ie 3 ioe   
            if (!ie) { // no ie
                status = 0;
            } else if (oe) {
                if (ee) { //ie+oe+ee
                    status = 3;
                } else { //ie+oe
                    status = 1;
                }
            } else if (ee) { // ie+ee
                status = 2;
            } else { // only ie
                status = 0;
            }

            result.add("status", new JsonPrimitive(status));
            return result;
        }
        return null;
    }

    /**
     * turns this test into it's JsonString equivalent with any unchanged fields
     * removed
     *
     * @param fileList a list of the file paths under the base directory (used
     * to zip files)
     * @param filePathNameList a list of the full file paths (used to zip files)
     * @return this object as a JsonString
     */
    public String getJsonChangeString(ArrayList<String> fileList, ArrayList<String> filePathNameList) {
        if (changed) {
            return getJsonChange(fileList, filePathNameList).getAsString();
        }
        return null;
    }

    @Override
    protected CHITest clone() throws CloneNotSupportedException {
        super.clone(); //To change body of generated methods, choose Tools | Templates.
        return new CHITest(parentCheckpoint, description, runtimeargs, ordering, marks, gradeonly, ioastext, input, output, outputerr);
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + (id == 0 ? "n" + ordering : id) + ", description=" + description + ", gradeonly=" + gradeonly + ", runtimeargs=" + runtimeargs + ", ioastext=" + ioastext + ", input=" + input + ", output=" + output + ", outputerr=" + outputerr + ", ordering=" + ordering + ", marks=" + marks + '}';
    }

    public boolean deleteAllTestFolders() {
        File base = new File(baseFolder);
        if (inputFileExistsOnMoodle || outputFileExistsOnMoodle || outputerrFileExistsOnMoodle) {
            changed = true;
        }
        return base.delete();
    }

    public boolean moveTestUp() {
        return parentCheckpoint.moveTestUp(this);
    }

    public boolean moveTestDown() {
        return parentCheckpoint.moveTestDown(this);
    }

    protected int increaseOrdering() {
        changed = true;
        changedVars[2] = true;
        return ++ordering;
    }

    protected int decreaseOrdering() {
        changed = true;
        changedVars[2] = true;
        return --ordering;
    }

}
