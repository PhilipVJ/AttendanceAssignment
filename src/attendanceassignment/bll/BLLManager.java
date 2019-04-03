/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.bll;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Student;
import attendanceassignment.be.StudentNotification;
import attendanceassignment.be.User;
import attendanceassignment.be.Teacher;
import attendanceassignment.dal.AttendanceDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Anders
 */
public class BLLManager
{

    AttendanceDAO aDAO;

    public BLLManager(AttendanceDAO aDAO) throws IOException
    {
        this.aDAO = aDAO;
    }

    public boolean setAttendance(Attendance attendance) throws ParseException, SQLException
    {

        return aDAO.setAttendance(attendance);

    }

    public User login(String username, String password) throws SQLException, IOException
    {

        return aDAO.userLogin(username, password);

    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException
    {
        return aDAO.getAllTeachers();

    }

    public ArrayList<Date> getAbsentDays(int id) throws SQLException
    {
        return aDAO.getAbsentDays(id);
    }

    public boolean requestAttendanceChange(int student, int teacher, Date date) throws SQLException
    {
        return aDAO.requestAttendanceChange(student, teacher, date);
    }

    public boolean checkForRequestedDay(int studentId, Date date) throws SQLException
    {
        return aDAO.checkForRequestedDay(studentId, date);
    }

    public ArrayList<Date> getRequestedDays(int id) throws SQLException
    {
        return aDAO.getAllRequestsByStudent(id);
    }

    public ArrayList<Date> getAllNonRequestedAbsentDays(int id) throws SQLException
    {
        return aDAO.getAllNonRequestedAbsentDays(id);
    }

    public void addAttendance(Attendance att) throws ParseException, SQLException
    {
        aDAO.setAttendance(att);
    }

    public ArrayList<Student> getClassStudents(String className) throws SQLException
    {
        return aDAO.getClassStudents(className);
    }

    public boolean checkForAttendance(int studentId, Date date) throws SQLException
    {
        return aDAO.checkForAttendance(studentId, date);
    }

    public ArrayList<Date> getAllSchoolDays() throws SQLException
    {

        return aDAO.allSchoolDays();

    }

    public ArrayList<StudentNotification> getTeacherNotifications(int teacherId) throws SQLException, SQLServerException, ParseException
    {

        return aDAO.getTeacherNotifications(teacherId);

    }

    public void deleteNotificationRequests(int studentID, Date absentDay) throws SQLException
    {
        aDAO.deleteNotificationRequests(studentID, absentDay);
    }

    public void acceptAttendance(Attendance att) throws SQLException
    {
        aDAO.acceptAttendance(att);
    }
}
