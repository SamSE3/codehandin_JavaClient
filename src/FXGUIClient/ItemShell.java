/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXGUIClient;

import moodleclient.DataClasses.CHITest;
import moodleclient.DataClasses.Checkpoint;
import moodleclient.DataClasses.Codehandin;
import moodleclient.DataClasses.Course;

/**
 *
 * @author SuperNova
 */
public class ItemShell {

    public Course co;
    public Codehandin c;
    public Checkpoint cp;
    public CHITest t;
    public int type;

    /**
     * make a root item shell
     */
    public ItemShell() {
        type = 0;
    }

    /**
     * set it to hold a Course object
     *
     * @param co the Course to hold
     */
    public ItemShell(Course co) {
        this.co = co;
        type = 1;
    }

    /**
     * set it to hold a Codehandin object
     *
     * @param c the Codehandin to hold
     */
    public ItemShell(Codehandin c) {
        this.c = c;
        type = 2;
    }

    /**
     * set it to hold a Checkpoint object
     *
     * @param cp the Checkpoint to hold
     */
    public ItemShell(Checkpoint cp) {
        this.cp = cp;
        type = 3;
    }

    /**
     * set it to hold a CHITest object
     *
     * @param t the CHITest to hold
     */
    public ItemShell(CHITest t) {
        this.t = t;
        type = 4;
    }

}
