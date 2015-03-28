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

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", shortname=" + shortname + ", codehandins=" + Arrays.toString(codehandins) + '}';
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
    
    
}
