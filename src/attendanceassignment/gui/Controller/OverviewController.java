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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class OverviewController implements Initializable {

    private AttendanceModel m_attendanceModel;
    @FXML
    private BorderPane bpane;

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    
    /**
     * prepares barchart data
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
     * prepares piechar data
     */
    private PieChart pieChartData(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Days Absent 13", 13),
        new PieChart.Data("Days For Semester 120", 120));
        
        PieChart pChart = new PieChart(pieChartData);
        pChart.setTitle("Absence 10.83%");
        pChart.setClockwise(true);
        pChart.setStartAngle(90);
        
        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        
        pChart.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, e ->{
                caption.setTranslateX(e.getSceneX()+10);
                caption.setTranslateY(e.getSceneY()+10);
                caption.setText(String.valueOf(data.getPieValue()) + "%");
            });
            data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, e ->{
                caption.setText("");
                
            });
        });


        
        
        return pChart;
    }
    
    /**
     * builds barchart
     */
    public void buildBarchart(){
         bpane.setCenter(barChartData());
    }
    
    /**
     * builds piechart
     */
     public void buildPieChart(){
         bpane.setCenter(pieChartData());
    }

    /**
     * set up so the controller can call methods from model
     */
    public void setUp(AttendanceModel attendanceModel) {
        m_attendanceModel = attendanceModel;
    }
    
    /**
     * builds barchart on button press
     */
    @FXML
    private void btnBarChart(ActionEvent event) {
        bpane.setCenter(barChartData());
    }
    
    /**
     * builds piechart on button press
     */
    @FXML
    private void btnPieChart(ActionEvent event) {
       bpane.setCenter(pieChartData());
    }
    
    /**
     * closes the window
     */
    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/StudentView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        StudentViewController svc = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}

