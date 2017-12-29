/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller;

/**
 *
 * @author daffodil-11
 */
public class AccessFundBean {
 private String fundid;

 
 public AccessFundBean(String accessFundId){
     fundid = accessFundId;
 }
    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }

    
 }
