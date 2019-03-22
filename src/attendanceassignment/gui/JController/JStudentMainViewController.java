/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.gui.AttModel.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class JStudentMainViewController implements Initializable {

    @FXML
    private Label userLabel;
    private AttendanceModel atModel;
    private Date date;
    private BorderPane rootLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void setAttendance(ActionEvent event) {
//        java.sql.Date sqlDate = new java.sql.Date(attendance.getDate().getTime());
//        date = atModel.setAttendance();

//        Date date = new Date();
//        SimpleDateFormat s = new SimpleDateFormat("dd/MM-yyyy");
//        lblDate.setText(s.format(date));
//        Attendance att = new Attendance(2,new Date());
//         
//        AbscensData abs = new AbscensData();
//        abs.setAttendance(att);
//        atModel.setAttendance(attendance);
    }

    @FXML
    private void showStatistics(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentStatistics.fxml"));
        AnchorPane root = loader.load();
        JStudentStatisticsController con = loader.getController();
        con.setModel(atModel);
        con.loadView();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

    void setModel(AttendanceModel atModel) {
        this.atModel = atModel;
    }

    void setUser() {
        userLabel.setText(atModel.getUser().getFirstname());
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

}
