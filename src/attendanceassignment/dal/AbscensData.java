/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
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
    }

    
    
    
    public Attendance setAttendance(String attendance) throws ParseException{
        
            
       
            try (Connection con = dbc.getConnection()){
                String sql = "INSERT INTO Attendance VALUES (?);";

                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                DateFormat format = new SimpleDateFormat("dd/MM-yyyy", Locale.ENGLISH);
                Date date = format.parse(attendance);
                
                pst.setDate(1, (java.sql.Date) date);
               
                
                 if(pst.executeUpdate() == 1);
                {
                ResultSet rs = pst.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                    
                Attendance att = new Attendance(id, attendance);
                return att;
                }
        
            } catch(SQLException e) {
        
             }
         return null;
         
        } 

    
  
    
}
