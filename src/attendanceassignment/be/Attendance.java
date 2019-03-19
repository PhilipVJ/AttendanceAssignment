/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

/**
 *
 * @author Anders
 */
public class Attendance {
    
    private String dayName;
    private int absence;

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
