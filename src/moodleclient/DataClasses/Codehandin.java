/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import moodleclient.ReplyClasses.*;
import moodleclient.ZipUtility;

/**
 * the codehandin data class
 *
 * @todo implement changeables
 */
public class Codehandin {

    private int id;
    private int contextid;
    private String assignname;
    private String intro;
    private long duedate;
    private int funcpercent;
    private boolean spectestonly;
    /*
     private boolean spectestruntimeargs;
     private boolean spectestaruntimeargs;
     */
    private boolean mustattemptcompile;
    private int proglangid;
    private String proglang; // not sent
    private final HashMap<Integer, Checkpoint> checkpointsO = new HashMap<>(); // id => Checkpoint (new ids are negative ints))
    private JsonArray deletedcps = new JsonArray();
    private Checkpoint[] checkpoints;

    private boolean clean = false;

    private boolean cpschanged = false;
    private boolean changed = false;
    // the ids of deleted checkpoints
//    private JsonArray delcpids = new JsonArray(), delfiles = new JsonArray();
//        private String[] vars = new String[]{"assignname", "intro", "duedate", "funcpercen", "spectestonly", "mustattemptcompile", "proglangid"}; //private String proglang not sent 
    private Boolean[] changedVars = new Boolean[]{false, false, false, false, false, false, false, false, false};
    private String baseFolder;
//    private ArrayList<String> fileList, filePathNameList;

    public String getAssignname() {
        return assignname;
    }

    public void setAssignname(String assignname) {
        this.assignname = assignname;
        changed = true;
    }

    public void setup(String baseFolder) {
        this.baseFolder = baseFolder + id + "/a/";
        if (checkpoints != null) {
            for (Checkpoint cp : checkpoints) {
                cp.setup(this, this.baseFolder);
                checkpointsO.put(cp.getOrdering(), cp);
            }
        }
    }

    public void setupNoFiles() {
        if (checkpoints != null) {
            for (Checkpoint cp : checkpoints) {
                cp.setupNoFiles(this);
                checkpointsO.put(cp.getOrdering(), cp);
            }
        }
    }

    public void setupFiles(String baseFolder) {
        this.baseFolder = baseFolder + id + "/a/";
        if (checkpoints != null) {
            for (Checkpoint cp : checkpoints) {
                cp.setupFiles(this.baseFolder);
            }
        }
    }

    public void cleanAssignment() {
        clean = true;
        changed = true;
    }

    public int getId() {
        return id;
    }

    public int getContextid() {
        return contextid;
    }

    public HashMap<Integer, Checkpoint> getCheckpoints() {
        return checkpointsO;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
        changed = true;
    }

    public long getDuedate() {
        return duedate;
    }

    public void setDuedate(int duedate) {
        this.duedate = duedate;
        changed = true;
    }

    public int getFuncpercent() {
        return funcpercent;
    }

    public void setFuncpercent(int funcpercent) {
        this.funcpercent = funcpercent;
        changed = true;
    }

    public boolean isSpectestonly() {
        return spectestonly;
    }

    public void setSpectestonly(boolean spectestonly) {
        this.spectestonly = spectestonly;
        changed = true;
    }

    public boolean isMustattemptcompile() {
        return mustattemptcompile;
    }

    public void setMustattemptcompile(boolean mustattemptcompile) {
        this.mustattemptcompile = mustattemptcompile;
        changed = true;
    }

    public int getProglangid() {
        return proglangid;
    }

    public void setProglangid(int proglangid) {
        this.proglangid = proglangid;
        changed = true;
    }

    public String getProglang() {
        return proglang;
    }

    public void setProglang(String proglang) {
        this.proglang = proglang;
        changed = true;
    }

//        public HashMap<Integer, Checkpoint> getCheckpoints() {
//            return checkpoints;
//        }
    public Checkpoint getCheckpoint(int ordering) {
        return checkpointsO.get(ordering);
    }

    public Checkpoint addNewCheckpoint() {
        cpschanged = true;
        //System.out.println("cps " + checkpointsO);
        //System.out.println("cps pre add " + checkpointsO.size());
        Checkpoint cp = new Checkpoint(this, checkpointsO.size(), baseFolder);
        checkpointsO.put(checkpointsO.size(), cp);
        //System.out.println("cps post add " + checkpointsO.size());
        return cp;
    }

    public boolean removeCheckpoint(int ordering) {
        Checkpoint cp = checkpointsO.remove(ordering);
        if (cp == null) {
            return false;
        }
        if (cp.getId() != 0) {
            JsonObject adelcp = new JsonObject();
            adelcp.add("id", new JsonPrimitive(cp.getId()));
            adelcp.add("d", new JsonPrimitive(1));
            deletedcps.add(adelcp);
            cpschanged = true;
        }
        return true;
    }

    public boolean isCpschanged() {
        return cpschanged;
    }

    public boolean isChanged() {
        return changed;
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    /**
     * turns this checkpoint into it's JsonObject equivalent with any unchanged
     * fields removed
     *
     * @return this object as a JsonObject
     */
    public ChangeObject getChangeData() {
        ArrayList<String> fileList = new ArrayList<>();
        ArrayList<String> filePathNameList = new ArrayList<>();
        JsonObject result = new JsonObject();
        if (id != 0) {
            result.add("id", new JsonPrimitive(id));
            result.add("contextid", new JsonPrimitive(contextid));
        }
        // add checkpoints if they have changed
        if (clean) {
            result.add("clean", new JsonPrimitive(true));
        }
        if (changed) {
            result.add("changed", new JsonPrimitive(true)); // tells it that its the codehandin thats changed
            if (changedVars[0]) {
                result.add("assignname", new JsonPrimitive(assignname));
            }
            if (changedVars[1]) {
                result.add("intro", new JsonPrimitive(intro));
            }
            if (changedVars[2]) {
                result.add("duedate", new JsonPrimitive(duedate));
            }
            if (changedVars[3]) {
                result.add("funcpercent", new JsonPrimitive(funcpercent));
            }
            if (changedVars[4]) {
                result.add("spectestonly", new JsonPrimitive(spectestonly));
            }
//            if (changedVars[5]) {
//                result.add("spectestruntimeargs", new JsonPrimitive(spectestruntimeargs));
//            }
//            if (changedVars[6]) {
//                result.add("spectestaruntimeargs", new JsonPrimitive(spectestaruntimeargse));
//            }
            if (changedVars[7]) {
                result.add("mustattemptcompile", new JsonPrimitive(mustattemptcompile));
            }
            if (changedVars[8]) {
                result.add("proglangid", new JsonPrimitive(proglangid));
            }
        }
        JsonArray checkpointsJA = new JsonArray();
        checkpointsJA.addAll(deletedcps);
        boolean hasChangedCheckpoints = checkpointsJA.size() != 0;
        for (Checkpoint aCheckpoint : checkpointsO.values()) {
            if (aCheckpoint.isChanged()) {
                checkpointsJA.add(aCheckpoint.getJsonChange(fileList, filePathNameList));
                hasChangedCheckpoints = true;
            }
        }
        if (!fileList.isEmpty()) {
            result.add("hasfiles", new JsonPrimitive(true));
        }
        if (hasChangedCheckpoints) {
            result.add("checkpoints", checkpointsJA);
        }
        boolean ret = false;
        if (changed || hasChangedCheckpoints) {
            ret = true;
        }
        File zipFile = null;
        if (!fileList.isEmpty()) {
            zipFile = ZipUtility.zipIt(fileList, filePathNameList, baseFolder.substring(0, baseFolder.length() - 2) + "az/" + id + ".zip");
            ret = true;
        }
        return new ChangeObject(result, zipFile);
    }

    /**
     * turns this checkpoint into it's JsonString equivalent with any unchanged
     * fields removed
     *
     * @return this object as a JsonString
     */
    public String getJsonChangeString() {
        return getChangeData().changeData.getAsString();
    }

    /**
     * moving up decrease the checkpoints ordering
     *
     * @return
     */
    public boolean moveCheckpointUp(Checkpoint checkpoint0) {
        int cpOrdering = checkpoint0.getOrdering();
        if (cpOrdering != 0) {// there is another checkpoint above
            //System.out.println("ordering0 " + ordering + " cp size " + checkpointsO.size());
            Checkpoint checkpoint1 = checkpointsO.get(checkpoint0.decreaseOrdering());// get the checkpoint below                
            //System.out.println("ordering0 " + ordering + " ordering1 " + checkpoint1.ordering);
            checkpoint1.increaseOrdering();
            checkpointsO.replace(cpOrdering - 1, checkpoint0);
            checkpointsO.replace(cpOrdering, checkpoint1);
            return true;
        }
        return false;
    }

    /**
     * moving down increases the checkpoints ordering
     *
     * @return
     */
    public boolean moveCheckpointDown(Checkpoint checkpoint0) {
        int cpOrdering = checkpoint0.getOrdering();
        if (cpOrdering < checkpointsO.size() - 1) {// there is another checkpoint above
            Checkpoint checkpoint1 = checkpointsO.get(checkpoint0.increaseOrdering());// get the checkpoint below
            checkpoint1.decreaseOrdering();
            checkpointsO.replace(cpOrdering + 1, checkpoint0);
            checkpointsO.replace(cpOrdering, checkpoint1);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Codehandin{" + "id=" + id + ", assignname=" + assignname + ", intro=" + intro + ", duedate=" + duedate + ", funcpercent=" + funcpercent + ", spectestonly=" + spectestonly + ", mustattemptcompile=" + mustattemptcompile + ", proglang=" + proglang + ", proglangid=" + proglangid + ", checkpoints0=" + Arrays.toString(checkpointsO.values().toArray()) + '}';
    }

}
