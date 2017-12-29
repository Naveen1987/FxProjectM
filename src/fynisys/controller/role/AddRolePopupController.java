/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.role;

import fynisys.request.service.PostRequestService;
import fynisys.request.service.RequestResponseInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class AddRolePopupController implements Initializable {
@FXML TextField roleName;
    /**
     * Initializes the controller class.
     */
    @FXML public void saveRole(){
        System.out.println("Role Name - "+roleName.getText());
        
        String json = "{\n" +
"\"rolename\":\""+roleName.getText()+"\",\n" +
"\"userid\":\"fyn\"\n" +
"}";
        PostRequestService request = new PostRequestService();
        request.postRequestService(json, "saverole", responseCallback);
        AddRoleController.dialog.close();
        
    }
    
    private final RequestResponseInterface responseCallback = new RequestResponseInterface() {
    @Override
    public void failureResponse(String errorMsg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        
    }

    @Override
    public void successResponse(String response) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try{
         System.out.println("response"+response);
        AddRoleController roleReload = new AddRoleController();
        roleReload.populate(roleReload.retreiveDate());
        
     }catch(Exception e){
         e.printStackTrace();
     }
     
    }
            
            };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
