/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.bll;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.User;
import attendanceassignment.be.Student;
import attendanceassignment.dal.AbscensData;
import attendanceassignment.dal.UserDB;
import attendanceassignment.dal.StudentData;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Anders
 */
public class BLLManager {
    StudentData studentData;
    AbscensData abscensData;
    UserDB userDb;
    
    
    public BLLManager(UserDB uDb) throws IOException
    {
        userDb = uDb;
    }
    
    /**
     * calls barchart data from dal
     */
    public Student getStudentDataBarChart(String username){   
        Student students;
        students = studentData.getStudentDataBarChart(username);
        return students;
    }
    
    public boolean setAttendance(Attendance attendance) throws ParseException, SQLException{
        
        
        return abscensData.setAttendance(attendance);
                
    }
    /**
     * calls piechart data from dal
     */
//    public ObservableList<PieChart.Data> getStudentsDataPieChart (){
//        
//        ObservableList<PieChart.Data> pieChartData = studentData.getPieChartData();
//        return pieChartData;
//    }
    
    /**
     * calls tableview data from dal
     */
    public ObservableList<Student> getAbsentTableview(){
        ObservableList<Student> students = studentData.getAbsentTableview();
        return students;
    }
    
    /**
     * calls tableview data from dal
     */
    public ObservableList<Student> getTeachEditTV(){
        ObservableList<Student> students = studentData.getTeachEditTV();
        return students;
    }
    
    /**
     * calls tableview data from dal
     */
    public ObservableList<Student> getSemester1Students(){

        ObservableList<Student> students = studentData.getSemester1Students();
        return students;
    }
    
    /**
     * calls tableview data from dal
     */
    public ObservableList<Student> getSemester2Students(){

        ObservableList<Student> students = studentData.getSemester2Students();
        return students; 
    }
    
    /**
     * calls tableview data from dal
     */
    public ObservableList<Student> getSemester3Students(){

        ObservableList<Student> students = studentData.getSemester3Students();
        return students;  
    }
    
    /**
     * calls tableview data from dal
     */
    public ObservableList<Student> getSemester4Students(){

        ObservableList<Student> students = studentData.getSemester4Students();
        return students;
    }

    public User login(String username, String password) throws SQLException, IOException 
    {
      
        return userDb.userLogin(username, password);
        
       
    }



    
    
}
