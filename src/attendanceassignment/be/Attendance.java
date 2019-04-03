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

    public Attendance(int id, Date attendance)
    {
        this.id = id;
        this.attendance = attendance;

    }

    public void setDate(Date attendance)
    {
        this.attendance = attendance;
    }

    public Date getDate()
    {
        return attendance;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}
