/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Teacher;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
    private AttendanceModel atModel;
    private BorderPane rootLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    void loadView() throws SQLException
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

    @FXML
    private void sendRequest(ActionEvent event)
    {

        Date dateToReq = absentDays.getSelectionModel().getSelectedItem();
        Teacher teacher = teacherView.getSelectionModel().getSelectedItem();

        if (dateToReq == null)
        {
            checker.setText("Vælg en dag du ønsker ændret");
            return;
        }

        if (teacher == null)
        {
            checker.setText("Vælg en lærer at sende anmodningen til");
            return;
        }

        if (Utility.compareDateWithToday(dateToReq) > 14)
        {
            Utility.createErrorAlert("Tidsperiode overskredet", "Anmodninger kan kun sendes hvis den valgte dato er indenfor de sidste 14 dage");
            return;
        }

        try
        {
            boolean request = atModel.requestAttendanceChange(teacher.getId(), dateToReq);
            if (request)
            {
                checker.setText("Anmodning afsendt");
                requests.getItems().add(dateToReq);
                absentDays.getItems().remove(dateToReq);
            }
        } catch (SQLException ex)
        {
            Utility.createErrorAlert("Problemer med SQL serveren", "En fejl opstod med SQl serveren. Prøv igen senere");
        }

    }

}
