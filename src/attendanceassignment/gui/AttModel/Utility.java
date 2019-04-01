/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

/**
 *
 * @author Philip
 */
public final class Utility
{

    private Utility()
    {
    }

    public static void createErrorAlert(String header, String content)
    {
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
    public static long compareDateWithToday(Date toCompare)
    {

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

    public static void makeLineChart(ArrayList<Date> allSchoolDays, ArrayList<Date> abscentDays, LineChart<String, Double> lineChart)
    {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        int alleSkoleDage = 0;
        double fravaersDage = 0;

        for (Date alleDage : allSchoolDays)
        {
            alleSkoleDage++;
            for (Date absentDay : abscentDays)
            {
                if (alleDage.equals(absentDay))
                {
                    fravaersDage++;
                }
            }

            int udregningsformel = (int) (fravaersDage / alleSkoleDage * 100);

            series.getData().add(new XYChart.Data("" + alleSkoleDage, udregningsformel));
        }

        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);

        lineChart.getXAxis().setLabel("Skoledage");
        lineChart.getYAxis().setLabel("Fravær i procent %");
    }

    public static void makeBarChart(ArrayList<Integer> abscentDays, BarChart<String, Integer> barChart)
    {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data("Mandag", abscentDays.get(0)));
        series.getData().add(new XYChart.Data("Tirsdag", abscentDays.get(1)));
        series.getData().add(new XYChart.Data("Onsdag", abscentDays.get(2)));
        series.getData().add(new XYChart.Data("Torsdag", abscentDays.get(3)));
        series.getData().add(new XYChart.Data("Fredag", abscentDays.get(4)));

        barChart.getData().add(series);
        barChart.setLegendVisible(false);
        barChart.getXAxis().setLabel("");
        barChart.getYAxis().setLabel("Hvor mange dage med fravær");
    }

    public static boolean checkNetwork() throws SocketException, IOException
    {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "ipconfig/all");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        boolean foundEASV = false;
        boolean foundDNS = false;

        while (true)
        {
            line = r.readLine();
            if (line == null)
            {
                return false;
            }
            if (line.contains("easv.dk"))
            {
                foundEASV = true;
            }
            if (line.contains("10.176.111.11"))
            {
                foundDNS = true;
            }

            if (foundEASV == true && foundDNS == true)
            {
                System.out.println("Netværk fundet");
                return true;
            }

        }

    }
    
     public static boolean checkNetworkOnlyByName() throws SocketException, IOException
    {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "netsh wlan show networks mode=Bssid");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while (true)
        {
            line = r.readLine();
            if (line == null)
            {
                return false;
            }

            if (line.contains("EASV"))
            {
                System.out.println("Fundet netværk igen");
                return true;
            }

        }

    }

}
