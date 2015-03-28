/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXGUIClient;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.GridPane;
import moodleclient.DataClasses.Course;


/**
 *
 * @author SuperNova
 */
public class CourseTreeCell extends TreeCell<Course>{
    
        private final GridPane gridPane;
        private final Label labelId,labelShortname;

        public CourseTreeCell() {
            labelId = new Label();
            labelShortname = new Label();
            
            gridPane = new GridPane();
            gridPane.setPadding(new Insets(0,0,0,0));            
            
            gridPane.add(labelId, 0, 0);
            gridPane.add(labelShortname, 1, 0);            
        }

        @Override
        public void updateItem(Course aCourse, boolean empty) {
            super.updateItem(aCourse, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(null);
                labelId.setText("id: "+aCourse.getId());
                labelShortname.setText("shortname: "+aCourse.getId());
                setGraphic(gridPane);
            }
        }
}
