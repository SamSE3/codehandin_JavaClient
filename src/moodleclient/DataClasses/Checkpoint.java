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
    private String description;
    private String runtimeargs;
    private int ordering;
    private int marks;
    HashMap<Integer, CHITest> testsO = new HashMap<>(); // ordering => test
    private ArrayList<CHITest> tests = new ArrayList<>();
    HashMap<Integer, Checkpoint> checkpointsO;
    private Checkpoint oldCheckpoint;
    private boolean changed = true;
    private JsonArray deletedts = new JsonArray();
    //private String[] vars = new String[]{"description", "runtimeargs", "ordering", "marks"};
    private Boolean[] changedVars = new Boolean[]{false, false, false, false};
    private String baseFolder;

    public Checkpoint(int ordering, HashMap<Integer, Checkpoint> checkpointsO, String baseFolder) {
        this.ordering = ordering;
        this.checkpointsO = checkpointsO;
        changed = true;
//            this.delids = delids;
        this.baseFolder = baseFolder;
        changedVars = new Boolean[]{false, false, true, false};
    }

    private Checkpoint(int id, String description, String runtimeargs, int ordering, int marks, HashMap<Integer, CHITest> testsO, Boolean[] changedVars) {
        this.id = id;
        this.description = description;
        this.runtimeargs = runtimeargs;
        this.ordering = ordering;
        this.marks = marks;
        this.testsO = testsO;
        this.changedVars = changedVars;
    }

    public void setup(String baseFolder) {
        changedVars = new Boolean[]{false, false, true, false};
        this.baseFolder = baseFolder;
        //System.out.println("cpid " + id + " baseFolder " + baseFolder + " basePath " + basePath);
        testsO = new HashMap<>();
        if (tests != null) {
            for (CHITest t : tests) {
                testsO.put(t.getOrdering(), t);
                t.setup(this, baseFolder, testsO);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getUploadId() {
        return (id == 0) ? "n" + ordering : "" + id;
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

//        public HashMap<Integer, Test> getTests() {
//            return tests;
//        }
    public CHITest getTest(int ordering) {
        return testsO.get(ordering);
    }

    public CHITest addNewTest() {
        changed = true;
        CHITest t = new CHITest(this, baseFolder, testsO);
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
        return new Checkpoint(id, description, runtimeargs, ordering, marks, (HashMap<Integer, CHITest>) testsO.clone(), changedVars);
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
        boolean hasChangedTests = testsJA.size()!=0;
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
     * @param deletedFiles
     * @param fileList
     * @param filePathNameList
     * @return this object as a JsonString
     */
    public String getJsonChangeString(ArrayList<String> fileList, ArrayList<String> filePathNameList) {
        return getJsonChange(fileList, filePathNameList).getAsString();
    }

    @Override
    public String toString() {
        return "Checkpoint{" + "id=" + (id == 0 ? "n" + ordering : id) + ", description=" + description + ", runtimeargs=" + runtimeargs + ", ordering=" + ordering + ", marks=" + marks + ", tests=" + tests + '}';
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    /**
     * moving up decrease the checkpoints ordering
     *
     * @return
     */
    public boolean moveCheckpointUp() {
        if (ordering != 0) {// there is another checkpoint above
            //System.out.println("ordering0 " + ordering + " cp size " + checkpointsO.size());
            Checkpoint checkpoint1 = checkpointsO.get(decreaseOrdering());// get the checkpoint below                
            //System.out.println("ordering0 " + ordering + " ordering1 " + checkpoint1.ordering);
            checkpoint1.increaseOrdering();
            checkpointsO.replace(ordering, this);
            checkpointsO.replace(ordering + 1, checkpoint1);
            return true;
        }
        return false;
    }

    /**
     * moving down increases the checkpoints ordering
     *
     * @return
     */
    public boolean moveCheckpointDown() {
        if (ordering < checkpointsO.size() - 1) {// there is another checkpoint above
            Checkpoint checkpoint1 = checkpointsO.get(increaseOrdering());// get the checkpoint below
            checkpoint1.decreaseOrdering();
            checkpointsO.replace(ordering, this);
            checkpointsO.replace(ordering - 1, checkpoint1);
            return true;
        }
        return false;
    }

    private int increaseOrdering() {
        changed = true;
        changedVars[2] = true;
        return ++ordering;
    }

    private int decreaseOrdering() {
        changed = true;
        changedVars[2] = true;
        return --ordering;
    }

}
