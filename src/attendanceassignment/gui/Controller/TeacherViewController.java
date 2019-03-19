/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.Controller;

import attendanceassignment.gui.Controller.TeacherNotificationsController;
import attendanceassignment.gui.Controller.TeacherDetailViewController;
import attendanceassignment.be.Student;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class TeacherViewController implements Initializable {
    
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private AttendanceModel m_attendanceModel;
    private Student selectedStudent;
    
    @FXML
    private TableView<Student> tvStudents;
    @FXML
    private TableColumn<Student, String> usernameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, Integer> semesterColumn;
    @FXML
    private TableColumn<Student, Integer> absenceColumn;
    @FXML
    private TableColumn<Student, Double> absencePercentColumn;
    @FXML
    private Label lblNotifications;
    
    /**
     * Initializes the controller class.
     * builds tableview on initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameColumn.setCellValueFactory(
                new PropertyValueFactory("username"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory("lastName"));
        semesterColumn.setCellValueFactory(
                new PropertyValueFactory("semester"));
        absenceColumn.setCellValueFactory(
                new PropertyValueFactory("absence"));
        absencePercentColumn.setCellValueFactory(
                new PropertyValueFactory("absencePercent"));
        
        lblNotifications.setText("5");
        
    }    
    
    /**
     * ensures that a student is selected
     * opens a new window
     */
    @FXML
    private void btnDetails(ActionEvent event) throws IOException {
        Student selectedStudent = tvStudents.getSelectionModel().getSelectedItem();
        if(selectedStudent != null)
        {
            
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/TeacherDetailView.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            TeacherDetailViewController tdwc = fxmlLoader1.getController();
            tdwc.setUp(new AttendanceModel());
            tdwc.buildBarchart();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }else{
            JOptionPane.showMessageDialog(null,"Please select a student");
        }
    }
    
    /**
     * closes the window
     */
    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/Login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        LoginController lgc = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
  
    /**
     * button sets tableview data
     */
    @FXML
    private void btn1sem(ActionEvent event) {
        ObservableList<Student> students = m_attendanceModel.getSemester1Students();
        tvStudents.getItems().clear();
        tvStudents.setItems(students);

    }

    /**
     * button sets tableview data
     */
    @FXML
    private void btn2sem(ActionEvent event) {
        ObservableList<Student> students2 = m_attendanceModel.getSemester2Students();
        tvStudents.getItems().clear();
        tvStudents.setItems(students2);
    }

    /**
     * button sets tableview data
     */
    @FXML
    private void btn3sem(ActionEvent event) {
        ObservableList<Student> students3 = m_attendanceModel.getSemester3Students();
        tvStudents.getItems().clear();
        tvStudents.setItems(students3);
    }
    
    /**
     * button sets tableview data
     */
    @FXML
    private void btn4sem(ActionEvent event) {
        ObservableList<Student> students4 = m_attendanceModel.getSemester4Students();
        tvStudents.getItems().clear();
        tvStudents.setItems(students4);
    }

    /**
     * set up so the controller can call methods from model
     */
      public void setUp(AttendanceModel attendanceModel) {
        m_attendanceModel = attendanceModel;
    }
      
    /**
     * opens notification window
     */
    @FXML
    private void btnViewNotifications(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/TeacherNotificationView.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            TeacherNotificationsController tnc = fxmlLoader1.getController();
            tnc.setUp(new AttendanceModel());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    
}
