/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.footer;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class FooterController implements Initializable {
    
    @FXML Label date;
    @FXML Label copyRight;
private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    
    public void DateTime(){
        LocalDate localDate = LocalDate.now();
        System.out.println(DateTimeFormatter.ofPattern("yyyy").format(localDate));
        copyRight.setText(" "+DateTimeFormatter.ofPattern("yyyy").format(localDate)+"Ciobera.com, All right reserved");
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy");
	//Setting the time zone
	dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
	System.out.println(dateTimeInGMT.format(new Date()));
        date.setText("Date-"+dateTimeInGMT.format(new Date()));

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DateTime();
    }    
    
}
