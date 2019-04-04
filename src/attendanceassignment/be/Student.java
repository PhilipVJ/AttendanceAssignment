/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.text.DecimalFormat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anders
 */
public class Student extends RecursiveTreeObject<Student> {

    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty className;
    private final StringProperty absence;
    private final double absenceAsNumber;
    private int id=-1;

    /**
     * Constructor for the Student class
     * @param firstName
     * @param lastName
     * @param className
     * @param absence
     * @param id 
     */
    public Student(String firstName, String lastName, String className, double absence, int id) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.className = new SimpleStringProperty();
        this.absence = new SimpleStringProperty();
        absenceAsNumber = absence;
        this.id = id;
        
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.className.set(className);
        
        if(absence!=0){
        DecimalFormat df = new DecimalFormat("#.0");
        this.absence.set(df.format(absence));
        }
        else
        {
        this.absence.set("0.0");
        }

    }

    /**
     * Gets the first name for a student
     * @return 
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Gets the last name for a student
     * @return 
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Sets the last name for a student
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Gets the class name for a student
     * @return 
     */
    public String getClassName() {
        return className.get();
    }

    /**
     * Gets the Absens for a student
     * @return 
     */
    public String getAbsence() {

        return absence.get();
    }
    
    /**
     * Gets the absens as a double for a student
     * @return 
     */
    public double getAbsenceDouble()
    {
        return absenceAsNumber;
    }

    /**
     * Gets the Id for a student
     * @return 
     */
    public int getId() {
        return id;
    }
    
    
    
    

}
