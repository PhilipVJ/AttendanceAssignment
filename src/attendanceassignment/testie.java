/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment;

import attendanceassignment.dal.AbscensData;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Anders
 */
public class testie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        
        AbscensData a = new AbscensData();
        Date toDay = new Date();
        java.sql.Date sqlDate = new java.sql.Date(toDay.getTime());
        boolean b = a.checkForAttendance(2, sqlDate);

        if(b = true){
            System.out.println("true");
        }
         else{
             System.out.println("false");
         }   
            
    }
    
}
