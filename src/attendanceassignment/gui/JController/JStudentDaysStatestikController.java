/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author nikla
 */
public class JStudentDaysStatestikController implements Initializable {

    private AttendanceModel atModel;
    private BorderPane rootLayout;
    
    @FXML
    private Label userLabel;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private JFXButton btnBack;

    void setUser() {
        userLabel.setText(atModel.getUser().getFirstname());
    }
    
    @FXML
    public void getBackButton(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentStatistics.fxml"));
        AnchorPane root = loader.load();
        JStudentStatisticsController con = loader.getController();
        con.setModel(atModel);
        con.setUser();
        con.loadView();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);    
    } catch(IOException ex2){
            Utility.createErrorAlert("Database filen kunne ikke fines", "Sikre at filen er i den rette mappe og prøv igen");
        }
    }

    public void loadView()
    {
        userLabel.setText("Logget ind som: " + atModel.getUser().getFirstname());
        openBarChart();
    }
    
    public void openBarChart()
    {
        try {
            int id = atModel.getUser().getId();
        ArrayList<Integer> abscentDays = Utility.whichDayAbscent(atModel.getAbsentDays(id));
        
        Utility.makeBarChart(abscentDays, barChart);
        } catch (SQLException ex) {
            Utility.createErrorAlert("Programmet kan ikke få kontakt til serveren", "Prøv venligst igen senere eller kontakt support!");
        }
    }

    public void setModel(AttendanceModel atModel)
    {
        this.atModel = atModel;
    }
    
    public void setRootLayout(BorderPane toSet)
    {
        rootLayout = toSet;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
