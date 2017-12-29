package fynisys.controller.role;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 /*from   w  w  w.  ja va 2  s. c o  m*/
public class RoleTest extends Application {
 
    private final TableView<RoleTableData> table = new TableView<>();
    private final ObservableList<RoleTableData> data =
            FXCollections.observableArrayList(new RoleTableData("A", "B","C","D"));
    final HBox hb = new HBox();
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setWidth(450);
        stage.setHeight(550);
 
 
        TableColumn firstNameCol = new TableColumn("Role Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("roleName"));
 
        TableColumn lastNameCol = new TableColumn("Role Type");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("roleType"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol);
 
        
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
   
} 