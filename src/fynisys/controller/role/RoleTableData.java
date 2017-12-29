/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.controller.role;

/**
 *
 * @author daffodil-11
 */
import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class RoleTableData {
    
    private SimpleStringProperty roleName;
    private SimpleStringProperty roleType;
    private SimpleStringProperty enteredBy;
    private SimpleStringProperty date;
    //private SimpleIntegerProperty roleid;
    
    
    
    public RoleTableData(RoleBean roleBean) {
        this.roleName = new SimpleStringProperty(roleBean.getRolename());
      //  this.roleid = new SimpleIntegerProperty(roleBean.getRoleid());
        this.roleType = new SimpleStringProperty(roleBean.getRoletype());
        this.enteredBy = new SimpleStringProperty(roleBean.getEnteredby());
        this.date = new SimpleStringProperty(roleBean.getDate());
        System.out.println("This is the value" +roleBean.getDate()+" "+roleBean.getEnteredby());
    }

    
	public RoleTableData(final String rolename, final String roletype,  final String enteredby, final String date) {
		this.roleName = new SimpleStringProperty(rolename);
		this.roleType = new SimpleStringProperty(roletype);
		this.enteredBy = new SimpleStringProperty(enteredby);
		this.date = new SimpleStringProperty(date);
		
	}
    
    public String getRoleName() {
        return roleName.get();
    }
    
    public final void setRoleName(final String rolename) {
        this.roleName.set(rolename);
    }
    
    public String getRoleType() {
        return roleType.get();
    }
    
    public final void setRoleType(final String roletype) {
        this.roleType.set(roletype);
    }
    
    public String getEnteredBy() {
        return enteredBy.get();
    }
    
    public final void setEnteredBy(final String enteredby) {
        this.enteredBy.set(enteredby);
    }
    
    public String getDate() {
        return date.get();
    }
    
    public final void setDate(final String date) {
        this.date.set(date);
    }
    
  
    
}
