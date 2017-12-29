/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.header;

import fynisys.controller.LoginController;
import fynisys.views.AddTabsController;
import fynisys.views.FynisysManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class HeaderController implements Initializable {

    @FXML MenuBar peopleMenuBar;
    @FXML Menu peopleMenu;
    @FXML Button logout;
     private FynisysManager fynmanager;
     MenuItem firstmenuitem;
     MenuItem firstsubmenu;
     private AddTabsController tabControl;
     
    
    
     @FXML public void HeaderAction(final FynisysManager fm){
        fynmanager = fm;
//        forgetpass.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) {
//            fynmanager.forgetPasswordScreen();
//        }});
        logout.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                fm.landingScreen();
            }});
        
             firstmenuitem.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.out.println("Inside the menu");
                
                
            }});
             
             firstsubmenu.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.out.println("Inside the Sub menu");
                
            }});
    }
    
   
       
   
     
    private void getMenuItems(){
//           Label lb = new Label("hello");
//     lb.setId("hello");
//     Label lb2 = new Label("hello2");
//     lb2.setId("hello2");
     //CustomMenuItem slide = new CustomMenuItem(new VBox(new VBox(new HBox(lb,lb2) )));
         firstmenuitem = new MenuItem("FirstMenu Item");
        Menu secondmenuitem = new Menu("SecondMenu Item");
        MenuItem thirdmenuitem = new MenuItem("ThirdMenu Item");
        MenuItem fourthmenuitem = new MenuItem("ForthMenu Item");
         firstsubmenu = new MenuItem("FirstSubMenu");
        MenuItem secondsubmenu = new MenuItem("SeconSubMenu");   
     
     peopleMenuBar.getMenus().add(peopleMenu);
        peopleMenu.getItems().addAll(firstmenuitem,secondmenuitem, thirdmenuitem, fourthmenuitem);
        secondmenuitem.getItems().addAll(firstsubmenu,secondsubmenu);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getMenuItems();
    }    
    
}
