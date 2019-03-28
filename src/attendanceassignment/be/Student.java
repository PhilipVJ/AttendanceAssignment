/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anders
 */
public class Student extends RecursiveTreeObject<Student> {

    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty className;
    private StringProperty absence;

    public Student(String firstName, String lastName, String className, double absence) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.className = new SimpleStringProperty();
        this.absence = new SimpleStringProperty();
        
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.className.set(className);
        DecimalFormat df = new DecimalFormat("#.00");
        
        this.absence.set(df.format(absence));
        System.out.println("FRAVÆR: "+absence);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }


    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getClassName() {
        return className.get();
    }

    public String getAbsence() {

        return absence.get();
    }
    
    
    
    

}
