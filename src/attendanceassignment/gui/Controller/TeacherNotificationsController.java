/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.Controller;

import attendanceassignment.be.Student;
import attendanceassignment.gui.Model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class TeacherNotificationsController implements Initializable {
    private AttendanceModel m_attendanceModel;
    @FXML
    private TableView<Student> TVstudentEdit;
    @FXML
    private TableColumn<Student, String> colFirstName;
    @FXML
    private TableColumn<Student, String> colLastName;
    @FXML
    private TableColumn<Student, Integer> colSemester;
    @FXML
    private TableColumn<Student, String> colDate;
    /**
     * Initializes the controller class.
     * creates tableview on initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colFirstName.setCellValueFactory(
                new PropertyValueFactory("username"));
        colLastName.setCellValueFactory(
                new PropertyValueFactory("lastName"));
        colSemester.setCellValueFactory(
                new PropertyValueFactory("semester"));
        colDate.setCellValueFactory(
        Produit -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Date date = Produit.getValue().getAbsentDay();
            SimpleDateFormat s = new SimpleDateFormat("dd/MM-yyyy");
            property.setValue(s.format(date));
            return property;
            }
        );
        absentTV();
    }    
    
    /**
     *  closes the window
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
     * prepares data for tableview
     */
    private void absentTV(){
        ObservableList<Student> student = m_attendanceModel.getTeachEditTV();
        
        TVstudentEdit.setItems(student);
    }
    
    /**
     * ensures that a student is selected
     * "sends" reject message to student
     */
    @FXML
    private void btnReject(ActionEvent event) {
        Student selectedStudent = TVstudentEdit.getSelectionModel().getSelectedItem();
        if(selectedStudent != null)
        {
            JOptionPane.showMessageDialog(null,"The request has been rejected");
            
        }else{
            JOptionPane.showMessageDialog(null,"Please select a student");
        }
    }
    
    /**
     * ensures student is selected
     * "sends" accept message to student and "removes" absence
     */
    @FXML
    private void btnAccept(ActionEvent event) {
        Student selectedStudent = TVstudentEdit.getSelectionModel().getSelectedItem();
        if(selectedStudent != null)
        {
            JOptionPane.showMessageDialog(null,"The request has been accepted");
            
        }else{
            JOptionPane.showMessageDialog(null,"Please select a student");
        }
    }
    
    /**
     * set up so the controller can call methods from model
     */
    public void setUp(AttendanceModel attendanceModel) {
        m_attendanceModel = attendanceModel;
    }
}
