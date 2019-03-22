/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Anders
 */
public class Attendance {
    
    private String dayName;
    private int absence;
    int id;
    Date attendance;
    

    public Attendance() {
    }
    
    /**
     * attendance contructor with specific parameters
     */
    public Attendance (String dayName, int absence) {
        this.dayName = dayName;
        this.absence = absence;
    }

    /**
     * gets absence
     */
    public int getAbsence() {
        return absence;
    }
    
    public Attendance(int id, Date attendance) throws ParseException {
        this.id = id;
//        DateFormat format = new SimpleDateFormat("dd/MM-yyyy", Locale.ENGLISH);
//        Date date = format.parse("",attendance); 
        this.attendance = attendance; 
        
    }

    /**
     * gets absence
     */
    public void setDate(Date attendance){
        this.attendance = attendance;
    }
    
    public Date getDate(){
     return attendance;
    }
    
    public int getId() {
     return id;
    }

    /**
     *
     * @param id
     * set id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets absence
     */
    public void setAbsence(int absence) {
        this.absence = absence;
    }

    /**
     * gets day name
     */
    public String getDayName() {
        return dayName;
    }

    /**
     * sets day name
     */
    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

}
