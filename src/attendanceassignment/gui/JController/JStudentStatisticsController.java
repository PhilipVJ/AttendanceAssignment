/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Teacher;
import attendanceassignment.gui.AttModel.Utility;
import attendanceassignment.gui.AttModel.AttendanceModel;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label checker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        absentDays.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Date date = absentDays.getSelectionModel().getSelectedItem();
                if (date != null) {
                    checkForRequest(date);
                }
            }

        });
        
        checker.setText("");

    }

    private void checkForRequest(Date date) {
        try {
            boolean hasBeenRequested = atModel.checkForRequestedDay(date);
            if (hasBeenRequested) {
                checker.setText("Allerede anmodet om ændring");
            } else {
                checker.setText("Ikke anmodet om ændring");
            }
        } catch (SQLException ex) {
            Utility.createErrorAlert("Problemer med SQL serveren", "En fejl opstod med forbindelsen til serveren");
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void showOverview(ActionEvent event) {
    }

    @FXML
    private void sendRequest(ActionEvent event) {

        Date dateToReq = absentDays.getSelectionModel().getSelectedItem();
        Teacher teacher = teacherView.getSelectionModel().getSelectedItem();
        
        if (dateToReq == null || teacher == null)
        {
            checker.setText("Vælg en dag og en lærer at sende anmodningen til");
            return;
        }

        try {
            boolean request = atModel.requestAttendanceChange(teacher.getId(), dateToReq);
            if (request) {
                checker.setText("Anmodning afsendt");
            }
        } catch (SQLException ex) {
            Utility.createErrorAlert("Problemer med SQL serveren", "Fejlen skyldes formentlig at du allerede har ansøgt om ændring på den pågældende dag");
        }
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
        System.out.println("Size:" + absentDays.size());
        for (Date date : absentDays) {
            this.absentDays.getItems().add(date);
        }
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

}
