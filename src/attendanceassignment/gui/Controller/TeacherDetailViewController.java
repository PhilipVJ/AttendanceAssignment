/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.Controller;

import attendanceassignment.gui.Model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class TeacherDetailViewController implements Initializable {
    
    private AttendanceModel m_attendanceModel;


    
    @FXML
    private BorderPane bPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }
    /**
     * prepates barchart data
     */
    private BarChart barChartData() {
        
        //Define category axises
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Weekdays");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of absent days");
                
        BarChart bChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataset;
        dataset = m_attendanceModel.getStudentDataBarChart("a");
         dataset.setName("Karsten");
        //Add dataset to chart
        bChart.getData().add(dataset);
        
        
        return bChart;
    }
    
    /**
     * builds barchart
     */
    public void buildBarchart(){
         bPane.setCenter(barChartData());
    }
    
    /**
     * closes the window
     */
    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/TeacherView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        TeacherViewController twc = fxmlLoader.getController();
        twc.setUp(new AttendanceModel());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    /**
     * set up so the controller can call methods from model
     */
    public void setUp(AttendanceModel attendanceModel) {
        m_attendanceModel = attendanceModel;
    }
  
}
