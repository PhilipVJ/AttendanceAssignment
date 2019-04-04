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
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Philip
 */
public interface IDatabase
{

    /**
     * Teacher Accept the request from a student in the sql
     * @param att
     * @throws SQLException
     */
    void acceptAttendance(Attendance att) throws SQLException;

    /**
     * Gets all the schoolDays from the sqlserver and put them into a arrayList
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<Date> allSchoolDays() throws SQLServerException, SQLException;

    /**
     * Checking if the student allready have set there present in the sql server
     * @param studentId
     * @param toCheck
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    boolean checkForAttendance(int studentId, Date toCheck) throws SQLServerException, SQLException;

    /**
     * Checks if the given date already has a change request
     *
     * @param studentId
     * @param toCheck
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    boolean checkForRequestedDay(int studentId, Date toCheck) throws SQLServerException, SQLException;

    /**
     * Delete the day where a student have not been present in the sql
     * @param studentID
     * @param date
     * @throws SQLServerException
     * @throws SQLException
     */
    void deleteNotificationRequests(int studentID, Date date) throws SQLServerException, SQLException;

    /**
     * Gets all the absentdays from the sqlserver where a student have not been in school
     * @param studentID
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<Date> getAbsentDays(int studentID) throws SQLServerException, SQLException;

    /**
     * Get and check if the student have set presence later on the day befor 15:00 and remove the not been there to been there.
     * @param studentID
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<Date> getAllNonRequestedAbsentDays(int studentID) throws SQLServerException, SQLException;

    /**
     * Get all the requested days from a student from the sql server
     * @param studentID
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<Date> getAllRequestsByStudent(int studentID) throws SQLServerException, SQLException;

    /**
     * Gets all the Teacher from the Sql server and puts in into a Arraylist
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<Teacher> getAllTeachers() throws SQLServerException, SQLException;

    /**
     * Get the student of a class from the SQL and put it into a ArrayList
     * @param className
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<Student> getClassStudents(String className) throws SQLServerException, SQLException;

    /**
     * Gets all the Classes from the Sql server and put it into a ArrayList
     * @param id
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<String> getClasses(int id) throws SQLServerException, SQLException;

    /**
     * Get the Student notifications from a student from the sql and put it into a arraylist so the teacher can see it
     * @param teacherID
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    ArrayList<StudentNotification> getTeacherNotifications(int teacherID) throws SQLServerException, SQLException;

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
    boolean requestAttendanceChange(int student, int teacher, Date toChange) throws SQLServerException, SQLException;

    /**
     * Setting the Attendance date into the sql server when a student hit the "im here" button
     * @param attendance
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    boolean setAttendance(Attendance attendance) throws SQLServerException, SQLException;

    /**
     * Checks if the user exist in the Sql server if it do send it to the right view.
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    User userLogin(String username, String password) throws SQLException;
    
}
