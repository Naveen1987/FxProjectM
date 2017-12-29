/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.user;

import fynisys.constants.DateConvertor;
import java.text.SimpleDateFormat;

/**
 *
 * @author daffodil-11
 */
public class UserBean {
    String displayname;
    String userid;
    String roleid;
    String enteredby;
    String date;
    String email;
    String isactive;
    String user_uuid;
    String rolename;
    String enteredbyusername;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getEnteredbyusername() {
        return enteredbyusername;
    }

    public void setEnteredbyusername(String enteredbyusername) {
        this.enteredbyusername = enteredbyusername;
    }
    
    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

   

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public String getDate() {
        return DateConvertor.unixTenToDateConvertor(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
    
}
