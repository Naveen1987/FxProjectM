///*
//* To change this license header, choose License Headers in Project Properties.
//* To change this template file, choose Tools | Templates
//* and open the template in the editor.
//*/
//package fynisys.views;
//
//import fynisys.controller.LoginController;
//import java.io.IOException;
//import javafx.application.Application;
//import static javafx.application.Application.launch;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.HPos;
//import javafx.geometry.VPos;
//import javafx.scene.Cursor;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.RowConstraints;
//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Font;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//
///**
// *
// * @author daffodil-11
// */
//public class Fynisys extends Application {
//    AnchorPane root;
//    Scene scene;
//    
//    @Override
//    public void start(Stage stage) throws Exception {
//         Scene scene = new Scene(new StackPane());
//        
//        FynisysManager sgpm = new FynisysManager(scene);
//        sgpm.registrationScreen();
//        //scene.setCursor(Cursor.HAND);
//        //stage.setScene(scene);
//        stage.setScene(scene);
//        stage.setTitle("FYNISYS");
//        //stage.setResizable(false);
//        //stage.setFullScreen(true);
//        
//        stage.setMaximized(true);
//        stage.getIcons().add(new Image(LoginController.class.getClassLoader().getResourceAsStream("fynisys/css/logo.png")));
//        //Font.loadFont(SnapGp.class.getResource("HelveticaNeueLTPro-Cn.otf").toExternalForm(), 10);
//        stage.show();
//        //Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
//        
////        final FXMLLoader LOADER = new FXMLLoader();
////        	LOADER.setLocation(getClass().getResource("registration.fxml"));
////        root = (AnchorPane) LOADER.load();
////         scene = new Scene(root);
////
////        stag.show();
//    }
//    
//    
//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    
//}
