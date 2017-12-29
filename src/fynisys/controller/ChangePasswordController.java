/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller;

import fynisys.constants.Constants;
import static fynisys.controller.ForgetPasswordController.JSON;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class ChangePasswordController implements Initializable {
private FynisysManager fynmanager;
@FXML private Button changepassword; 
@FXML private TextField oldPassword;
@FXML private TextField newPassword;
@FXML private TextField confirmPassword;
@FXML private Label invalidEntry;
@FXML private Label dontmatch;
@FXML Button showpassword;
@FXML Label displaypassword;
@FXML PasswordField passwordText;
String UserName= null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML public void showPassword(){
        displaypassword.setText(passwordText.getText());
        displaypassword.setVisible(true);
        
        //System.out.println("Username - "+passwordText.getText());
        //System.out.println("username - "+username.getText());
        
    }
    
    @FXML public void hidePassword(){
        displaypassword.setVisible(false);
    }
   
    
@FXML public void ChangePassword(final FynisysManager fm, String sessionId ){
     fynmanager = fm;
     changepassword.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) {   
          System.out.println("Check match - "+confirmPassword.getText().equals(newPassword.getText()));
         if(confirmPassword.getText().equals(newPassword.getText())){
          try {
             //System.out.println("Username:- "+UserName);
             JSONObject obj = new JSONObject();
             Response response= null;
             obj.put("username", sessionId);
             obj.put("oldpassword", oldPassword.getText());
             obj.put("newpassword", newPassword.getText());
             System.out.println("Object :---" +obj.toString());
             
             OkHttpClient client = new OkHttpClient.Builder().build();
             
             RequestBody body;
             body = RequestBody.create(JSON, obj.toString());
             
             Request request = new Request.Builder()
                     .url(Constants.URL+"resetpassword")
                     .addHeader("Content-Type", "application/json")
                     .post(body)
                     .build();
             
             response = client.newCall(request).execute();
             
             System.out.println(response.body().string());
             System.err.println(response);
             if(response.toString().contains("code=200")){
                 fynmanager.landingScreen();}else{
                 System.out.println("Unable to change password!!");
                 invalidEntry.setVisible(true);
                   dontmatch.setVisible(false);
             }
             
             
             
             //fynmanager.showlandingScreen();
         } catch (IOException ex) {
             Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
         }
         }else{
              invalidEntry.setVisible(false);
             dontmatch.setVisible(true);
         }
         
     }});
}
}
