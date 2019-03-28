/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    public Student(String firstName, String lastName, String className) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.className = new SimpleStringProperty();
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.className.set(className);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    /**
     * sets last name
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getClassName() {
        return className.get();
    }
    
    

}
