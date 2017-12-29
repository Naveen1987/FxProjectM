package fynisys.views;

import fynisys.controller.ChangePasswordController;
import fynisys.controller.ForgetPasswordController;
import fynisys.controller.LandingController;
import fynisys.controller.LoginController;
import fynisys.controller.OtpController;
import fynisys.controller.header.HeaderController;
import fynisys.controller.role.AddRoleController;
import java.io.IOException;
import java.util.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

/** Manages control flow for logins */
public class FynisysManager {
    //static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SnapGpManager.class.getName());
    
    public Scene scene;
    
    public FynisysManager(Scene scene) {
        this.scene = scene;
    }
    
    public FynisysManager(){
        
    }
    /**
     * Callback method invoked to notify that a user has been authenticated.
     * Will show the main application screen.
     */
    public void showlandingScreen(){
        landingScreen();
    }
    /**
     * Callback method invoked to notify that a user has logged out of the main application.
     * Will show the login application screen.
     */
//  public void logout() {
//    showLoginScreen();
//  }
    
    public void registrationScreen()  {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TryLoginfxml.fxml")
        );
       // loader.setRoot(this);
        try {
            scene.setRoot((Parent) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(FynisysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginController controller =
                loader.<LoginController>getController();
        controller.LoginAction(this, "No Temporary Password");

//HeaderController controller =
//        loader.<HeaderController>getController();
//controller.HeaderAction(this);

//        AddRoleController controller =
//        loader.<AddRoleController>getController();
//controller.AddRoleAction(this);

//AddTabsController controller =
//        loader.<AddTabsController>getController();
//controller.AddTabsAction(this ,"fyn");

    }
    
    public void landingScreen()  {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("AddTabs.fxml")
        );
        try {
            scene.setRoot((Parent) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(FynisysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddTabsController controller =
        loader.<AddTabsController>getController();
controller.AddTabsAction(this ,"fyn");

    }
    
    public void tempPassLoginScreen()  {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TryLoginfxml.fxml")
        );
        try {
            scene.setRoot((Parent) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(FynisysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginController controller =
                loader.<LoginController>getController();
        controller.LoginAction(this, "temp");
    }
    
    public void showenterOtp(String SessionId)  {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("otp.fxml")
        );
        try {
            scene.setRoot((Parent) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(FynisysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        OtpController controller =
                loader.<OtpController>getController();
        controller.EnterOtp(this,SessionId);
    }
    
    public void changePassword(String sessionId)  {
        System.out.println("SessionId=======>>> "+sessionId);
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("ChangePassword.fxml")
        );
        try {
            scene.setRoot((Parent) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(FynisysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangePasswordController controller =
                loader.<ChangePasswordController>getController();
        controller.ChangePassword(this, sessionId);
    }
    
    public void forgetPasswordScreen()  {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("forgetPassword.fxml")
        );
        try {
            scene.setRoot((Parent) loader.load());
            ForgetPasswordController controller =
                    loader.<ForgetPasswordController>getController();
            controller.BackToLogin(this);
        } catch (IOException ex) {
            Logger.getLogger(FynisysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void showSettingView() {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("setting.fxml")
//            );
//            scene.setRoot((Parent) loader.load());
//            SettingController controller =
//                    loader.<SettingController>getController();
//            //controller.initSessionID(this, sessionID);
//        } catch (IOException ex) {
//            //Logger.getLogger(SnapGpManager.class.getName()).log(Level.SEVERE, null, ex);
//            logger.error(ex);
//        }
//    }
}