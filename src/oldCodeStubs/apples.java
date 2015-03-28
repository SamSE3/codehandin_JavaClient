/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldCodeStubs;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeViewBuilder;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author fabriceb
 */
public class apples extends Application {

    /**
     *
     * @author fabriceb
     */
    public class DnDCell extends TreeCell<Integer> {
        
        private TreeView<Integer> parentTree;
        
        public DnDCell(final TreeView<Integer> parentTree) {
            this.parentTree = parentTree;
            // ON SOURCE NODE.
            setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Drag detected on " + item);
                    if (item == null) {
                        return;
                    }
                    Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.put(DataFormat.PLAIN_TEXT, item.toString());
                    dragBoard.setContent(content);
                    event.consume();
                }
            });
            setOnDragDone(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    System.out.println("Drag done on " + item);
                    dragEvent.consume();
                }
            });
            // ON TARGET NODE.
//            setOnDragEntered(new EventHandler<DragEvent>() {
//                @Override
//                public void handle(DragEvent dragEvent) {
//                    System.out.println("Drag entered on " + item);
//                    dragEvent.consume();
//                }
//            });        
            setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    System.out.println("Drag over on " + item);
                    if (dragEvent.getDragboard().hasString()) {
                        int valueToMove = Integer.parseInt(dragEvent.getDragboard().getString());
                        if (valueToMove != item) {
                            // We accept the transfer!!!!!
                            dragEvent.acceptTransferModes(TransferMode.MOVE);
                        }
                    }
                    dragEvent.consume();
                }
            });
//            setOnDragExited(new EventHandler<DragEvent>() {
//                @Override
//                public void handle(DragEvent dragEvent) {
//                    System.out.println("Drag exited on " + item);
//                    dragEvent.consume();
//                }
//            });        
            setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    System.out.println("Drag dropped on " + item);
                    int valueToMove = Integer.parseInt(dragEvent.getDragboard().getString());
                    TreeItem<Integer> itemToMove = search(parentTree.getRoot(), valueToMove);
                    TreeItem<Integer> newParent = search(parentTree.getRoot(), item);
                    // Remove from former parent.
                    itemToMove.getParent().getChildren().remove(itemToMove);
                    // Add to new parent.
                    newParent.getChildren().add(itemToMove);
                    newParent.setExpanded(true);
                    dragEvent.consume();
                }
            });
        }
        
        private TreeItem<Integer> search(final TreeItem<Integer> currentNode, final int valueToSearch) {
            TreeItem<Integer> result = null;
            if (currentNode.getValue() == valueToSearch) {
                result = currentNode;
            } else if (!currentNode.isLeaf()) {
                for (TreeItem<Integer> child : currentNode.getChildren()) {
                    result = search(child, valueToSearch);
                    if (result != null) {
                        break;
                    }
                }
            }
            return result;
        }
        private Integer item;
        
        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            this.item = item;
            String text = (item == null) ? null : item.toString();
            setText(text);
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        TreeItem<Integer> treeRoot = new TreeItem(0);
        treeRoot.getChildren().add(new TreeItem(1));
        treeRoot.getChildren().add(new TreeItem(2));
        treeRoot.getChildren().add(new TreeItem(3));
        TreeView<Integer> treeView = TreeViewBuilder.<Integer>create().root(treeRoot).build();
        treeView.setCellFactory(new Callback<TreeView<Integer>, TreeCell<Integer>>() {
            @Override
            public TreeCell call(TreeView<Integer> param) {
                return new DnDCell(param);
            }
        });
        AnchorPane.setTopAnchor(treeView, 0d);
        AnchorPane.setRightAnchor(treeView, 0d);
        AnchorPane.setBottomAnchor(treeView, 0d);
        AnchorPane.setLeftAnchor(treeView, 0d);
        //
        AnchorPane root = AnchorPaneBuilder.create().children(treeView).build();
        Scene scene = new Scene(root, 300, 250);
        //
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}