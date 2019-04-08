/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.ExceptionHandler;
import attendanceassignment.gui.AttModel.LoaderFactory;
import attendanceassignment.gui.AttModel.Utility;
import attendanceassignment.gui.AttModel.ViewEnum;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JStudentStatisticsController implements Initializable
{
    private AttendanceModel atModel;
    private BorderPane rootLayout;
    
    @FXML
    private Label lblFravær;
    @FXML
    private Label userNameTag;
    @FXML
    private LineChart<String, Double> lineChart;


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
        try {
        FXMLLoader loader = LoaderFactory.getInstance().createFXMLLoader(ViewEnum.JStudentDaysStatestik);
        AnchorPane root = loader.load();
        JStudentDaysStatestikController con = loader.getController();
        con.setModel(atModel);
        con.loadView();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
        } catch(Exception ex){
           ExceptionHandler.handleException(ex);
        }
    }

    public void setModel(AttendanceModel atModel)
    {
        this.atModel = atModel;
    }

    public void loadView()
    {
        try{
        userNameTag.setText("Logget ind som: " + atModel.getUser().getFirstname());
        DecimalFormat df = new DecimalFormat("#.00");
        int id = atModel.getUser().getId();
        lblFravær.setText("Fravær: " + df.format(Utility.calculateAbsencePercentage(atModel.getAllSchoolDays(), atModel.getAbsentDays(id))) + "%");
        setchart();
        } catch(Exception ex){
           ExceptionHandler.handleException(ex);
        }
    }

    public void setRootLayout(BorderPane toSet)
    {
        rootLayout = toSet;
    }

    void setUser() {
        userNameTag.setText(atModel.getUser().getFirstname());
    }
    
    @FXML
    private void goBack(ActionEvent event)
    {
        try {
        FXMLLoader loader = LoaderFactory.getInstance().createFXMLLoader(ViewEnum.JStudentMainView);
        AnchorPane root = loader.load();
        JStudentMainViewController con = loader.getController();
        con.setModel(atModel);
        con.setUser();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
        } catch(Exception ex){
           ExceptionHandler.handleException(ex);
        }
    }

    public void setchart()
    {
        try {
        int id = atModel.getUser().getId();
        ArrayList<Date> allSchoolDays = atModel.getAllSchoolDays();
        ArrayList<Date> abscentDays = atModel.getAbsentDays(id);
        
        Utility.makeLineChart(allSchoolDays, abscentDays,lineChart);
        } catch(Exception ex){
           ExceptionHandler.handleException(ex);
        }
    }


    
    
}
