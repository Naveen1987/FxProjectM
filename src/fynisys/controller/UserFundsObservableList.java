/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller;

import java.util.Calendar;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author daffodil-11
 */
public class UserFundsObservableList {
    
     private SimpleStringProperty fundid;
    private SimpleStringProperty funddetail;
    private SimpleStringProperty fundname;
    private SimpleStringProperty enteredby;
    private SimpleStringProperty createdon;
    
    public UserFundsObservableList(UserFundsBean userFundBean) {
        this.fundid = new SimpleStringProperty(userFundBean.getFundid());
      //  this.roleid = new SimpleIntegerProperty(roleBean.getRoleid());
        this.funddetail = new SimpleStringProperty(userFundBean.getFunddetail());
        this.enteredby = new SimpleStringProperty(userFundBean.getEnteredby());
        this.createdon = new SimpleStringProperty(userFundBean.getCreatedon());
        
    }

    public String getFundid() {
        return fundid.get();
    }

    public void setFundid(String fundid) {
        this.fundid.set(fundid);
    }

    public String getFunddetail() {
        return funddetail.get();
    }

    public void setFunddetail(String funddetail) {
        this.funddetail.set(funddetail);
    }

    public String getFundname() {
        return fundname.get();
    }

    public void setFundname(String fundname) {
        this.fundname.set(fundname);
    }

    public String getEnteredby() {
        return enteredby.get();
    }

    public void setEnteredby(String enteredby) {
        this.enteredby.set(enteredby);
    }

    public String getCreatedon() {
        return createdon.get();
    }

    public void setCreatedon(String  createdon) {
        this.createdon.set(createdon);
    }
    
    
    
}

