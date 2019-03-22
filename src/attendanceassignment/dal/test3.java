/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Anders
 */
public class test3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException, SQLException {
       Attendance att = new Attendance(2,new Date());
         
        AbscensData abs = new AbscensData();
        abs.setAttendance(att);
    }
    
}
