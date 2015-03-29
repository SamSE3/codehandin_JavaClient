/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleclient.DataClasses;

import java.util.Arrays;

/**
 *
 * @author SuperNova
 */
public class Course {

    private int id;
    private String shortname;
    //ArrayList<Codehandin> codehandins = new ArrayList<>();
    private Codehandin[] codehandins;

    /**
     * sets up all the codehandin assignments (linking their files and ordering
     * them by their specified order)
     *
     * @param baseFolder
     */
    public void setup(String baseFolder) {
        if (codehandins == null) {
            //throw error?
        } else {
            for (Codehandin c : codehandins) {
                c.setup(baseFolder);
            }
        }
    }

    public void setupNoFiles() {
        if (codehandins == null) {
            //throw error?
        } else {
            for (Codehandin c : codehandins) {
                c.setupNoFiles();
            }
        }
    }
    
    public void setupFiles(String baseFolder) {
        if (codehandins == null) {
            //throw error?
        } else {
            for (Codehandin c : codehandins) {
                c.setupFiles(baseFolder);
            }
        }
    }    

    public int getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public Codehandin[] getCodehandins() {
        return codehandins;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", shortname=" + shortname + ", codehandins=" + Arrays.toString(codehandins) + '}';
    }
}
