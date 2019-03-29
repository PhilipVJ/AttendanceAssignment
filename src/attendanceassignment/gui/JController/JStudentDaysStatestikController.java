/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.User;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    public void getBackButton(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentStatistics.fxml"));
        AnchorPane root = loader.load();
        JStudentStatisticsController con = loader.getController();
        con.setModel(atModel);
        con.setUser();
        con.loadView();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

    public void loadView() throws SQLException
    {
        userLabel.setText(atModel.getUser().getFirstname());
        openBarChart();
    }
    
    public void openBarChart() throws SQLException
    {
        int id = atModel.getUser().getId();
        ArrayList<Integer> abscentDays = Utility.whichDayAbscent(atModel.getAbsentDays(id));
        
        Utility.makeBarChart(abscentDays, barChart); 
       
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
