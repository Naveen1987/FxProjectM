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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class OtpController implements Initializable {
    private FynisysManager fynmanager;
    @FXML Button otpsubmit;
//@FXML TextField EnterUsername;
    @FXML TextField enterotp;
    @FXML Label incorrectOtp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    @FXML public void EnterOtp(final FynisysManager fm, String SessionId){
        fynmanager = fm;
        otpsubmit.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) {
            
            JSONObject obj = new JSONObject();
            Response response= null;
            obj.put("username", SessionId);
            obj.put("otp", enterotp.getText());
            System.out.println("Object :---" +obj.toString());
            
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            
            RequestBody body;
            body = RequestBody.create(JSON, obj.toString());
            
            Request request = new Request.Builder()
                    .url(Constants.URL+"sendtemppass")
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();
            try {
                response = client.newCall(request).execute();
            } catch (IOException ex) {
                Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(response.body().toString());
            System.err.println(response);
            if(response.toString().contains("code=200")){
                fynmanager.tempPassLoginScreen();}else{
                System.out.println("OTP not valid!!");
                incorrectOtp.setVisible(true);
                
            }
            
            
        }});}
    
}
