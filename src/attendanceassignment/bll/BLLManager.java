/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.bll;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Student;
import attendanceassignment.be.User;
import attendanceassignment.be.Teacher;
import attendanceassignment.dal.AbscensData;
import attendanceassignment.dal.UserDB;

import attendanceassignment.dal.TeacherDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Anders
 */
public class BLLManager {

    AbscensData abscensData = new AbscensData();
    TeacherDAO tDAO = new TeacherDAO();
    UserDB userDb = new UserDB();

    public BLLManager() throws IOException {

    }

    public boolean setAttendance(Attendance attendance) throws ParseException, SQLException {

        return abscensData.setAttendance(attendance);

    }

    public User login(String username, String password) throws SQLException, IOException {

        return userDb.userLogin(username, password);

    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        return tDAO.getAllTeachers();

    }

    public ArrayList<Date> getAbsentDays(int id) throws SQLException {
        return abscensData.getAbsentDays(id);
    }

    public boolean requestAttendanceChange(int student, int teacher, Date date) throws SQLException {
        return abscensData.requestAttendanceChange(student, teacher, date);
    }

    public boolean checkForRequestedDay(int studentId, Date date) throws SQLException {
        return abscensData.checkForRequestedDay(studentId, date);
    }

    public ArrayList<Date> getRequestedDays(int id) throws SQLException {
        return abscensData.getAllRequestsByStudent(id);
    }

    public ArrayList<Date> getAllNonRequestedAbsentDays(int id) throws SQLException {
        return abscensData.getAllNonRequestedAbsentDays(id);
    }

    public void addAttendance(Attendance att) throws ParseException, SQLException {
        abscensData.setAttendance(att);
    }

    public ArrayList<Student> getClassStudents(String className) throws SQLException {
        return abscensData.getClassStudents(className);
    }

    public boolean checkForAttendance(int studentId, Date date) throws SQLException {
        return abscensData.checkForAttendance(studentId, date);
    }

    public ArrayList<Date> getAllSchoolDays() throws SQLException {

        return abscensData.allSchoolDays();

    }
    
//    public ArrayList<Student> getTeacherNotifications(int id) throws SQLException {
//
//        return abscensData.getTeacherNotifications(id);
//
//    }
}
