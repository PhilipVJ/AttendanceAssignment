/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.bll;

import attendanceassignment.be.Person;
import attendanceassignment.be.Student;
import attendanceassignment.be.Teacher;
import attendanceassignment.dal.UserDB;
import attendanceassignment.dal.StudentData;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javax.swing.JOptionPane;

/**
 *
 * @author Anders
 */
public class BLLManager {
    StudentData studentData = new StudentData();
    
    UserDB userDb;
    
    
    public BLLManager() throws IOException
    {
        userDb = new UserDB();
    }
    
    /**
     * calls barchart data from dal
     */
    public Student getStudentDataBarChart(String username){   
        Student students;
        students = studentData.getStudentDataBarChart(username);
        return students;
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

    public Person login(String username, String password) throws SQLException, IOException 
    {
      
        return userDb.userLogin(username, password);
        
       
    }



    
    
}
