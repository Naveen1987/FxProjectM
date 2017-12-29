/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.role;

import fynisys.constants.DateConvertor;

/**
 *
 * @author daffodil-11
 */
public class RoleBean {
private String rolename;
private String roletype;
private String enteredby;
private String date;
private int roleid;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(final String rolename) {
        this.rolename = rolename;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype( final String roletype) {
        this.roletype = roletype;
    }

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(final String enteredby) {
        this.enteredby = enteredby;
    }

    public String getDate() {
        return DateConvertor.unixToDateConvertor(date);
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(final int roleid) {
        this.roleid = roleid;
    }


}
