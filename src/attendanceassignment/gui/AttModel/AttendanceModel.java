/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Student;
import attendanceassignment.be.StudentNotification;
import attendanceassignment.be.Teacher;
import attendanceassignment.be.User;
import attendanceassignment.bll.BLLManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Anders
 */
public class AttendanceModel {

    private final BLLManager bllMan;

    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private ArrayList<StudentNotification> notifications;
    private User user;

    public AttendanceModel() throws IOException {
        bllMan = new BLLManager();
    }

    /**
     * calls barchart data from bll applies attendance to weekdays
     */
    public boolean setAttendance(Attendance attendance) throws ParseException, SQLException {

        return bllMan.setAttendance(attendance);

    }

    public User login(String username, String password) throws SQLException, IOException {
        user = bllMan.login(username, password);
        return user;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        return bllMan.getAllTeachers();
    }

    public ArrayList<Date> getAbsentDays(int id) throws SQLException {
        return bllMan.getAbsentDays(id);
    }

    public boolean requestAttendanceChange(int teacher, Date date) throws SQLException {
        return bllMan.requestAttendanceChange(user.getId(), teacher, date);
    }

    public boolean checkForRequestedDay(Date date) throws SQLException {
        return bllMan.checkForRequestedDay(user.getId(), date);
    }

    public ArrayList<Date> getRequestedDays() throws SQLException {
        return bllMan.getRequestedDays(user.getId());
    }

    public ArrayList<Date> getAllNonRequestedAbsentDays() throws SQLException {
        return bllMan.getAllNonRequestedAbsentDays(user.getId());
    }

    public void addAttendance(Attendance att) throws ParseException, SQLException {
        bllMan.addAttendance(att);
    }




    public ArrayList<Student> getClassStudents(String className) throws SQLException {
        return bllMan.getClassStudents(className);
    }

    public boolean checkForAttendance(Date date) throws SQLException {
       return bllMan.checkForAttendance(user.getId(),date);
    }

    public ArrayList<Date> getAllSchoolDays() throws SQLException
    {
        return bllMan.getAllSchoolDays();
    }
    
    public void loadTeacherNotifications() throws SQLException, SQLServerException, ParseException {
        notifications = bllMan.getTeacherNotifications(user.getId());
        
        
    }

    public void deleteNotificationRequests(int studentID, Date absentDay) throws SQLException {
        bllMan.deleteNotificationRequests(studentID,absentDay);
    }
    public int getNumberOfNotifications() throws SQLException, SQLServerException, ParseException {
        return notifications.size();
    }
    
    public ArrayList<StudentNotification> getNotifications(){
        return notifications;
    }

}
