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

    int id;
    Date attendance;

    public Attendance(int id, Date attendance) {
        this.id = id;
        this.attendance = attendance;

    }

    public void setDate(Date attendance) {
        this.attendance = attendance;
    }

    public Date getDate() {
        return attendance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
