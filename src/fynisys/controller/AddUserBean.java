/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller;

import java.util.List;

/**
 *
 * @author daffodil-11
 */
public class AddUserBean {
   
String displayname;
String userid;
String email;
String reuter_code;
String password;
String pass_exp_day;
String pass_exp_date;
String roleid;
String date;
String status;
String createdby;
    List funds;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReuter_code() {
        return reuter_code;
    }

    public void setReuter_code(String reuter_code) {
        this.reuter_code = reuter_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPass_exp_day() {
        return pass_exp_day;
    }

    public void setPass_exp_day(String pass_exp_day) {
        this.pass_exp_day = pass_exp_day;
    }

    public String getPass_exp_date() {
        return pass_exp_date;
    }

    public void setPass_exp_date(String pass_exp_date) {
        this.pass_exp_date = pass_exp_date;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public List getFunds() {
        return funds;
    }

    public void setFunds(List funds) {
        this.funds = funds;
    }

    
    
}