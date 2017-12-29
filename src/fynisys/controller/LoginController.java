/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.controller;

import fynisys.constants.Constants;
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
import javafx.geometry.Rectangle2D;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import okhttp3.MediaType;
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
public class LoginController implements Initializable {
    
    @FXML Button showpassword;
    @FXML PasswordField passwordText;
    @FXML TextField username;
    @FXML Label displaypassword;
    @FXML private Label incorrect;
    private FynisysManager fynmanager;
    @FXML private Button submit;
    @FXML private Button forgetpass;
    @FXML private Label temporaryPassword;
    
    @FXML public void showPassword(){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println("Screen size -- "+primaryScreenBounds);
        displaypassword.setText(passwordText.getText());
        displaypassword.setVisible(true);
        //System.out.println("Username - "+passwordText.getText());
        //System.out.println("username - "+username.getText());
        //https://stackoverflow.com/questions/22789449/export-stage-into-pdf
        //http://www.dynamicreports.org/
//        PDDocument doc    = new PDDocument();
//            PDPage page = new PDPage();
//            PDImageXObject pdimage;
//            PDPageContentStream content;
//            try {
//                pdimage = PDImageXObject.createFromFile("chart.png",doc);
//                content = new PDPageContentStream(doc, page);
//                content.drawImage(pdimage, 100, 100);
//                content.close();
//                doc.addPage(page);
//                doc.save("pdf_file.pdf");
//                doc.close();
//                file.delete();
//            } catch (IOException ex) {
//                Logger.getLogger(NodeToPdf.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
    }
    
    @FXML public void hidePassword(){
        displaypassword.setVisible(false);
    }
    
    @FXML public void TempPasswordPage(){
        temporaryPassword.setVisible(true);
    }
    
    @FXML public void LoginAction(final FynisysManager fm, String status){
        if(status.equalsIgnoreCase("temp")){
            TempPasswordPage();
        }
        fynmanager = fm;
        forgetpass.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) {
            fynmanager.forgetPasswordScreen();
        }});
        submit.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try {
                    //System.out.println("Username - "+passwordText.getText());
                    //  System.out.println("username - "+username.getText());
                    OkHttpClient client = new OkHttpClient();
                    Response response= null;
                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("username", username.getText()).addFormDataPart("password", passwordText.getText()).build();
                    
                    Request request = new Request.Builder()
                            .url(Constants.URL+"login")
                            .method("POST", RequestBody.create(null, new byte[0]))
                            .post(requestBody)
                            .build();
                    try {
                        response = client.newCall(request).execute();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String resbody = response.body().string();
                    System.out.println("Response Body:-  "+resbody);
                    System.out.println(response.toString());
                    if(resbody.contains("reset password")){
                        incorrect.setVisible(false);
                        //fynmanager.showlandingScreen();
                        fynmanager.changePassword(username.getText());
                    }else if(resbody.contains("Successfully login")){
                        incorrect.setVisible(false);
                        fynmanager.showlandingScreen();
                    }
                    else{
                        incorrect.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
