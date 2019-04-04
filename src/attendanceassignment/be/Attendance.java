/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import java.util.Date;

/**
 *
 * @author Anders
 */
public class Attendance
{

    int id;
    Date attendance;

    /**
     * Constructor for the Attendance class
     * @param id
     * @param attendance 
     */
    public Attendance(int id, Date attendance)
    {
        this.id = id;
        this.attendance = attendance;

    }

    /**
     * Sets the date for attendance
     * @param attendance 
     */
    public void setDate(Date attendance)
    {
        this.attendance = attendance;
    }

    /**
     * Gets the date for attendance class
     * @return Date
     */
    public Date getDate()
    {
        return attendance;
    }

    /**
     * Getting the Id for attendance class
     * @return Id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set the Id for the attendance class
     * @param id 
     */
    public void setId(int id)
    {
        this.id = id;
    }

}
