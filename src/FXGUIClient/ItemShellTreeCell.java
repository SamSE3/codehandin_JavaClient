/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXGUIClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import moodleclient.DataClasses.CHITest;
import moodleclient.DataClasses.Checkpoint;
import moodleclient.DataClasses.Codehandin;
import moodleclient.DataClasses.Course;

/**
 *
 * @author SuperNova
 */
public class ItemShellTreeCell extends TreeCell<ItemShell> {

    private static ArrayList<ItemShellTreeCell> shtcs;
    private static ArrayList<String> coids;
    private static ArrayList<String> cids;
    private static ArrayList<String> cpids;
    private static ArrayList<String> tids;
    private int oldid;

    private GridPane grid;
    private int oldtype, newtype;
    private boolean notrun = true;

    //generic fields
    private Label labelId, labelOrd, labelName, labelIntroDesc;
    private Text textId, textOrd, textName, textIntroDesc;

    //course fields
    // just an id and a name
    //codehandin fields
    private Label labelDuedate, labelFuncpercent, labelProglang;
    private Text textFuncpercent;
    private CheckBox checkBoxSpectestOnly, checkBoxMustattemptcompile;
    private ComboBox comboBoxProglang;
    private DatePicker datePickerDuedate;

    //checkpoint fields
    private Label labelRuntimeargs, labelMarks;
    private Text textRuntimeargs, textMarks;
    //description runtimeargs ordering marks

    //test fields
    private CheckBox checkBoxGradeonly, checkBoxIoastext;
    private Label labelInput, labelOutput, labelOutputerr;
    private Text textInput, textOutput, textOutputerr;

    public ItemShellTreeCell() {

    }

    @Override
    protected void updateItem(ItemShell item, boolean empty) {

        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setGraphic(null);
        } else {
            if (item != null) {
                // used by all 
                grid = new GridPane();
                labelId = new Label("id: ");
                textId = new Text();
                textId.setWrappingWidth(20);

                // used by most
                labelOrd = new Label(" #: ");
                textOrd = new Text();
                textOrd.setWrappingWidth(20);
                labelName = new Label();
                labelName.setMinWidth(80);
                textName = new Text();
                textName.setWrappingWidth(80);
                labelIntroDesc = new Label();
                labelIntroDesc.setMinWidth(80);
                //textOrd.s

                oldtype = item.type;
                switch (oldtype) {
                    case 1: // course
                        Course co = item.co;
                        System.out.println("oldid " + oldid + " newid " + co.getId());
                        grid.add(labelId, 0, 0);
                        grid.add(textId, 1, 0);
                        oldid = co.getId();
                        textId.setText("" + co.getId());
                        labelName.setText("Name: ");
                        grid.add(labelName, 2, 0);
                        textName.setText(co.getShortname());
                        grid.add(textName, 3, 0);
                        break;
                    case 2: // codehandin
                        Codehandin c = item.c;
                        System.err.println(c);
                        textId.setText("" + c.getId());
                        grid.add(labelId, 0, 0);
                        grid.add(textId, 1, 0);
                        labelName.setText("Assign name: ");
                        grid.add(labelName, 2, 0);
                        textName.setText(c.getAssignname());
                        grid.add(textName, 3, 0);
                        labelDuedate = new Label("Duedate: ");
                        grid.add(labelDuedate, 4, 0);
                        datePickerDuedate = new DatePicker(); //LocalDate.(c.getDuedate())
                        grid.add(datePickerDuedate, 5, 0);
                        labelFuncpercent = new Label("Functional percentage: ");
                        grid.add(labelFuncpercent, 6, 0);
                        textFuncpercent = new Text("" + c.getFuncpercent());
                        checkBoxSpectestOnly = new CheckBox("Spec test Only: ");
                        checkBoxSpectestOnly.setSelected(c.isSpectestonly());
                        grid.add(checkBoxSpectestOnly, 7, 0);
                        checkBoxMustattemptcompile = new CheckBox("Must attempt compile: ");
                        checkBoxMustattemptcompile.setSelected(c.isMustattemptcompile());
                        labelProglang = new Label("Programming Language: ");
                        grid.add(labelProglang, 8, 0);
                        comboBoxProglang = new ComboBox();

                        labelIntroDesc.setText("Assign Intro: ");
                        grid.add(labelIntroDesc, 2, 1);
                        textIntroDesc = new Text(c.getIntro());
                        grid.add(textIntroDesc, 3, 1, 5, 1);
                        break;
                    case 3: // checkpoint
//              "id": 575,
//              "name": "",
//              "description": "a cp desc",
//              "ordering": 1,
//              "marks": 5,                        
                        Checkpoint cp = item.cp;
//                        textId.setText("" + cp.getId());
                        textOrd.setText("" + cp.getOrdering());
                        grid.add(labelOrd, 0, 0);
                        grid.add(textOrd, 1, 0);
                        labelName = new Label("Name: ");
                        grid.add(labelName, 2, 0);
                        textName = new Text(cp.getName());
                        grid.add(textName, 3, 0);
                        labelMarks = new Label("Marks: ");
                        grid.add(labelMarks, 4, 0);
                        textMarks = new Text("" + cp.getMarks());
                        textMarks.setWrappingWidth(20);
                        grid.add(textMarks, 5, 0);
                        labelIntroDesc = new Label("Description: ");
                        grid.add(labelIntroDesc, 6, 0);
                        textIntroDesc = new Text(cp.getDescription());
                        grid.add(textIntroDesc, 7, 0);

                        break;
                    case 4: // test
//                  "id" : 1012,
//                  "description": "test2 desc",
//                  "gradeonly": true,
//                  "ioastext": false,
//                  "input": "i2.txt",
//                  "output": "o2.txt",
//                  "outputerr": "e2.txt",
//                  "ordering": 1,
//                  "marks": 2                        

                        CHITest t = item.t;
                        grid.add(labelOrd, 0, 0);
                        textOrd.setText("" + t.getOrdering());
                        grid.add(textOrd, 1, 0);
                        labelMarks = new Label("Marks: ");
                        grid.add(labelMarks, 2, 0);
                        textMarks = new Text("" + t.getMarks());
                        textMarks.setWrappingWidth(20);
                        grid.add(textMarks, 3, 0);

                        checkBoxGradeonly = new CheckBox("for Grading Only:");
                        checkBoxGradeonly.setSelected(t.isGradeonly());
                        grid.add(checkBoxGradeonly, 4, 0);
                        checkBoxIoastext = new CheckBox("I/O as text (not files)");
                        checkBoxIoastext.setSelected(t.isGradeonly());
                        grid.add(checkBoxIoastext, 5, 0);

                        labelIntroDesc = new Label("Description: ");
                        grid.add(labelIntroDesc, 2, 1);
                        textIntroDesc = new Text(t.getDescription());
                        grid.add(textIntroDesc, 3, 1);

                        String i = t.getInput(),
                         o = t.getOutput(),
                         e = t.getOutputerr();

                        labelInput = new Label("    Input: ");
                        grid.add(labelInput, 2, 2);
                        textInput = new Text(i == null ? "" : i);
                        grid.add(textInput, 3, 2);
                        labelOutput = new Label("   Output: ");
                        grid.add(labelOutput, 4, 2);
                        textOutput = new Text(o == null ? "" : o);
                        grid.add(textOutput, 5, 2);
                        labelOutputerr = new Label("Outputerr: ");
                        grid.add(labelOutputerr, 6, 2);
                        textOutputerr = new Text(e == null ? "" : e);
                        grid.add(textOutputerr, 7, 2);

                        break;
                    default:
                        break;
                }
                if (item.type != 0) {
                    setGraphic(grid);
                }
            }
        }
        notrun = false;
    }
}
