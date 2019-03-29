/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;

/**
 *
 * @author Philip
 */
public class Utility {

    public static void createErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Returns the number of days between the given date and today
     *
     * @param toCompare
     * @return
     */
    public static long compareDateWithToday(Date toCompare) {
      
        java.sql.Date sqlDate = new java.sql.Date(toCompare.getTime());
        java.sql.Date sqlDateToday = new java.sql.Date(new Date().getTime());
        String dateBeforeString = sqlDate.toString();
        String dateAfterString = sqlDateToday.toString();

        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        return noOfDaysBetween;
    }
    
    public static double calculateAbsencePercentage(ArrayList<Date> allSchoolDays, ArrayList<Date> absentDays)
    {    
        double result = 0;
        double abscentDaye = absentDays.size();
        double allSchoolday = allSchoolDays.size();
        result = (abscentDaye / allSchoolday * 100.00);
        return result;
    }

    public static ArrayList<Integer> whichDayAbscent(ArrayList<Date> absentDays)
    {
        SimpleDateFormat dage = new SimpleDateFormat("EEEE");
        
        ArrayList<Integer> days = new ArrayList();

        int monday = 0;
        int tuesday = 0;
        int wedensday = 0;
        int thursday = 0;
        int friday = 0;

        for (Date whichDays : absentDays)
        {
            String dag = dage.format(whichDays);
            switch (dag)
            {
                case "mandag":
                    monday++;
                    break;
                case "tirsdag":
                    tuesday++;
                    break;
                case "onsdag":
                    wedensday++;
                    break;
                case "torsdag":
                    thursday++;
                    break;
                case "fredag":
                    friday++;
                    break;
            }
        }

        days.add(monday);
        days.add(tuesday);
        days.add(wedensday);
        days.add(thursday);
        days.add(friday);

        return days;
    }
    
}
