/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.scene.control.skin.VirtualFlow;
        
import fynisys.constants.DateConvertor;
import fynisys.controller.role.AddRoleController;
import static fynisys.controller.role.AddRoleController.data;
import fynisys.controller.role.RoleBean;
import fynisys.controller.role.RoleTableData;
import fynisys.request.service.GetRequestService;
import fynisys.request.service.PostRequestService;
import fynisys.request.service.RequestResponseInterface;
import fynisys.views.AddTabsController;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class AddUserController implements Initializable {
    
    @FXML Accordion FundAccessAccordion;
    @FXML TilePane accordianTitlePan;
    @FXML RadioButton r;
    List<String> fundArray = new ArrayList<>();
    List<String> roleList = new ArrayList<>();
    List<RoleBean> roleDate = new ArrayList<>();
    @FXML TextField displayName;
    @FXML TextField userId;
    @FXML TextField emailId;
    @FXML TextField reutersCode;
    @FXML TextField userPassword;
    @FXML TextField reEnterPassword;
    @FXML TextField passwordExpiryDays;
    @FXML DatePicker passwordValidTillDate;
    @FXML ComboBox Role;
    @FXML DatePicker date;
    @FXML RadioButton activeStatus;
    @FXML RadioButton deactiveStatus;
    AddTabsController tabController;  
    
    
    
    /**
     * Initializes the controller class.
     */
    public static  ObservableList<UserFundsObservableList> data = FXCollections
            .observableArrayList();
    
    
    @FXML public void SaveUser(ActionEvent event){
        AddUserBean userBean = new AddUserBean();
        System.out.println("displayName - " + displayName.getText());
        userBean.setDisplayname(displayName.getText().toString());
        System.out.println("userId - " + userId.getText());
        userBean.setUserid(userId.getText().toString());
        System.out.println("emailId - "+emailId.getText());
        userBean.setEmail(emailId.getText());
        System.out.println("reutersCode - "+reutersCode.getText());
        userBean.setReuter_code(reutersCode.getText().toString());
        System.out.println(" userPassword - "+userPassword.getText());
        userBean.setPassword(userPassword.getText().toString());
        System.out.println("reEnterPassword - "+reEnterPassword.getText());
        System.out.println("passwordExpiryDays - "+passwordExpiryDays.getText());
        userBean.setPass_exp_day(passwordExpiryDays.getText().toString());
        System.out.println("passwordValidTillDate - "+passwordValidTillDate.getValue());
        userBean.setPass_exp_date(DateConvertor.convert(passwordValidTillDate.getValue().toString()));
        System.out.println(" Role - "+Role.getValue());
        System.out.println("Date - "+date.getValue());
        userBean.setDate(DateConvertor.convert(date.getValue().toString()));
        System.out.println("activeStatus - "+activeStatus.isSelected());
        if(deactiveStatus.isSelected()){
            userBean.setStatus("0");
        }else{
            userBean.setStatus("1");
        }
        System.out.println("deactiveStatus - "+deactiveStatus.isSelected());
        
        for(RoleBean rb : roleDate){
            if(rb.getRolename().equalsIgnoreCase(Role.getValue().toString())){
                System.out.println("Role Name - " +rb.getRolename()+"Role Id - "+rb.getRoleid());
                userBean.setRoleid(rb.getRoleid()+"");
            }
            
        }
        
        
        List fundlist = new ArrayList();
        for(int i=0; i<fundArray.size();i++){
            fundlist.add(new AccessFundBean(fundArray.get(i)));
        }
        userBean.setFunds(fundlist);
        
        
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        System.out.println( gson.toJson(userBean));
//        String passwordValidTillDates = DateConvertor.convert(passwordValidTillDate.getValue().toString());
//        String Date = DateConvertor.convert(date.getValue().toString());
System.out.println("Size - "+fundArray.size());

PostRequestService saveUserPostRequest   = new PostRequestService();
saveUserPostRequest.postRequestService(gson.toJson(userBean), "saveuser", saveUserResponseCallback);
    }
    
    
    
    
    private final RequestResponseInterface saveUserResponseCallback = new RequestResponseInterface() {
        @Override
        public void failureResponse(String errorMsg) {
            System.out.println("Error - "+errorMsg);
            
        }
        @Override
        public void successResponse(String response) {
            
            System.out.println("response - "+response);
            
            
        }
    };
    
    private void getfunds(){
        //System.out.println("Hi in the getfund methiod");
        
        try {
            GetRequestService getRequestService = new GetRequestService();
            getRequestService.getRequestService(responseCallback, "getfunds");
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final RequestResponseInterface responseCallback = new RequestResponseInterface() {
        @Override
        public void failureResponse(String errorMsg) {
            System.out.println(""+errorMsg);
            
        }
        @Override
        public void successResponse(String response) {
            
            System.out.println("response - "+response);
            ObjectMapper mapper = new ObjectMapper();
            try {
                populate(Arrays.asList(mapper.readValue(response, UserFundsBean[].class)));
                
                
//            Gson g = new Gson();
//            UserFundsBean userFunds = g.fromJson(response, UserFundsBean.class);

//System.out.println(""+userFunds.getEnteredby());
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
    
     public void setParentController( AddTabsController context){
        tabController=context;
    }
    
    public  void populate(final List<UserFundsBean> userFunds) {
        userFunds.forEach(p -> System.out.println(p.getFundid() +" "+p.getFundname()));
        //System.out.println("Userfunf size - "+role.size());
        userFunds.forEach(p -> data.add(new UserFundsObservableList(p)));
        
        VBox accessFundBox = new VBox(10);
        //accessFundBox.setPadding(new Insets(10));
        accessFundBox.setPadding(new Insets(5,0,0,20));
        accessFundBox.getStylesheets().add("/fynisys/css/adduser.css");
        accessFundBox.getStyleClass().add("accessFundBox");
        TitledPane fundTpane = new TitledPane("User access to fund", accessFundBox);
        fundTpane.setPrefWidth(1290.0);
        fundTpane.setPrefHeight(150.0);
        fundTpane.setTextFill(Color.WHITE);
        
        fundTpane.getStylesheets().add("/fynisys/css/adduser.css");
        FundAccessAccordion.getPanes().add(fundTpane);
        FundAccessAccordion.setExpandedPane(fundTpane);
//accessFundBox.getChildren().setAll(buttons);
//accessFundBox.getChildren().addAll(selectedItem,selectedIndex);
//        ToggleGroup toggleGroups = new ToggleGroup();
for(UserFundsBean funds : userFunds){
//        RadioButton r = new RadioButton(funds.getFundname());
//        r.setId(funds.getFundid());
//        accessFundBox.getChildren().addAll(r);
//r.setToggleGroup(toggleGroups);

final CheckBox cb = new CheckBox(funds.getFundname());
cb.setId(funds.getFundid());
final Tooltip tooltip = new Tooltip("$ tooltip");
tooltip.setFont(new Font("Arial", 16));
cb.setTooltip(tooltip);
accessFundBox.getChildren().addAll(cb);
cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
    public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
        String fundId = cb.getId();
        System.out.println(fundId);
        fundArray.add(fundId);
    }
});
}

//toggleGroups.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));
    }
    
    
    private final RequestResponseInterface roleResponseCallback = new RequestResponseInterface() {
        @Override
        public void failureResponse(String errorMsg) {
            System.out.println(""+errorMsg);
            
        }
        @Override
        public void successResponse(String response) {
            
            System.out.println("response Role - "+response);
            ObjectMapper mapper = new ObjectMapper();
            try {
                setRoles(Arrays.asList(mapper.readValue(response, RoleBean[].class)));
                
//
//
////            Gson g = new Gson();
////            UserFundsBean userFunds = g.fromJson(response, UserFundsBean.class);
//
////System.out.println(""+userFunds.getEnteredby());
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
    
    
    public  void setRoles( List<RoleBean> Roles) {
        roleDate = Roles;
        roleList = new ArrayList<String>();
        for(RoleBean rb : Roles){
            roleList.add(rb.getRolename());
        }
        Role.getItems().setAll(roleList);
        //System.out.println("Userfunf size - "+role.size());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getfunds();
        
        GetRequestService getRole = new GetRequestService();
        try {
            getRole.getRequestService(roleResponseCallback, "roles");
            
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
