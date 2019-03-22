/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author nikla
 */
public class AbscensData {
    DbConnection dbc;

    public AbscensData() throws IOException {
        dbc = new DbConnection();
        System.out.println("dbc"+ dbc);
    }

    
    
    
    public boolean setAttendance(Attendance attendance) throws ParseException, SQLServerException, SQLException{
        boolean success = false;
            System.out.println("første");
       
            Connection con = dbc.getConnection();
                String sql = "INSERT INTO Attendance VALUES (?,?);";

                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                java.sql.Date sqlDate = new java.sql.Date(attendance.getDate().getTime());
                System.out.println(""+ sqlDate.toString());
                pst.setInt(1, attendance.getId());
                pst.setDate(2, sqlDate);
               
                
                pst.execute();
                success = true;
                System.out.println("true");
            
         return success;
         
        } 

    
  
    
}
