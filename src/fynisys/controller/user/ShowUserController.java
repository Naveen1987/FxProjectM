/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.controller.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fynisys.controller.role.AddRoleController;
import static fynisys.controller.role.AddRoleController.ReportData;
import static fynisys.controller.role.AddRoleController.data;
import fynisys.controller.role.RoleBean;
import fynisys.controller.role.RoleTableData;
import fynisys.request.service.GetRequestService;
import fynisys.request.service.PostRequestService;
import fynisys.request.service.RequestResponseInterface;
import fynisys.views.AddTabsController;
import fynisys.views.FynisysManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class ShowUserController implements Initializable {
    @FXML private VBox DisplayRoleVBox;
    @FXML TabPane tabPane;
    public static  Stage dialog;
    @FXML private TextField filterField;
    FynisysManager fyn= new FynisysManager();
    AddTabsController tabController;
    String isactive;
    List<UserBean> UserData;
    
    /**
     * Initializes the controller class.
     */
    private static final TableView<UserTableData> table = new TableView<>();
    
    public static  ObservableList<UserTableData> data = FXCollections
            .observableArrayList();
    @FXML public void ExportPdf(){
        
    }
    
    @FXML public void addUser(){
        //AddTabsController adTab = new AddTabsController();
        //adTab.AddTabsAction(fyn, "fyn");
//adTab.newTab();
//System.out.println("Tabe Pane"+tabPane.getParent());
tabController.newTab("AddUser");
    }
    
    public void setParentController( AddTabsController context){
        tabController=context;
    }
    
    public  void populate(final List<UserBean> user) {
        UserData = user;
        user.forEach(p -> data.add(new UserTableData(p)));
        
    }
    
    public List<UserBean> retreiveDate(){
        try {
            GetRequestService getRequestService = new GetRequestService();
            getRequestService.getRequestService(responseCallback, "getalluser");
        } catch (IOException ex) {
            Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<UserBean>();
    }
    
    private final RequestResponseInterface responseCallback = new RequestResponseInterface() {
        @Override
        public void failureResponse(String errorMsg) {
            
            
        }
        @Override
        public void successResponse(String response) {
            
            System.out.println("response - "+response);
            data.clear();
            ObjectMapper mapper = new ObjectMapper();
            try {
                populate(Arrays.asList(mapper.readValue(response, UserBean[].class)));
            } catch (IOException ex) {
                Logger.getLogger(ShowUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
    
    
    
    public void deactivateUser(String userId){
        ActiveDeactiveUserBean adbean= new ActiveDeactiveUserBean();
        adbean.setUserid(userId);
        adbean.setModifiedby("fyn");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        PostRequestService deactivateUserPostRequest = new PostRequestService();
        deactivateUserPostRequest.postRequestService(gson.toJson(adbean), "deactiveuser", deactivateUserResponseCallback);
    }
    
    
    private final RequestResponseInterface deactivateUserResponseCallback = new RequestResponseInterface() {
        @Override
        public void failureResponse(String errorMsg) {
            System.out.println("Error - "+errorMsg);
            
        }
        @Override
        public void successResponse(String response) {
            
            System.out.println("response - "+response);
            populate(retreiveDate());
            
        }
    };
    
    public void activateUser(String userId){
        ActiveDeactiveUserBean adbean= new ActiveDeactiveUserBean();
        adbean.setUserid(userId);
        adbean.setModifiedby("fyn");
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        PostRequestService activateUserPostRequest = new PostRequestService();
        activateUserPostRequest.postRequestService(gson.toJson(adbean), "activeuser", deactivateUserResponseCallback);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        FilteredList<UserTableData> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (data.getDisplayname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches enteredBy.
                } else if (data.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches role name.
                }else if (data.getRole().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches role type.
                }else if (data.getUserid().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches role type.
                    
                }return false; // Does not match.
            });
        });
        SortedList<UserTableData> sortedData = new SortedList<>(filteredData);
        
        table.getStylesheets().add("/fynisys/css/user/showuser.css");
        
        TableColumn displayNameCol = new TableColumn("DISPLAY NAME");
        displayNameCol.setMinWidth(200);
        displayNameCol.setCellValueFactory(
                new PropertyValueFactory<>("displayname"));
        
        TableColumn userIdCol = new TableColumn("USER ID");
        userIdCol.setMinWidth(180);
        userIdCol.setCellValueFactory(
                new PropertyValueFactory<>("userid"));
        
        TableColumn roleCol = new TableColumn("ROLE");
        roleCol.setMinWidth(180);
        roleCol.setCellValueFactory(
                new PropertyValueFactory<>("role"));
        
        TableColumn enteredByCol = new TableColumn("ENTERED BY");
        enteredByCol.setMinWidth(200);
        enteredByCol.setCellValueFactory(
                new PropertyValueFactory<>("enteredby"));
        
        TableColumn dateCol = new TableColumn("DATE");
        dateCol.setMinWidth(170);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        
        TableColumn emailCol = new TableColumn("EMAIL");
        emailCol.setMinWidth(180);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        
        
        TableColumn editCol = new TableColumn("ACTIVATE/DEACTIVATE USER");
        editCol.setMinWidth(180);
        
        //System.out.println("Column text"+editCol.getCellData(0));
        //editCol.setStyle("-fx-text-fill: #185372;");
        
        
        editCol.setCellValueFactory(new PropertyValueFactory<>("isactive"));
        
        Callback<TableColumn<UserTableData, String>, TableCell<UserTableData, String>> cellFactory;
        cellFactory = //
                new Callback<TableColumn<UserTableData, String>, TableCell<UserTableData, String>>() {
                    @Override
                    public TableCell call(final TableColumn<UserTableData, String> param) {
                        final TableCell<UserTableData, String> cell;
                        cell = new TableCell<UserTableData, String>() {
                            
                            Button btn = new Button("");
                            
                            
                            
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    UserTableData usertable = getTableView().getItems().get(getIndex());
                                    if(usertable.getIsactive().equalsIgnoreCase("1")){
                                        btn.getStyleClass().remove("deactiveButton");
                                        btn.getStyleClass().add("activeButton");
                                        setGraphic(btn);
                                        setText("     ACTIVE");
                                    }else{
                                        btn.getStyleClass().remove("activeButton");
                                        btn.getStyleClass().add("deactiveButton");
                                        setGraphic(btn);
                                        setText("DEACTIVE");
                                    }
                                    btn.setOnAction(event -> {
                                        
                                        System.out.println("Is active - "+usertable.getIsactive());
                                        if(usertable.getIsactive().equalsIgnoreCase("1")){
                                            deactivateUser(usertable.getUserid());
                                        }else{
                                            activateUser(usertable.getUserid());
                                        }
                                    });
                                    //setGraphic(btn);
                                    
                                }
                                btn.setStyle("-fx-background-color: transparent; -fx-border-radius: 0; -fx-background-radius: 0;  -fx-text-fill: #185372; -fx-font-weight:bold");
                                btn.getStylesheets().add("fynisys/css/user/showuser.css");
                                btn.setPrefWidth(50);
                                
                            }
                        };
                        return cell;
                    }
                };
        editCol.setCellFactory(cellFactory);
        
        
        
        
        
        
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        table.getColumns().addAll(displayNameCol, userIdCol, roleCol,enteredByCol, dateCol, emailCol);
        table.getColumns().add(editCol);
        //table.getItems().forEach(item -> System.out.println("Specific column value is "+item.getIsactive()));
        populate(retreiveDate());
        DisplayRoleVBox.getChildren().add(table);
    }
    
}
