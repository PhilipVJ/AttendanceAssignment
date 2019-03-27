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
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nikla
 */
public class AbscensData
{

    DbConnection dbc;

    public AbscensData() throws IOException
    {
        dbc = new DbConnection();

    }

    public boolean setAttendance(Attendance attendance) throws ParseException, SQLServerException, SQLException
    {
        boolean success = false;
        String sql = "INSERT INTO Attendance VALUES (?,?);";
        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            java.sql.Date sqlDate = new java.sql.Date(attendance.getDate().getTime());
            pst.setInt(1, attendance.getId());
            pst.setDate(2, sqlDate);
            pst.execute();
            success = true;
        }

        return success;

    }

    public boolean requestAttendanceChange(int student, int teacher, Date toChange) throws SQLServerException, SQLException
    {

        String sql = "INSERT INTO AttendanceChange VALUES (?,?,?);";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            java.sql.Date sqlDate = new java.sql.Date(toChange.getTime());
            pst.setInt(1, student);
            pst.setInt(2, teacher);
            pst.setDate(3, sqlDate);
            pst.execute();
            return true;
        }

    }

    public ArrayList<Date> getAbsentDays(int studentID) throws SQLServerException, SQLException
    {

        ArrayList<Date> absentDays = new ArrayList<>();

        String sql = "Select Date from Dates where Date NOT IN ( Select Date from Attendance WHERE StudentID=(?)) AND Date <= (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            Date toDay = new Date();
            java.sql.Date sqlDate = new java.sql.Date(toDay.getTime());
            pst.setInt(1, studentID);
            pst.setDate(2, sqlDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {

                Date date = rs.getDate("Date");
                absentDays.add(date);
            }

        }
        return absentDays;
    }

    /**
     * Checks if the given date already has a change request
     *
     * @param studentId
     * @param toCheck
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    public boolean checkForRequestedDay(int studentId, Date toCheck) throws SQLServerException, SQLException
    {

        String sql = "Select * from AttendanceChange where studentID = (?) AND date = (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            java.sql.Date sqlDate = new java.sql.Date(toCheck.getTime());
            pst.setInt(1, studentId);
            pst.setDate(2, sqlDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {

                return true;
            }
        }
        return false;
    }

    public ArrayList<Date> getAllRequestsByStudent(int studentID) throws SQLServerException, SQLException
    {

        ArrayList<Date> requestedDays = new ArrayList<>();

        String sql = "Select * from AttendanceChange where studentID = (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            pst.setInt(1, studentID);

            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {

                Date date = rs.getDate("date");
                requestedDays.add(date);
            }

        }
        return requestedDays;
    }

    public ArrayList<Date> getAllNonRequestedAbsentDays(int studentID) throws SQLServerException, SQLException
    {

        ArrayList<Date> requestedDays = new ArrayList<>();

        String sql = "Select Date from Dates where Date NOT IN ( Select Date from Attendance WHERE StudentID=(?)) AND Date <= (?) AND Date NOT IN(Select date from AttendanceChange WHERE studentID=(?))";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            Date toDay = new Date();
            java.sql.Date sqlDate = new java.sql.Date(toDay.getTime());
            pst.setInt(1, studentID);
            pst.setDate(2, sqlDate);
            pst.setInt(3, studentID);

            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {

                Date date = rs.getDate("date");
                requestedDays.add(date);
            }

        }
        // If the current date is in the list and the clock has not passed 15:00 it should be removed
        for (Date requestedDay : requestedDays)
        {
            String date = requestedDay.toString();
            Date toDay = new Date();
            java.sql.Date sqlDate = new java.sql.Date(toDay.getTime());
            String currentDate = sqlDate.toString();
            int curTime = LocalDateTime.now().getHour();
            System.out.println(""+curTime);
            if(curTime<15 && date.equals(currentDate))
            {
                requestedDays.remove(requestedDay);
                break;
            }


        }

        return requestedDays;
    }

}
