package FXGUIClient;

import java.io.File;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import moodleclient.DataClasses.CHITest;
import moodleclient.DataClasses.Checkpoint;
import moodleclient.DataClasses.Codehandin;
import moodleclient.DataClasses.Course;
import moodleclient.MoodleClient;
import moodleclient.ReplyClasses.*;
import static moodleclient.ReplyClasses.readFile;

public class NewFXSwingMain extends Application {

    private String adminUN = "aadmin", adminPW = "aadminA!1";

    private Stage primaryStage;
    //private User loggedUser;
    private static final Pattern PATTERNUSERNAMEFORM = Pattern.compile("[a-zA-Z]{4}\\d{4}");
    private Timeline timelineSignIn;
    //private final Text textStatus = new Text();
    private static final String[] dots = {" .", " ..", " ..."};
    private int val = 0;
    private String token;
    private static NewFXSwingMain instance;
    protected Text textStatus = new Text();
    private Proglang[] proglangs;

    ;

    public static NewFXSwingMain getInstance() {
        return instance;
    }

    public NewFXSwingMain() {
        instance = this;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(logInScene());
        //makeLogin(primaryStage);
        primaryStage.show();
    }

    private void setStatuses(String status, Text textStatus) {
        timelineSignIn = new Timeline(
                new KeyFrame(Duration.ZERO, new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        if (val == 3) {
                            val = 0;
                        }
                        textStatus.setText(status + dots[val]);
                        val++;
                    }
                }), new KeyFrame(Duration.millis(1000))
        );
        timelineSignIn.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * generates the logIn Scene
     *
     * @return
     */
    public Scene logInScene() {
        primaryStage.setTitle("CHI builder - login");

        // make the labels,fields and buttons
        Text textTitle = new Text("Welcome");
        textTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label labelFAN = new Label("FAN:");
        TextField textFieldFAN = new TextField();
        Label labelPassword = new Label("Password:");
        PasswordField passwordFieldFANPassword = new PasswordField();
        Button buttonSignIn = new Button("Sign in");
        //put the sign in button on the RHS in its own HBox
        HBox hBoxButtonSignIn = new HBox(10);
        hBoxButtonSignIn.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxButtonSignIn.getChildren().add(buttonSignIn);

        textFieldFAN.setText(adminUN);
        passwordFieldFANPassword.setText(adminPW);

        // make a login loop animation    
        // when the sign in button is pressed play the animation        
        buttonSignIn.setOnAction((ActionEvent e) -> {
            textStatus.setFill(Color.FIREBRICK);
            if (textFieldFAN.getText().isEmpty()) {
                textStatus.setText("no username input");
            } else if (passwordFieldFANPassword.getText().isEmpty()) {
                textStatus.setText("no password input");
            } //            else if (!PATTERNUSERNAMEFORM.matcher(textFieldFAN.getText()).matches()) {
            //                textStatus.setText("impossible username");
            //            } 
            else {
                textStatus.setFill(Color.SEAGREEN);
                setStatuses("Signing in", textStatus);
                timelineSignIn.play();
                Task loginTask = new LoginTask(textFieldFAN.getText(), passwordFieldFANPassword.getText(), textStatus, primaryStage);
                Thread th = new Thread(loginTask);
                th.setDaemon(true);
                th.start();
            }
        }
        );

        //add the elements to a grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);// h gap between components
        grid.setVgap(10);// v gap between components
        grid.setPadding(new Insets(25, 25, 25, 25)); // gap around the grid
        grid.add(textTitle, 0, 0, 2, 1);
        grid.add(labelFAN, 0, 1);
        grid.add(textFieldFAN, 1, 1);
        grid.add(labelPassword, 0, 2);
        grid.add(passwordFieldFANPassword, 1, 2);
        grid.add(hBoxButtonSignIn, 1, 3);
        grid.add(textStatus, 0, 4, 2, 1);

        // make the scene
        return new Scene(grid);

//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * performs the login and error handling
     */
    public class LoginTask extends Task<Integer> {

        String username, password;
        ReplyMoodleToken tokenObject;
        CHIData cd;
        Stage primaryStage;
        Text textStatus;
        int assignmentid;
        int[] assignmentids;
        int mode;
        boolean basic = true, getProglangs = true, error = false;
        String errorText;
        /**
         * the state of the task 0 for just started 1 for has logged in 2 for
         * has gotten assignments
         */
        int state;

        /**
         *
         * @param username
         * @param password
         * @param textStatus
         * @param primaryStage
         */
        public LoginTask(String username, String password, Text textStatus, Stage primaryStage) {
            this.username = username;
            this.password = password;
            this.textStatus = textStatus;
            this.primaryStage = primaryStage;
            mode = 0;
        }

        /**
         * get all assignments - the default method
         *
         * @param proglang
         */
        public void setGetAllAssignments(boolean proglang) {
            this.getProglangs = proglang;
            mode = 0;
        }

        public void setAssignmentid(int assignmentid) {
            this.assignmentid = assignmentid;
            basic = false;
            mode = 1;
        }

        public void setAssignmentids(int[] assignmentids, boolean basic, boolean proglang) {
            this.assignmentids = assignmentids;
            this.basic = basic;
            this.getProglangs = proglang;
            mode = 2;
        }

        //some of the possible error replies
        /*
         {
         "error": "Web service is not available (it doesn\u0027t exist or might be disabled)",
         "stacktrace": "* line 77 of \\login\\token.php: moodle_exception thrown\n",
         "debuginfo": "\r\nError code: servicenotavailable",
         "reproductionlink": "http://localhost/"
         }
         {
         "error": "The username was not found in the database",
         "stacktrace": "* line 196 of \\login\\token.php: moodle_exception thrown\n",
         "debuginfo": "\r\nError code: usernamenotfound",
         "reproductionlink": "http://localhost/"
         }
         "warnings": [
         {
         "item": "fetch_assignments",
         "itemid": 0,
         "warningcode": "noIDsInDB",
         "message": " There are currently no codehandin assignments in the database"
         }
         ]
         */
        @Override
        protected Integer call() throws Exception {
            if (state != 2) {
                return state;
            }
            error = false;
            if (token == null) {
                tokenObject = MoodleClient.getToken(username, password);
                if (tokenObject.token != null) {
                    token = tokenObject.token;
                    cd = MoodleClient.getAssignmentsAll(token, true);
                } else {
                    error = true;
                    if (tokenObject.error != null) {
                        if (tokenObject.error.length() > 28) {
                            errorText = tokenObject.error.substring(0, 28) + "...";
                        } else {
                            errorText = tokenObject.error;
                        }
                    } else {
                        errorText = "unknown error? no error or token returned";
                    }
                }
            } else if (mode == 0) {
                cd = MoodleClient.getAssignments(token);
            } else { // mode 1 or 2
                cd = MoodleClient.getAssignments(token, basic, getProglangs, (mode == 1) ? new int[]{assignmentid} : assignmentids);
            }
            if (cd != null) {
                if (cd.courses == null) {
                    error = true;
                    if (cd.error != null) {
                        errorText = "unknown error? no error/token returned";
                    } else {
                        if (cd.error.length() > 28) {
                            errorText = cd.error.substring(0, 28) + "...";
                        } else {
                            errorText = cd.error;
                        }
                    }
                }
            }
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            timelineSignIn.stop();
            if (error) { // there is an error in getting tokens
                textStatus.setText(errorText);
            } else if (cd.error != null) {
                textStatus.setText(cd.error);
            } else if (cd.courses != null) {
                textStatus.setText("going to assignment screen");
                if (getProglangs) {
                    proglangs = cd.proglangs;
                    getProglangs = false;
                }
                primaryStage.setScene(getAssignmentSelectorScene(cd.courses));
                primaryStage.hide();
                primaryStage.show();
            } else {
                textStatus.setText("unknown error? no error or token returned");
            }
        }
    }

    /**
     * generates the assignment display scene
     *
     * @return
     */
    private Scene getAssignmentSelectorScene(Course[] courses) {

        GridPane grid = new GridPane();
        grid.setMinHeight(990);
        grid.setMinWidth(1900);

        TreeItem<ItemShell> rootNode = new TreeItem<>(new ItemShell());
        primaryStage.setTitle("CHI builder - Assignment Selector");

        // make the labels,fields and buttons
        for (Course aCourse : courses) {
            TreeItem<ItemShell> aCourseTreeItem = new TreeItem<>(new ItemShell(aCourse));
            for (Codehandin aCHI : aCourse.getCodehandins()) {
                aCHI.setupNoFiles();
                TreeItem<ItemShell> aCHITreeItem = new TreeItem<>(new ItemShell(aCHI));
                for (Checkpoint cp : aCHI.getCheckpoints().values()) {
                    TreeItem<ItemShell> cpTreeItem = new TreeItem<>(new ItemShell(cp));
                    for (CHITest t : cp.getTests().values()) {
                        cpTreeItem.getChildren().add(new TreeItem<>(new ItemShell(t)));
                    }
                    aCHITreeItem.getChildren().add(cpTreeItem);
                }
                aCourseTreeItem.getChildren().add(aCHITreeItem);
            }
            rootNode.getChildren().add(aCourseTreeItem);
        }

        TreeView<ItemShell> treeView = new TreeView<>(rootNode);
        treeView.setShowRoot(false);
        treeView.setMinSize(1400, 700);
        //treeView.setMaxSize(val, val);
        treeView.setCellFactory(new Callback<TreeView<ItemShell>, TreeCell<ItemShell>>() {
            @Override
            public TreeCell<ItemShell> call(TreeView<ItemShell> p) {
                return new ItemShellTreeCell();
            }
        });
        grid.add(treeView, 0, 0);

//        primaryStage.setHeight(1000);
//        primaryStage.setWidth(1850);
        return new Scene(grid);
    }

    public class FetchAssignmentsTask extends Task<Void> {

//        String token;
//        Boolean basic;
//        int[] assignmentids;        
//        Text textStatus;
        CHIData cd;

//        public FetchAssignmentsTask(boolean basic) { //, int[] assignmentids, Text textStatus
//            this.basic = basic;
//            this.assignmentids = assignmentids;
//            this.textStatus = textStatus;
//        }
        @Override
        protected Void call() throws Exception {

//            cd = (assignmentids == null) ? MoodleClient.getAssignments(token, basic)
//                    : MoodleClient.getAssignments(token, basic, assignmentids);
            return null;
        }

        @Override
        protected void succeeded() {

        }
    }

    /**
     * performs the uploading of a Codehandin to web service
     */
    public class uploadCHITask extends Task<Void> {

        File zipFile;
        int assignmentid;
        String dataAsJsonString;
        boolean legacy;
        UploadReply ur;
        Text textStatus;

        public uploadCHITask(File zipFile, int assignmentid, String dataAsJsonString, boolean legacy) {
            this.zipFile = zipFile;
            this.assignmentid = assignmentid;
            this.dataAsJsonString = dataAsJsonString;
            this.legacy = legacy;
        }

        @Override
        protected Void call() throws Exception {
            ur = MoodleClient.uploadCodehandin(token, zipFile, assignmentid, dataAsJsonString, legacy);
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded(); //To change body of generated methods, choose Tools | Templates.
            timelineSignIn.stop();
            if (ur.error != null) {
                textStatus.setText(ur.error);
            } else {
                textStatus.setText(ur.out);
            }
        }
    }

}
