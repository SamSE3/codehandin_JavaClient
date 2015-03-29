/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author SuperNova
 */
public class Checkpoint {

    private int id;
    private String name;
    private String description;
    private String runtimeargs;    
    private int ordering;
    private int marks;
    
    
    HashMap<Integer, CHITest> testsO = new HashMap<>(); // ordering => test
    private ArrayList<CHITest> tests = new ArrayList<>();
    //HashMap<Integer, Checkpoint> checkpointsO;
    private Checkpoint oldCheckpoint;
    private boolean changed = true;
    private JsonArray deletedts = new JsonArray();
    //private String[] vars = new String[]{"description", "runtimeargs", "ordering", "marks"};
    private Boolean[] changedVars = new Boolean[]{false, false, false, false};
    Codehandin parentCodehandin;
    private String baseFolder;

    public Checkpoint(Codehandin parentCodehandin, int ordering, String baseFolder) {
        this.parentCodehandin = parentCodehandin;
        this.ordering = ordering;
        changed = true;
//            this.delids = delids;
        this.baseFolder = baseFolder;
        changedVars = new Boolean[]{false, false, true, false};
    }

    private Checkpoint(Codehandin parentCodehandin, int id, String description, String runtimeargs, int ordering, int marks, Boolean[] changedVars) {
        this.parentCodehandin = parentCodehandin;
        this.id = id;
        this.description = description;
        this.runtimeargs = runtimeargs;
        this.ordering = ordering;
        this.marks = marks;
        this.testsO = testsO;
        this.changedVars = changedVars;
    }

    public void setup(Codehandin parentCodehandin, String baseFolder) {
        this.parentCodehandin = parentCodehandin;
        changedVars = new Boolean[]{false, false, true, false};
        this.baseFolder = baseFolder;
        //System.out.println("cpid " + id + " baseFolder " + baseFolder + " basePath " + basePath);
        testsO = new HashMap<>();
        if (tests != null) {
            for (CHITest t : tests) {
                testsO.put(t.getOrdering(), t);
                t.setup(this, baseFolder);
            }
        }
    }

    public void setupNoFiles(Codehandin parentCodehandin) {
        this.parentCodehandin = parentCodehandin;
        changedVars = new Boolean[]{false, false, true, false};
        //System.out.println("cpid " + id + " baseFolder " + baseFolder + " basePath " + basePath);
        testsO = new HashMap<>();
        if (tests != null) {
            for (CHITest t : tests) {
                testsO.put(t.getOrdering(), t);
                t.setupNoFiles(this);
            }
        }
    }

    public void setupFiles(String baseFolder) {        
        this.baseFolder = baseFolder;
        //System.out.println("cpid " + id + " baseFolder " + baseFolder + " basePath " + basePath);
        if (tests != null) {
            for (CHITest t : tests) {
                t.setupFiles(baseFolder);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getUploadId() {
        return (id == 0) ? "n" + ordering : "" + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
        changedVars[3] = true;
        changed = true;
    }

    public CHITest getTest(int ordering) {
        return testsO.get(ordering);
    }

    public HashMap<Integer, CHITest> getTests() {
        return testsO;
    }

    public CHITest addNewTest() {
        changed = true;
        CHITest t = new CHITest(this, baseFolder);
        testsO.put(testsO.size(), t);
        return t;
    }

    /**
     * removing a test will delete it!
     *
     * @param ordering
     * @return
     */
    public boolean removeTest(int ordering) {
        CHITest t = testsO.remove(ordering);
        if (t == null) {
            return false;
        }
        int tid = t.getId();
        if (tid != 0) {
            JsonObject adelt = new JsonObject();
            adelt.add("id", new JsonPrimitive(t.getId()));
            adelt.add("d", new JsonPrimitive(1));
            deletedts.add(adelt);
            changed = true;
        }
        return true;
    }

    public boolean isChanged() {
        return changed;
    }

//        public void setOldCheckpoint() {
//            oldCheckpoint = new Checkpoint(id, description, runtimeargs, ordering, marks, (HashMap<Integer, Test>) tests.clone());
//        }
    public Checkpoint getOldCheckpoint() {
        return oldCheckpoint;
    }

    @Override
    protected Checkpoint clone() throws CloneNotSupportedException {
        super.clone(); // does nothing as extends nothing, if called asks to throw CloneNotSupportedException
        return new Checkpoint(parentCodehandin, id, description, runtimeargs, ordering, marks, changedVars);
    }

    /**
     * turns this checkpoint into it's JsonObject equivalent with any unchanged
     * fields removed
     *
     * @param fileList
     * @param filePathNameList
     * @return this object as a JsonObject
     */
    public JsonObject getJsonChange(ArrayList<String> fileList, ArrayList<String> filePathNameList) {
        JsonObject result = new JsonObject();
        if (id == 0) {
            result.add("id", new JsonPrimitive("n" + ordering));
        } else {
            result.add("id", new JsonPrimitive(id));
        }
        if (changed) {
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
        }
        // add tests if they have changed
        JsonArray testsJA = new JsonArray();
        testsJA.addAll(deletedts);
        boolean hasChangedTests = testsJA.size() != 0;
        for (CHITest aTest : testsO.values()) {
            if (aTest.isChanged()) {
                testsJA.add(aTest.getJsonChange(fileList, filePathNameList));
                hasChangedTests = true;
            }
        }
        if (hasChangedTests) {
            result.add("tests", testsJA);
        }
        if (hasChangedTests || changed) {
            return result;
        }
        return null;
    }

    /**
     * turns this checkpoint into it's JsonString equivalent with any unchanged
     * fields removed
     *
     * @param fileList
     * @param filePathNameList
     * @return this object as a JsonString
     */
    public String getJsonChangeString(ArrayList<String> fileList, ArrayList<String> filePathNameList) {
        return getJsonChange(fileList, filePathNameList).getAsString();
    }

    @Override
    public String toString() {
        return "Checkpoint{" + "id=" + (id == 0 ? "n" + ordering : id) + ", name=" + name + ", description=" + description + ", runtimeargs=" + runtimeargs + ", ordering=" + ordering + ", marks=" + marks + ", tests=" + tests + '}';
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    /**
     * moves a test up, since the test orderings increase in number as they go
     * down, this will decrease the ordering number
     *
     * @param test0 the test to move up
     * @return true if the test is moved or false if it is not
     */
    public boolean moveTestUp(CHITest test0) {
        int tOrdering = test0.getOrdering();
        if (tOrdering != 0) {// there is another test below
            CHITest test1 = testsO.get(test0.decreaseOrdering());// get the test below
            test1.increaseOrdering();
            testsO.replace(tOrdering - 1, test0);
            testsO.replace(tOrdering, test1);
            return true;
        }
        return false;
    }

    /**
     * moves a test down, since the test orderings increase in number as they go
     * down, this will increase the ordering number
     *
     * @param test0 the test to move down
     * @return true if the test is moved or false if it is not
     */
    public boolean moveTestDown(CHITest test0) {
        int tOrdering = test0.getOrdering();
        if (tOrdering < testsO.size() - 1) {// there is another test above
            CHITest test1 = testsO.get(test0.increaseOrdering());// get the test below(+1 ordering) by increasing the ordering of this test by 1
            test1.decreaseOrdering(); // decrease the ordering of the other test
            testsO.replace(tOrdering - 1, test0);
            testsO.replace(tOrdering, test1);
            return true;
        }
        return false;
    }
    
        /**
     * moving up decrease the checkpoints ordering
     *
     * @return
     */
    public boolean moveCheckpointUp() {
        return parentCodehandin.moveCheckpointUp(this);
    }

    /**
     * moving down increases the checkpoints ordering
     *
     * @return
     */
    public boolean moveCheckpointDown() {
        return parentCodehandin.moveCheckpointDown(this);
    }

    int increaseOrdering() {
        changed = true;
        changedVars[2] = true;
        return ++ordering;
    }

    int decreaseOrdering() {
        changed = true;
        changedVars[2] = true;
        return --ordering;
    }

}
