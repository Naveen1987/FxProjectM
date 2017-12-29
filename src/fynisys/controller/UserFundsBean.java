/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.controller;

import java.util.Calendar;

/**
 *
 * @author daffodil-11
 */
public class UserFundsBean {
    private String fundid;
    private String funddetail;
    private String fundname;
    private String enteredby;
    private String createdon;
    
    public String getFundid() {
        return fundid;
    }
    
    public void setFundid(String fundid) {
        this.fundid = fundid;
    }
    
    public String getFunddetail() {
        return funddetail;
    }
    
    public void setFunddetail(String funddetail) {
        this.funddetail = funddetail;
    }
    
    public String getFundname() {
        return fundname;
    }
    
    public void setFundname(String fundname) {
        this.fundname = fundname;
    }
    
    public String getEnteredby() {
        return enteredby;
    }
    
    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }
    
  
    
}
