/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Teacher;
import attendanceassignment.gui.AttModel.AttendanceModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JStudentStatisticsController implements Initializable {

    @FXML
    private Label userNameTag;
    @FXML
    private Label attendanceTag;

    @FXML
    private JFXListView<Teacher> teacherView;
    private AttendanceModel atModel;
    private BorderPane rootLayout;
    @FXML
    private JFXListView<Date> absentDays;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void showOverview(ActionEvent event) {
    }

    @FXML
    private void sendRequest(ActionEvent event) {
        
    atModel.requestAttendanceChange(teacherView.getSelectionModel().getSelectedItem().getId(),absentDays.getSelectionModel().getSelectedItem());
    }

    void setModel(AttendanceModel atModel) {
        this.atModel = atModel;
    }

    void loadView() throws SQLException {
        userNameTag.setText(atModel.getUser().getFirstname());
        
        // Loads the teacher view
        ArrayList<Teacher> allTeachers = atModel.getAllTeachers();
        for (Teacher teacher : allTeachers) {

            teacherView.getItems().add(teacher);
        }
        // Loads the absence days view
        ArrayList<Date> absentDays = atModel.getAbsentDays();
        for (Date date : absentDays)
        {
            this.absentDays.getItems().add(date);
        }
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

}
