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

    public Student(String firstName, String lastName, String className, double absence) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.className = new SimpleStringProperty();
        this.absence = new SimpleStringProperty();
        
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
