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
   private String password;
    String username;
    int absence;
    int semester;
    Date absentDay;
    double absencePercent;
    private List<Attendance> Weekdays = new ArrayList<>();
    
    /**
     * constructor for student with specific parameters
     */
    public Student(String username, String lastName, int semester, int absence, double absencePercent) {
        this.username = username;
        this.lastName.set(lastName);
        this.semester = semester;
        this.absence = absence;
        this.absencePercent = absencePercent;
    }   
    
    public Student(String firstName, String lastName)
    {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public Student(){
    }
    /**
     * 
     */
    public Student(String absentDay) throws ParseException {

        DateFormat format = new SimpleDateFormat("dd/MM-yyyy", Locale.ENGLISH);
        Date date = format.parse(absentDay);
        this.absentDay = date; 
    }
    
    /**
     * constructor for student with specific parameters
     */
    public Student(String username, String lastName, int semester, String absentDay) throws ParseException{
        
        DateFormat format = new SimpleDateFormat("dd/MM-yyyy", Locale.ENGLISH);
        Date date = format.parse(absentDay);
        
        this.username = username;
        this.lastName.set(lastName);
        this.semester = semester;
        this.absentDay = date;
    }
            
    /**
     * gets absent day
     */
     public Date getAbsentDay(){
        return absentDay;
    }
    
    /**
     * sets absent day
     */
    public void setAbsentDay(Date absentDay){
        this.absentDay = absentDay;
    }
    
    /**
     * gets semester
     */
    public int getSemester(){
        return semester;
    }
    
    /**
     * sets semester
     */
    public void setSemester(int semester){
        this.semester = semester;
    }
    
    /**
     * gets absence
     */
    public int getAbsence(){
        return absence;
    }
    
    /**
     * sets absence
     */
    public void setAbsence(int absence){
        this.absence = absence;
    }
    
    /**
     * gets absence in percent
     */
    public double getAbsencePercent(){
        return absencePercent;
    }
    
    /**
     * sets absence in percent
     */
    public void setAbsencePercent(double absentpercent){
        this.absencePercent = absencePercent;
    }
    
    /**
     * gets last name
     */
    public String getLastName(){
        return lastName.get();
    }
    
    /**
     *  sets last name
     */
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    
    /**
     * not yet in use
     */
    public String getPassword() {
        return password;
    }
    
    /**
     *  not yet in use
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     *  gets username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * sets username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets list of weekdays
     */
    public List<Attendance> getWeekdayList(){
        return Weekdays;
    }
   
    /**
     * adds attendance to weekdays
     */        
    public void addAbsence(Attendance absence){
            Weekdays.add(absence);
    }
    
}
