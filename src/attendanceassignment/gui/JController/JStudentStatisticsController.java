/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
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
    @FXML
    private Label lblFravær;
    @FXML
    private Label userNameTag;
    @FXML
    private LineChart<String, Double> lineChart;

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
    private void showOverview(ActionEvent event) throws IOException, SQLException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentDaysStatestik.fxml"));
        AnchorPane root = loader.load();
        JStudentDaysStatestikController con = loader.getController();
        con.setModel(atModel);
        con.loadView();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

    public void setModel(AttendanceModel atModel)
    {
        this.atModel = atModel;
    }

    public void loadView() throws SQLException
    {
        userNameTag.setText("Logget ind som: " + atModel.getUser().getFirstname());
        DecimalFormat df = new DecimalFormat("#.00");
        int id = atModel.getUser().getId();
        lblFravær.setText("Fravær: " + df.format(Utility.calculateAbsencePercentage(atModel.getAllSchoolDays(), atModel.getAbsentDays(id))) + "%");
        setchart();
    }

    public void setRootLayout(BorderPane toSet)
    {
        rootLayout = toSet;
    }

    void setUser() {
        userNameTag.setText(atModel.getUser().getFirstname());
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
        int id = atModel.getUser().getId();
        ArrayList<Date> allSchoolDays = atModel.getAllSchoolDays();
        ArrayList<Date> abscentDays = atModel.getAbsentDays(id);
        
        Utility.makeLineChart(allSchoolDays, abscentDays,lineChart);
    }


    
    
}
