/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Student;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JTeacherViewController implements Initializable {

    private AttendanceModel aModel;
    private BorderPane rootLayout;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label userNameTag;
    @FXML
    private Label numberOfRequests;
    @FXML
    private JFXTreeTableView<Student> tableView;
    @FXML
    private JFXComboBox<String> classChooser;
    @FXML
    private TreeTableColumn<Student, String> firstNameCol;
    @FXML
    private TreeTableColumn<Student, String> lastNameCol;
    @FXML
    private TreeTableColumn<Student, String> classNameCol;
    @FXML
    private TreeTableColumn<Student, String> absenceCol;
    @FXML
    private Label classAbsence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        classAbsence.setVisible(false);
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void showRequests(ActionEvent event) {
    }

    public void setModel(AttendanceModel model) {
        this.aModel = model;
    }

    public void setUser() {
        userNameTag.setText("Logget ind som: " + aModel.getUser().getFirstname());
        
        //mock husk at ændre!!!!
        numberOfRequests.setText("Antal anmodninger: " + "2");
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

    public void loadViews() {
        // Loads the combobox with all the teachers classes
        ObservableList<String> allClasses = FXCollections.observableArrayList(aModel.getUser().getAllClasses());
        classChooser.setItems(allClasses);

        // Setup tableview
        firstNameCol.setSortable(false);
        lastNameCol.setSortable(false);
        classNameCol.setSortable(false);
        absenceCol.setSortable(false);
                
        firstNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastName"));
        classNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("className"));
        absenceCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("absence"));

        tableView.setShowRoot(false);
        
      

    }

    @FXML
    private void chooseClass(ActionEvent event) {
        String className = classChooser.getValue();
        try {
            ArrayList<Student> classStudents = aModel.getClassStudents(className);
            ObservableList<Student> students = FXCollections.observableArrayList(classStudents);
            TreeItem<Student> root = new RecursiveTreeItem<>(students, RecursiveTreeObject::getChildren);
            tableView.setRoot(root);
            
            classAbsence.setVisible(true);

            double combinedAbsence = 0;
            for (Student classStudent : classStudents) {
                combinedAbsence += classStudent.getAbsenceDouble();
            }
            
            Comparator<TreeItem<Student>> byAbsence = ((t, t1) -> {
                return (int)(t1.getValue().getAbsenceDouble()-t.getValue().getAbsenceDouble());
            } );
                          
            root.getChildren().sort(byAbsence);
        
            double averageAbsence = combinedAbsence / classStudents.size();

            classAbsence.setText("Gennemsnitlig fravær: " + averageAbsence + "%");

        } catch (SQLException ex) {
            Logger.getLogger(JTeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showAttendanceLine(ActionEvent event) throws SQLException {
        TreeItem<Student> chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null) {
            Stage newStage = new Stage();

            NumberAxis y = new NumberAxis();
            CategoryAxis x = new CategoryAxis();
       
            LineChart lineC = new LineChart(x, y);
            
            int id = chosenStudent.getValue().getId();

            Utility.makeLineChart(aModel.getAllSchoolDays(), aModel.getAbsentDays(id), lineC);
            BorderPane bPane = new BorderPane();
            bPane.setCenter(lineC);

            bPane.getStyleClass().add("background");

            Scene newScene = new Scene(bPane);
            newStage.setHeight(600);
            newStage.setWidth(1000);
            newStage.setResizable(false);

            newScene.getStylesheets().add("attendanceassignment/css/Style.css");

            newStage.setScene(newScene);
            newStage.show();
        }
    }

    @FXML
    private void showAttendanceBar(ActionEvent event) throws SQLException {

        TreeItem<Student> chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null) {

            Stage newStage = new Stage();

            NumberAxis y = new NumberAxis();
            CategoryAxis x = new CategoryAxis();
            
            BarChart barC = new BarChart(x, y);
            int id = chosenStudent.getValue().getId();
            ArrayList<Integer> absentDays = Utility.whichDayAbscent(aModel.getAbsentDays(id));
            Utility.makeBarChart(absentDays, barC);
            BorderPane bPane = new BorderPane();
            bPane.setCenter(barC);

            bPane.getStyleClass().add("background");

            Scene newScene = new Scene(bPane);
            newStage.setHeight(600);
            newStage.setWidth(1000);
            newStage.setResizable(false);

            newScene.getStylesheets().add("attendanceassignment/css/Style.css");

            newStage.setScene(newScene);
            newStage.show();

        }
    }
}
