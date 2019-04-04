/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anders
 */
public class StudentNotification extends RecursiveTreeObject<StudentNotification>{
    
    private final StringProperty lastName;
    private final StringProperty firstName;
    private Date absentDay;
    private final StringProperty className;
    private final int studentID;

    
    public StudentNotification(String firstName, String lastName , String className, Date absentDay, int studentID) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        
        this.absentDay = absentDay;
        this.className = new SimpleStringProperty();
        this.studentID = studentID;
        
        this.firstName.set(firstName);
        this.lastName.set(lastName);       
        this.className.set(className);
        
    }

    public int getStudentID() {
        return studentID;
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public Date getAbsentDay() {
        return absentDay;
    }

    public String getClassName() {
        return className.get();
    }

    public void setAbsentDay(Date absentDay) {
        this.absentDay = absentDay;
    }

    @Override
    public String toString() {
        return  lastName +""  + firstName + "" + absentDay + ""+ className ;
    }
  
    
}
