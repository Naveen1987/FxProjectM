/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.constants;

import fynisys.controller.AddUserController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;

/**
 *
 * @author daffodil-11
 */
public class DateConvertor {
    public static String convert(String convertDate){
        long unixTime = 0;
        try {
            String date1 = convertDate;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date =  dateFormat.parse(date1);
            
            unixTime = (long) date.getTime()/1000;
            System.out.println(unixTime );//<- prints 1352504418
        } catch (ParseException ex) {
            Logger.getLogger(DateConvertor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unixTime+"";
    }
    
    
    public static String unixTenToDateConvertor(String date){
        java.util.Date Unixtime=new java.util.Date(Long.parseLong(date)*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Normaldate = sdf.format(Unixtime);
        return Normaldate;
    }
    
    public static String unixToDateConvertor(String date){
        java.util.Date Unixtime=new java.util.Date(Long.parseLong(date));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Normaldate = sdf.format(Unixtime);
        return Normaldate;
    }
}
