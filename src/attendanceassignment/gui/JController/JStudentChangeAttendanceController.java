/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Teacher;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.ExceptionHandler;
import attendanceassignment.gui.AttModel.Utility;
import attendanceassignment.gui.AttModel.ViewEnum;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JStudentChangeAttendanceController implements Initializable
{

    private AttendanceModel atModel;
    private BorderPane rootLayout;

    @FXML
    private JFXListView<Date> absentDays;
    @FXML
    private JFXListView<Teacher> teacherView;
    @FXML
    private JFXListView<Date> requests;
    @FXML
    private Label checker;
    @FXML
    private Label userNameTag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    void loadView()
    {
        try
        {
            String studentClassName = atModel.getUser().getAllClasses().get(0);
            userNameTag.setText("Logget ind som: " + atModel.getUser().getFirstname());

            // Loads the teacher view
            ArrayList<Teacher> allTeachers = atModel.getAllTeachers();

            for (Teacher teacher : allTeachers)
            {
                // Checks if the teacher teaches in the students class
                if (teacher.getClassses().contains(studentClassName))
                {
                    teacherView.getItems().add(teacher);
                }
            }
            // Loads the absence days view
            ArrayList<Date> absentDays = atModel.getAllNonRequestedAbsentDays();
            for (Date date : absentDays)
            {
                this.absentDays.getItems().add(date);
            }

            // Loads the request view
            ArrayList<Date> requests = atModel.getRequestedDays();
            for (Date date : requests)
            {
                this.requests.getItems().add(date);
            }
        } catch (Exception ex) 
        {
            ExceptionHandler.handleException(ex);

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

    @FXML
    private void goBack(ActionEvent event)
    {
        try{
        FXMLLoader loader = atModel.createFXMLLoader(ViewEnum.JStudentMainView);
        AnchorPane root = loader.load();
        JStudentMainViewController con = loader.getController();
        con.setModel(atModel);
        con.setUser();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
        } catch (Exception ex) 
        {
            ExceptionHandler.handleException(ex);
        } 
    }

    @FXML
    private void sendRequest(ActionEvent event)
    {
        Date dateToReq = absentDays.getSelectionModel().getSelectedItem();
        Teacher teacher = teacherView.getSelectionModel().getSelectedItem();
        //sikrer at en dato er valgt
        if (dateToReq == null)
        {
            checker.setText("Vælg en dag du ønsker ændret");
            return;
        }
        //sikrer at en teacher er valgt
        if (teacher == null)
        {
            checker.setText("Vælg en lærer at sende anmodningen til");
            return;
        }
        //sikrer at man højst kan ændre sit fravær i op til 14 dage
        if (Utility.compareDateWithToday(dateToReq) > 14)
        {
            Utility.createErrorAlert("Tidsperiode overskredet", "Anmodninger kan kun sendes hvis den valgte dato er indenfor de sidste 14 dage");
            return;
        }

        try
        {
            //sender datoen videre til databasen
            boolean request = atModel.requestAttendanceChange(teacher.getId(), dateToReq);
            if (request)
            {
                checker.setText("Anmodning afsendt");
                requests.getItems().add(dateToReq);
                absentDays.getItems().remove(dateToReq);
            }
        } catch (Exception ex) 
        {
            ExceptionHandler.handleException(ex);

        } 

    }

}
