/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.gui.AttModel.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JStudentStatisticsController implements Initializable
{
    @FXML
    private Label lblFravær;
    @FXML
    private Label userNameTag;
    @FXML
    private LineChart<String, Double> lineChart;
    @FXML
    private Label checker;

    private AttendanceModel atModel;
    private BorderPane rootLayout;

    /**
     * Initializes the controller @FXML private JFXListView<?> requests; class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }

    @FXML
    private void showOverview(ActionEvent event)
    {
    }

    public void setModel(AttendanceModel atModel)
    {
        this.atModel = atModel;
    }

    public void loadView() throws SQLException
    {
        userNameTag.setText(atModel.getUser().getFirstname());
        DecimalFormat df = new DecimalFormat("#.00");
        lblFravær.setText("" + df.format(calculateDaysProcent()));
        setchart();
    }

    public void setRootLayout(BorderPane toSet)
    {
        rootLayout = toSet;
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentMainView.fxml"));
        AnchorPane root = loader.load();
        JStudentMainViewController con = loader.getController();
        con.setModel(atModel);
        con.setUser();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

    public void setchart() throws SQLException
    {
        
        ArrayList<Date> allSchoolDays = atModel.getAllSchoolDays();
        ArrayList<Date> abscentDays = atModel.getAbsentDays();
        
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        
        double alleSkoleDage = 0;
        double fravaersDage = 0;
        
        for (Date alleDage : allSchoolDays)
        {
            alleSkoleDage++;
            for (Date absentDay : abscentDays)
            {
                if(alleDage.equals(absentDay))
                {
                    fravaersDage++;
                }
            }
             
            int udregningsformel = (int) (fravaersDage / alleSkoleDage * 100);
            
            series.getData().add(new XYChart.Data("" + alleSkoleDage, udregningsformel));
            
        }
        
        lineChart.setTitle("Fraværs statistik");
        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        lineChart.getXAxis().setLabel("Antal dage til dagsdato");
        lineChart.getXAxis().setTranslateX(-30);
        lineChart.getYAxis().setLabel("Fravær i procent %");
        
        
    }
    
    
    public double calculateDaysProcent() throws SQLException
    {
        ArrayList<Date> allSchoolDays = atModel.getAllSchoolDays();
        ArrayList<Date> abscentDays = atModel.getAbsentDays();
        
        double result = 0;
        
        System.out.println("hvilkendag:" + allSchoolDays.toString());
        
        double abscentDaye = abscentDays.size();
        double allSchoolday = allSchoolDays.size();
        System.out.println("fraværsdage: " + abscentDaye);
        System.out.println("skoledage: " + allSchoolday);
        
        result = (abscentDaye / allSchoolday * 100.00);
        System.out.println("udregn for helvede" + result);
        return result;
    }
    
    
    

}
