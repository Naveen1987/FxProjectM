/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.user;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author daffodil-11
 */
public class UserTableData {
    SimpleStringProperty displayname;
    SimpleStringProperty userid;
    SimpleStringProperty role;
    SimpleStringProperty enteredby;
    SimpleStringProperty date;
    SimpleStringProperty email;
    SimpleStringProperty isactive;
    SimpleStringProperty user_uuid;
    
    public  UserTableData(UserBean userBean){
        this.displayname = new SimpleStringProperty(userBean.getDisplayname());
        this.userid= new SimpleStringProperty(userBean.getUserid());
        this.role = new SimpleStringProperty(userBean.getRolename());
        this.enteredby = new SimpleStringProperty(userBean.getEnteredby());
        this.date = new SimpleStringProperty(userBean.getDate());
        this.email = new SimpleStringProperty(userBean.getEmail());
        this.isactive = new SimpleStringProperty(userBean.getIsactive());
                
    }

    public String  getDisplayname() {
        return displayname.get();
    }

    public void setDisplayname(String displayname) {
        this.displayname.set(displayname);
    }

    public String getUserid() {
        return userid.get();
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getEnteredby() {
        return enteredby.get();
    }

    public void setEnteredby(String enteredby) {
        this.enteredby.set(enteredby);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getIsactive() {
        return isactive.get();
    }

    public void setIsactive(String isactive) {
        this.isactive.set(isactive);
    }
    
    
}
