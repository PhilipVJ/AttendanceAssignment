/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Student;
import attendanceassignment.be.Teacher;
import attendanceassignment.be.User;
import attendanceassignment.bll.BLLManager;
import attendanceassignment.dal.UserDBFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Anders
 */
public class AttendanceModel {

    private BLLManager bllMan;

    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private User user;

    public AttendanceModel() throws IOException {
        bllMan = new BLLManager(UserDBFactory.getUserDB());
    }

    /**
     * calls barchart data from bll applies attendance to weekdays
     */
    public XYChart.Series getStudentDataBarChart(String username) {
        Student student;
        student = bllMan.getStudentDataBarChart(username);

        //Create dataset for the barchart
        XYChart.Series dataset = new XYChart.Series();
//        dataset.setName("");

        for (Attendance attendance : student.getWeekdayList()) {
            dataset.getData().add(new XYChart.Data(attendance.getDayName(), attendance.getAbsence()));
        }
        return dataset;
    }
    
    public boolean setAttendance(Attendance attendance) throws ParseException, SQLException{
        
        
        return bllMan.setAttendance(attendance);
                
    }

    /**
     * calls piechart data from bll
     */
//    public ObservableList<PieChart.Data> getStudentsDataPieChart (){
//        
//        ObservableList<PieChart.Data> pieChartData = bllManager.getStudentsDataPieChart();
//        return pieChartData;
//    }
    /**
     * calls tableview data from bll
     */
    public ObservableList<Student> getAbsentTableview() {

        ObservableList<Student> student = bllMan.getAbsentTableview();
        return student;
    }

    /**
     * calls tableview data from bll
     */
    public ObservableList<Student> getTeachEditTV() {

        ObservableList<Student> student = bllMan.getTeachEditTV();
        return student;
    }

    /**
     * calls tableview data from bll
     */
    public ObservableList<Student> getSemester1Students() {

        ObservableList<Student> students = bllMan.getSemester1Students();
        return students;
    }

    /**
     * calls tableview data from bll
     */
    public ObservableList<Student> getSemester2Students() {

        ObservableList<Student> students2 = bllMan.getSemester2Students();
        return students2;
    }

    /**
     * calls tableview data from bll
     */
    public ObservableList<Student> getSemester3Students() {

        ObservableList<Student> students3 = bllMan.getSemester3Students();
        return students3;
    }

    /**
     * calls tableview data from bll
     */
    public ObservableList<Student> getSemester4Students() {

        ObservableList<Student> students4 = bllMan.getSemester4Students();
        return students4;
    }

    public User login(String username, String password) throws SQLException, IOException {
        user = bllMan.login(username, password);
        return user;
    }
    
    public User getUser()
    {
        return user;
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException {
      return bllMan.getAllTeachers();
    }

    public ArrayList<Date> getAbsentDays() throws SQLException {
        return bllMan.getAbsentDays(user.getId());
    }

    public boolean requestAttendanceChange(int teacher, Date date) throws SQLException {
       return bllMan.requestAttendanceChange(user.getId(),teacher,date);
    }

    public boolean checkForRequestedDay(Date date) throws SQLException {
      return bllMan.checkForRequestedDay(user.getId(),date);
    }

    public ArrayList<Date> getRequestedDays() throws SQLException {
      return bllMan.getRequestedDays(user.getId());
    }

    public ArrayList<Date> getAllNonRequestedAbsentDays() throws SQLException {
       return bllMan.getAllNonRequestedAbsentDays(user.getId());
    }

    public void addAttendance(Attendance att) throws ParseException, SQLException
    {
        bllMan.addAttendance(att);
    }


}

