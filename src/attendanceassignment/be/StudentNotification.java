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

    /**
     * Get the StudentId for a student in notification class
     * @return StudentId
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Gets the last name of a student in notification class
     * @return LastName 
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Gets the first name of a student in notification class
     * @return FirstName
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Get the date for absent day of a student in notification class
     * @return AbsentDay
     */
    public Date getAbsentDay() {
        return absentDay;
    }

    /**
     * Gets the class name of a student in notification class
     * @return classnames in studentNotification
     */
    public String getClassName() {
        return className.get();
    }

    /**
     * Sets the date for absent of a student in notification class
     * @param absentDay 
     */
    public void setAbsentDay(Date absentDay) {
        this.absentDay = absentDay;
    }

    /**
     * Override to a string with lastname, firstname, absentday and classname
     * @return Lastname firstName, absentDays and className
     */
    @Override
    public String toString() {
        return  lastName +""  + firstName + "" + absentDay + ""+ className ;
    }
  
    
}
