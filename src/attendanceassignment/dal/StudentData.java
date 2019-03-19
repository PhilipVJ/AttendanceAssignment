/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Student;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.scene.chart.PieChart;

/**
 *
 * @author Anders
 */
public class StudentData {
    /**
     * mock barchart data
     */
    public Student getStudentDataBarChart(String username){
       
       
        Student student1 = new Student();
        student1.setUsername("Karsten");
        student1.addAbsence(new Attendance("MON", 5));
        student1.addAbsence(new Attendance("TUE", 3));
        student1.addAbsence(new Attendance("WED", 4));
        student1.addAbsence(new Attendance("THU", 0));
        student1.addAbsence(new Attendance("FRI", 1));

        return student1;
    }
    
    /**
     * mock piechart data
     */
//     public ObservableList<PieChart.Data> getPieChartData(){
//        
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
//                new PieChart.Data("Days Absent 13", 13),
//                new PieChart.Data("Days For Semester 120", 120));
//                
//        return pieChartData;
//    }
     
     /**
     * mock tableview data
     */
     public ObservableList<Student> getAbsentTableview(){
        ObservableList<Student> student = FXCollections.observableArrayList();
        try {
            
            student.add(new Student("13/1-2019"));
            student.add(new Student("17/1-2019"));
            student.add(new Student("26/1-2019"));
            student.add(new Student("27/1-2019"));
            student.add(new Student("30/1-2019"));
            student.add(new Student("02/2-2019"));
            student.add(new Student("05/2-2019"));
            student.add(new Student("07/2-2019"));
            student.add(new Student("13/2-2019"));
            student.add(new Student("15/2-2019"));
            student.add(new Student("16/2-2019"));
            student.add(new Student("19/2-2019"));
            student.add(new Student("24/2-2019"));
            
            return student;
        } catch (ParseException ex) {
            Logger.getLogger(StudentData.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return student;
    } 
     
    /**
     * mock tableview data
     */
    public ObservableList<Student> getTeachEditTV(){
        ObservableList<Student> student = FXCollections.observableArrayList();
        try {
            
            student.add(new Student("Kasten"," Larsen",1,"13/1-2019"));
            student.add(new Student("Anders","Johansen",2,"17/1-2019"));
            student.add(new Student("Kenneth","Brodersen",2, "26/1-2019"));
            student.add(new Student("Mads","Madsen",3,"27/1-2019"));
            student.add(new Student("Niels","Bjerre",4,"30/1-2019"));
            
            
            return student;
        } catch (ParseException ex) {
            Logger.getLogger(StudentData.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return student;
    } 
    
    /**
     * mock tableview data
     */
    public ObservableList<Student> getSemester1Students(){
        ObservableList<Student> students1 = FXCollections.observableArrayList();
        students1.add(new Student("Karsten","Larsen",1,13,10.83));
        students1.add(new Student("Peter","Hansen",1,17,14.16));
        students1.add(new Student("Jonas","Thygesen",1,30,25));
        
        return students1;
    }
    
    /**
     * mock tableview data
     */
    public ObservableList<Student> getSemester2Students(){
        ObservableList<Student> students2 = FXCollections.observableArrayList();
        students2.add(new Student("Anders","Johansen",2,24,20));
        students2.add(new Student("Kenneth","Brodersen",2,6,5));
        students2.add(new Student("Kasper","Mikkelsen",2,19,15.83));
        return students2;
        }
    
    /**
     * mock tableview data
     */
    public ObservableList<Student> getSemester3Students(){
        ObservableList<Student> students3 = FXCollections.observableArrayList();
        students3.add(new Student("Michael","Nielsen",3,26,21.66));
        students3.add(new Student("Mads","Madsen",3,34,28.33));
        students3.add(new Student("Rune","Andersen",3,16,13.33));

        return students3;
        }
    
    /**
     * mock tableview data
     */
    public ObservableList<Student> getSemester4Students(){
        ObservableList<Student> students4 = FXCollections.observableArrayList();
        students4.add(new Student("Brian","Beck",4,22,18.33));
        students4.add(new Student("Niels","Bjerre",4,27,22.5));
        students4.add(new Student("Martin","Eriksen",4,4,3.33));
        return students4;
        }
    
}
