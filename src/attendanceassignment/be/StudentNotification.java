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
import java.util.Date;
import java.util.Locale;
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
//    private final String absence;

    public StudentNotification(String firstName, String lastName , String className, String absentDay) throws ParseException {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        DateFormat format = new SimpleDateFormat("dd/MM-yyyy");
        Date date = format.parse(absentDay);
        this.absentDay = date;
        this.className = new SimpleStringProperty();
        
        this.firstName.set(firstName);
        this.lastName.set(lastName);       
        this.className.set(className);
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public Date getAbsentDay() {
        return absentDay;
    }

   

    public StringProperty getClassName() {
        return className;
    }

    public void setAbsentDay(Date absentDay) {
        this.absentDay = absentDay;
    }

    @Override
    public String toString() {
        return  lastName +""  + firstName + "" + absentDay + ""+ className ;
    }
  
    
}
