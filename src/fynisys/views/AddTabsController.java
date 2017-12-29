/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.views;

import fynisys.controller.AddUserController;
import fynisys.controller.ManageRolesController;
import fynisys.controller.header.HeaderController;
import fynisys.controller.role.AddRoleController;
import fynisys.controller.user.ShowUserController;
import fynisys.views.FynisysManager;
import fynisys.views.FynisysSplash;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;


/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class AddTabsController implements Initializable {
    
    private FynisysManager fynmanager;
    @FXML private Parent header;
    @FXML public TabPane tabPane ;
    @FXML MenuBar peopleMenuBar;
    @FXML Menu peopleMenu;
    @FXML Button logout;
    @FXML Tab roleTab;
    @FXML private  ShowUserController showUserController;
    MenuItem clientInvestor;
    MenuItem manageRoles;
    MenuItem manageUsers;
    ShowUserController userController = new ShowUserController();
    AddRoleController roleController = new AddRoleController();
    AddUserController addUserController = new AddUserController();
    
    /**
     * Initializes the controller class.
     */
    
    
    @FXML public void AddTabsAction(final FynisysManager fm, String userId){
        
        fynmanager = fm;
        logout.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                fm.registrationScreen();
            }});
        
        
        clientInvestor.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                //addRoleController.AddRoleAction(fm);
                //System.out.println("Inside the menu");
                
                try {
                    Tab loader = FXMLLoader.load(this.getClass().getResource("addRole.fxml"));
                    tabPane.getTabs().addAll(loader);
                    tabPane.getSelectionModel().select(loader);
                    
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        manageRoles.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.out.println("Inside the Sub menu");
                try {
//                    Tab loader = FXMLLoader.load(this.getClass().getResource("AddUser.fxml"));
//                    tabPane.getTabs().addAll(loader);
//                    tabPane.getSelectionModel().select(loader);


FXMLLoader loader = new FXMLLoader();
loader.setLocation(getClass().getResource("addRole.fxml"));

loader.setController(roleController);


Parent parent = loader.load();
Tab myDynamicTab = new Tab("Roles");
myDynamicTab.setClosable(true);
myDynamicTab.setContent(parent);
tabPane.getTabs().add(myDynamicTab);
tabPane.getSelectionModel().select(myDynamicTab);



                } catch (IOException ex) {
                    Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        manageUsers.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.out.println("Inside the Sub menu");
                try {
                    //tabPane.getTabs().addAll((Tab) FXMLLoader.load(this.getClass().getResource("showUser.fxml")));
                    
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("showUser.fxml"));
                    
                    loader.setController(userController);
                    
                    
                    Parent parent = loader.load();
                    Tab myDynamicTab = new Tab("User");
                    myDynamicTab.setClosable(true);
                    myDynamicTab.setContent(parent);
                    tabPane.getTabs().add(myDynamicTab);
                    tabPane.getSelectionModel().select(myDynamicTab);
                } catch (IOException ex) {
                    Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        
    }
    
    private void getMenuItems(){
//           Label lb = new Label("hello");
//     lb.setId("hello");
//     Label lb2 = new Label("hello2");
//     lb2.setId("hello2");
//CustomMenuItem slide = new CustomMenuItem(new VBox(new VBox(new HBox(lb,lb2) )));
clientInvestor = new MenuItem("Client/Investor");
Menu rm = new Menu("Relationship Manager(RM)");
Menu applicationUsers = new Menu("Application Users");
MenuItem brokers = new MenuItem("Brokers");
MenuItem Custodians = new MenuItem("Custodians");
manageRoles = new MenuItem("Manage Roles");
manageUsers = new MenuItem("Manage Users");
MenuItem userLogs = new MenuItem("User Logs");
peopleMenuBar.getMenus().add(peopleMenu);
peopleMenu.getItems().addAll(clientInvestor,rm, applicationUsers, brokers,Custodians);
applicationUsers.getItems().addAll(manageRoles,manageUsers, userLogs);
    }
    
    
    public void newTab(String tabName){
        try {
            //tabPane.getTabs().addAll((Tab) FXMLLoader.load(this.getClass().getResource("AddUser.fxml")));
            Class<?> classType = Class.forName("fynisys.controller."+tabName+"Controller");
            Constructor<?> ctor = classType.getConstructor();
            Object object = ctor.newInstance(new Object[] {});
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(tabName+".fxml"));
            loader.setController(object);
            System.out.println(""+loader.getController());
            Parent parent = loader.load();
            Tab myDynamicTab = new Tab(tabName);
            myDynamicTab.setClosable(true);
            myDynamicTab.setContent(parent);
            tabPane.getTabs().add(myDynamicTab);
            tabPane.getSelectionModel().select(myDynamicTab);
            
            
        } catch (IOException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void initialize(URL location, ResourceBundle resources)  {
        userController.setParentController(this);
        roleController.setParentController(this);
        //AddUserController.setParentController(this);
        //System.out.println("Location - "+getClass());
        try {
            tabPane.getTabs().addAll((Tab) FXMLLoader.load(this.getClass().getResource("landing.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AddTabsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getMenuItems();
    }
    
    
    
//    public void init() {
//               tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable) -> {
//            if (newValue == tab2_bar) {
//                System.out.println("- 2.Tab bar -");
//                System.out.println("xxx_tab2bar_xxxController=" + xxx_tab2bar_xxxController); //if =null => inject problem
//                xxx_tab2bar_xxxController.handleTab2ButtonBar();
//            } else if (newValue == tab1_foo) {
//                System.out.println("- 1.Tab foo -");
//                System.out.println("xxx_tab1foo_xxxController=" + xxx_tab1foo_xxxController); //if =null => inject problem
//                xxx_tab1foo_xxxController.handleTab1ButtonFoo();
//            } else {
//                System.out.println("- another Tab -");
//            }
//        });
//    }
    
}