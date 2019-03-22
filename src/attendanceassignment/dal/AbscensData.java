/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Teacher;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikla
 */
public class AbscensData {

    DbConnection dbc;

    public AbscensData() throws IOException {
        dbc = new DbConnection();
        System.out.println("dbc" + dbc);
    }

    public boolean setAttendance(Attendance attendance) throws ParseException, SQLServerException, SQLException {
        boolean success = false;
        System.out.println("første");

        Connection con = dbc.getConnection();
        String sql = "INSERT INTO Attendance VALUES (?,?);";

        PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        java.sql.Date sqlDate = new java.sql.Date(attendance.getDate().getTime());
        System.out.println("" + sqlDate.toString());
        pst.setInt(1, attendance.getId());
        pst.setDate(2, sqlDate);

        pst.execute();
        success = true;
        System.out.println("true");

        return success;

    }

    public boolean requestAttendanceChange(int student, int teacher, Date toChange) {

        boolean success = false;
        String sql = "INSERT INTO AttendanceChange VALUES (?,?,?);";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

            java.sql.Date sqlDate = new java.sql.Date(toChange.getTime());
            pst.setInt(1, student);
            pst.setInt(2, teacher);
            pst.setDate(3, sqlDate);
            pst.execute();
            success = true;

            return success;
        } catch (SQLServerException ex) {
            Logger.getLogger(AbscensData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AbscensData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    public ArrayList<Date> getAbsentDays(int studentID)
    {
        
        ArrayList<Date> absentDays = new ArrayList<>();
        
        String sql = "Select Date from Dates where Date NOT IN ( Select Date from Attendance WHERE StudentID=(?)) AND Date <= (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

            Date toDay = new Date();
            java.sql.Date sqlDate = new java.sql.Date(toDay.getTime());
            pst.setInt(1, studentID);
            pst.setDate(2, sqlDate);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                Date date = rs.getDate("Date");
                absentDays.add(date);
            }
            
           
        } catch (SQLServerException ex) {
            Logger.getLogger(AbscensData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AbscensData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return absentDays;
        
    }
    
    
}
