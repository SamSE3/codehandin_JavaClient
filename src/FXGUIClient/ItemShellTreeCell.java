/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXGUIClient;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import moodleclient.DataClasses.CHITest;
import moodleclient.DataClasses.Checkpoint;
import moodleclient.DataClasses.Codehandin;
import moodleclient.DataClasses.Course;
import moodleclient.ReplyClasses.Proglang;
import moodleclient.MoodleClient;

/**
 *
 * @author SuperNova
 */
public class ItemShellTreeCell extends TreeCell<ItemShell> {

    public static Proglang[] proglangs;
    public static String[] proglangsNames;
//    private static ArrayList<ItemShellTreeCell> shtcs;
//    private static ArrayList<String> coids;
//    private static ArrayList<String> cids;
//    private static ArrayList<String> cpids;
//    private static ArrayList<String> tids;
    private int oldid;

    private GridPane grid;
    private int oldtype, newtype;

    //generic fields
    private Label labelId, labelOrd, labelName, labelIntroDesc;
    private TextField textName, textIntroDesc;

    //course fields
    // just an id and a name
    //codehandin fields
    private Label labelDuedate, labelFuncpercent, labelProglang, labelSpectestonly, labelMustattemptcompile;
    private TextField textFuncpercent, textDuedate;
    private CheckBox checkBoxSpectestonly, checkBoxMustattemptcompile;
    private ComboBox comboBoxProglang;

    //checkpoint fields
    private Label labelRuntimeargs, labelMarks;
    private TextField textRuntimeargs, textMarks;
    //description runtimeargs ordering marks

    //test fields
    private CheckBox checkBoxGradeonly, checkBoxIoastext;
    private Label labelInput, labelOutput, labelOutputerr;
    private TextField textInput, textOutput, textOutputerr;

    private static String padText3(String in) {
        int pads = 3 - in.length();
        if (pads == 1) {
            return " " + in;
        } else if (pads == 2) {
            return "  " + in;
        }
        return in;
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

//                textId = new TextField();
//                textId.setMinWidth(20);
                // used by most
//                textOrd = new TextField();
//                textOrd.setMaxWidth(30);
                labelIntroDesc = new Label();
                labelIntroDesc.setMinWidth(80);
                //textOrd.s

                oldtype = item.type;
                switch (oldtype) {
                    case 1: // course
                        Course co = item.co;
                        oldid = co.getId();
                        //System.out.println("oldid " + oldid + " newid " + co.getId());
                        labelId = new Label("id: " + co.getId());
                        grid.add(labelId, 0, 0);
                        labelName = new Label("Course Name: " + co.getShortname());
                        labelName.setMaxWidth(180);
                        grid.add(labelName, 2, 0);
                        break;
                    case 2: // codehandin
                        Codehandin c = item.c;
                        //System.err.println(c);
                        labelId = new Label("id: " + c.getId());
                        grid.add(labelId, 0, 0);
                        labelName = new Label("Assign name: ");
                        grid.add(labelName, 1, 0);
                        textName = new TextField(c.getAssignname());
                        textName.setMaxWidth(120);
                        grid.add(textName, 2, 0);
                        labelDuedate = new Label("  Duedate: ");
                        grid.add(labelDuedate, 3, 0);
                        System.err.println(c.getDuedate());
                        textDuedate = new TextField(MoodleClient.convertToDateString(c.getDuedate()));
                        grid.add(textDuedate, 4, 0);
                        labelFuncpercent = new Label("  Functional percentage: ");
                        grid.add(labelFuncpercent, 5, 0);
                        textFuncpercent = new TextField("" + c.getFuncpercent());
                        textFuncpercent.setMaxWidth(35);
                        grid.add(textFuncpercent, 6, 0);
                        labelSpectestonly = new Label("  Spec test Only: ");
                        grid.add(labelSpectestonly, 7, 0);
                        checkBoxSpectestonly = new CheckBox();
                        checkBoxSpectestonly.setSelected(c.isSpectestonly());
                        //HBox hBoxSpectestOnly = new HBox(5.0, new Label("Spec test Only: "), checkBoxSpectestOnly);
                        //grid.add(hBoxSpectestOnly, 8, 0);
                        grid.add(checkBoxSpectestonly, 8, 0);
                        labelMustattemptcompile = new Label("  Must attempt compile: ");
                        grid.add(labelMustattemptcompile, 9, 0);
                        checkBoxMustattemptcompile = new CheckBox();
                        checkBoxMustattemptcompile.setSelected(c.isMustattemptcompile());

                        //HBox hBoxMustattemptcompile = new HBox(5.0, new Label("Must attempt compile: "), checkBoxMustattemptcompile);
                        grid.add(checkBoxMustattemptcompile, 10, 0);
                        labelProglang = new Label("  Programming Language: ");
                        grid.add(labelProglang, 11, 0);
                        comboBoxProglang = new ComboBox();
                        comboBoxProglang.getItems().addAll((Object[]) proglangsNames);
                        comboBoxProglang.setValue(c.getProglang());
                        grid.add(comboBoxProglang, 12, 0);

                        labelIntroDesc.setText("Assign Intro: ");
                        grid.add(labelIntroDesc, 2, 1);
                        textIntroDesc = new TextField(c.getIntro());
                        grid.add(textIntroDesc, 3, 1, 9, 1);
                        break;
                    case 3: // checkpoint                     
                        Checkpoint cp = item.cp;
//                        textId.setText("" + cp.getId());
                        labelOrd = new Label("#: " + cp.getOrdering());
                        grid.add(labelOrd, 0, 0);
                        labelName = new Label("cp Name: ");
                        grid.add(labelName, 1, 0);
                        textName = new TextField(cp.getName());
                        textName.setMaxWidth(120);
                        grid.add(textName, 2, 0);
                        labelMarks = new Label("Marks: ");
                        grid.add(labelMarks, 3, 0);
                        textMarks = new TextField("" + cp.getMarks());
                        textMarks.setMaxWidth(35);
                        grid.add(textMarks, 4, 0);
                        labelRuntimeargs = new Label("Runtime Arguments: ");
                        grid.add(labelRuntimeargs, 5, 0);
                        textRuntimeargs = new TextField(cp.getRuntimeargs());
                        grid.add(textRuntimeargs, 6, 0);

                        labelIntroDesc = new Label("Description: ");
                        grid.add(labelIntroDesc, 7, 0);
                        textIntroDesc = new TextField(cp.getDescription());
                        grid.add(textIntroDesc, 8, 0);

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
                        labelOrd = new Label(" #: " + t.getOrdering());
                        grid.add(labelOrd, 0, 0);
                        labelMarks = new Label("Marks: ");
                        grid.add(labelMarks, 1, 0);
                        textMarks = new TextField("" + t.getMarks());
                        textMarks.setMaxWidth(35);
                        grid.add(textMarks, 2, 0);

                        Label labelBoxGradeonly = new Label("for Grading Only: ");
                        checkBoxGradeonly = new CheckBox();
                        checkBoxGradeonly.setSelected(t.isGradeonly());
                        grid.add(labelBoxGradeonly, 3, 0);
                        grid.add(checkBoxGradeonly, 4, 0);

                        Label labelIoastext = new Label("  I/O as text (not files): ");
                        checkBoxIoastext = new CheckBox();
                        checkBoxIoastext.setSelected(t.isIoastext());
                        grid.add(labelIoastext, 5, 0);
                        grid.add(checkBoxIoastext, 6, 0);

                        labelIntroDesc = new Label("Description: ");
                        grid.add(labelIntroDesc, 2, 1, 2, 1);
                        textIntroDesc = new TextField(t.getDescription());
                        grid.add(textIntroDesc, 4, 1, 8, 1);

                        String i = t.getInput(),
                         o = t.getOutput(),
                         e = t.getOutputerr();

                        labelInput = new Label("  Input: ");
                        grid.add(labelInput, 7, 0);
                        textInput = new TextField(i.equals("0") ? "No File" : i);
                        textInput.setMinWidth(200);
                        grid.add(textInput, 8, 0);
                        labelOutput = new Label("  Output: ");
                        grid.add(labelOutput, 9, 0);
                        textOutput = new TextField(o.equals("0") ? "No File" : o);
                        textOutput.setMinWidth(200);
                        grid.add(textOutput, 10, 0);
                        labelOutputerr = new Label("  Outputerr: ");
                        grid.add(labelOutputerr, 11, 0);
                        textOutputerr = new TextField(e.equals("0") ? "No File" : e);
                        textOutputerr.setMinWidth(200);
                        grid.add(textOutputerr, 12, 0);

                        break;
                    default:
                        break;
                }
                if (item.type != 0) {
                    setGraphic(grid);
                }
            }
        }
    }
}
