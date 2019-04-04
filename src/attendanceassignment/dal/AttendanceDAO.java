/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Student;
import attendanceassignment.be.StudentNotification;
import attendanceassignment.be.Teacher;
import attendanceassignment.be.User;
import attendanceassignment.gui.AttModel.Utility;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nikla
 */
public class AttendanceDAO implements IDatabase
{

    DbConnection dbc;

    /**
     * Constructor of the AttendanceDao
     * @param con
     * @throws IOException 
     */
    public AttendanceDAO(DbConnection con) throws IOException
    {
        this.dbc = con;

    }

    /**
     * Gets all the Teacher from the Sql server and puts in into a Arraylist
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public ArrayList<Teacher> getAllTeachers() throws SQLServerException, SQLException
    {

        ArrayList<Teacher> allTeachers = new ArrayList<>();
        String sql = "SELECT * FROM Person WHERE pType='Teacher'";
        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                String firstName = rs.getString("firstname");

                String lastname = rs.getString("lastname");

                int id = rs.getInt("personID");

                Teacher toAdd = new Teacher(firstName, lastname, id);
                toAdd.addClasses(getClasses(id));

                allTeachers.add(toAdd);

            }
            return allTeachers;
        }
    }

    /**
     * Gets all the Classes from the Sql server and put it into a ArrayList
     * @param id
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public ArrayList<String> getClasses(int id) throws SQLServerException, SQLException
    {
        ArrayList<String> allClasses = new ArrayList<>();
        String sql = "SELECT * FROM PersonClass INNER JOIN Class ON PersonClass.classID = Class.classID WHERE personID=(?);";
        ResultSet rs = null;
        Connection con = dbc.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        rs = pst.executeQuery();
        while (rs.next())
        {
            String className = rs.getString("classname");
            allClasses.add(className);

        }
        return allClasses;

    }

    /**
     * Setting the Attendance date into the sql server when a student hit the "im here" button
     * @param attendance
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public boolean setAttendance(Attendance attendance) throws SQLServerException, SQLException
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

    /**
     * Set the day a student want a attendance change if they have been in school but forgot to
     * set them as present in the sql server
     * @param student
     * @param teacher
     * @param toChange
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
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

    /**
     * Gets all the absentdays from the sqlserver where a student have not been in school
     * @param studentID
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
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
     * Gets all the schoolDays from the sqlserver and put them into a arrayList
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public ArrayList<Date> allSchoolDays() throws SQLServerException, SQLException
    {
        ArrayList<Date> presentDays = new ArrayList<>();
        Date date = new Date();

        String sql = "SELECT * FROM Dates WHERE date <= (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pst.setDate(1, sqlDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                Date dater = rs.getDate("Date");
                presentDays.add(dater);
            }
        }
        return presentDays;
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
    @Override
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

    /**
     * Checking if the student allready have set there present in the sql server
     * @param studentId
     * @param toCheck
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public boolean checkForAttendance(int studentId, Date toCheck) throws SQLServerException, SQLException
    {

        String sql = "SELECT * FROM Attendance WHERE studentID = (?) AND date = (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            java.sql.Date sqlDate = new java.sql.Date(toCheck.getTime());
            pst.setInt(1, studentId);
            pst.setDate(2, sqlDate);
            ResultSet rs = pst.executeQuery();

            //checks if value exists
            if (rs.next())
            {
                return true;
            } else
            {
                return false;
            }

        }

    }

    /**
     * Get all the requested days from a student from the sql server
     * @param studentID
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
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

    /**
     * Get and check if the student have set presence later on the day befor 15:00 and remove the not been there to been there.
     * @param studentID
     * @return
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
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

            if (curTime < 15 && date.equals(currentDate))
            {
                requestedDays.remove(requestedDay);
                break;
            }

        }

        return requestedDays;
    }

    /**
     * Get the student of a class from the SQL and put it into a ArrayList
     * @param className
     * @return Henter students der tilhører en bestemt klasse.
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public ArrayList<Student> getClassStudents(String className) throws SQLServerException, SQLException
    {
        ArrayList<Student> classStudents = new ArrayList<>();

        String sql = "SELECT * FROM PersonClass \n"
                + "INNER JOIN Class ON PersonClass.classID = Class.classID \n"
                + "INNER JOIN Person ON PersonClass.personID = Person.personID\n"
                + "WHERE pType='Student' AND classname = (?);";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            pst.setString(1, className);

            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int id = rs.getInt("personID");
                Student toAdd = new Student(firstName, lastName, className, Utility.calculateAbsencePercentage(allSchoolDays(), getAbsentDays(id)), id);
                classStudents.add(toAdd);
            }
            return classStudents;
        }

    }

    /**
     * Get the Student notifications from a student from the sql and put it into a arraylist so the teacher can see it
     * @param teacherID
     * @return Henter elevers fraværs notifikationer til den individuelle lære
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public ArrayList<StudentNotification> getTeacherNotifications(int teacherID) throws SQLServerException, SQLException
    {

        ArrayList<StudentNotification> notifications = new ArrayList<>();

        String sql = "SELECT * FROM AttendanceChange \n"
                + "INNER JOIN Person ON AttendanceChange.studentID = Person.personID\n"
                + "INNER JOIN PersonClass ON AttendanceChange.studentID = PersonClass.personID\n"
                + "INNER JOIN Class ON PersonClass.classID = Class.classID\n"
                + "WHERE teacherID = (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {
            pst.setInt(1, teacherID);

            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {

                String firstName = rs.getNString("firstname");
                String lastName = rs.getNString("lastname");
                String className = rs.getNString("classname");
                Date absentDay = rs.getDate("date");
                int id = rs.getInt("personID");

                StudentNotification toAdd = new StudentNotification(firstName, lastName, className, absentDay, id);
                notifications.add(toAdd);
            }
        }
        return notifications;
    }

    /**
     * Delete the day where a student have not been present in the sql
     * @param studentID
     * @param date
     * @throws SQLServerException
     * @throws SQLException 
     */
    @Override
    public void deleteNotificationRequests(int studentID, Date date) throws SQLServerException, SQLException
    {

        String sql = "delete from AttendanceChange where studentID =(?) and date = (?)";

        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pst.setInt(1, studentID);
            pst.setDate(2, sqlDate);
            pst.execute();

        }

    }

    /**
     * Teacher Accept the request from a student in the sql  
     * @param att
     * @throws SQLException 
     * Sætter eleven som tilstedeværende og sletter fraværs notifikationen
     */
    @Override
    public void acceptAttendance(Attendance att) throws SQLException 
    {
        Connection con = null;

        try
        {
            con = dbc.getConnection();
            con.setAutoCommit(false);
            //Insert attendence
            String sql = "INSERT INTO Attendance VALUES (?,?);";
            PreparedStatement insert = con.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(att.getDate().getTime());
            insert.setInt(1, att.getId());
            insert.setDate(2, sqlDate);
            insert.executeUpdate();
            // Delete the request
            String sqltwo = "delete from AttendanceChange where studentID =(?) and date = (?)";
            PreparedStatement delete = con.prepareStatement(sqltwo);
            delete.setInt(1, att.getId());
            delete.setDate(2, sqlDate);
            delete.executeUpdate();
            // Execute
            con.commit();

        } catch (SQLException ex)
        {
            if (con != null)
            {                
                con.rollback();
            }
        } finally
        {
            if (con != null)
            {
                con.setAutoCommit(true); 
                con.close(); 
            }
        }
    }

    @Override
    public User userLogin(String username, String password) throws SQLException
    {

        User user = null;
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        ResultSet rs = null;
        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("personID");
                user = getUser(id);
            }
        }
        return user;
    }

    private User getUser(int id) throws SQLServerException, SQLException
    {
        User user = null;
        String sql = "SELECT * FROM Person WHERE personID = ?";
        ResultSet rs = null;
        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);)
        {

            pst.setInt(1, id);

            rs = pst.executeQuery();
            if (rs.next())
            {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String type = rs.getString("pType");
                user = new User(id, firstName, lastName, type);
                ArrayList<String> classes = getClasses(id);
                for (String schoolClass : classes)
                {
                    user.addClass(schoolClass);
                }
            }
        }

        return user;

    }
    


}
