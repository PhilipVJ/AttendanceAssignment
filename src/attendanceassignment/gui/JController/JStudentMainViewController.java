/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Attendance;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
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
    private void setAttendance(ActionEvent event) throws ParseException, SQLException, IOException {
        int curTime = LocalDateTime.now().getHour();
        int tooLate = 25;
        int tooEarly = 0;
        date = new Date();
        boolean attendanceHasBeenSet = atModel.checkForAttendance(date);
        
        if(attendanceHasBeenSet == false && (curTime < tooLate && curTime > tooEarly) && Utility.checkNetwork()==true){
             
                Attendance att = new Attendance(atModel.getUser().getId(), date);
                atModel.addAttendance(att);
        }
        if(attendanceHasBeenSet==true){
            Utility.createErrorAlert("Tilstedeværelse er sat", "Du er allerede sat som tilstedeværende for i dag");
        }
        if(curTime > tooLate){
            Utility.createErrorAlert("Udenfor tidsperioden", "Fravær kan kun sættes frem til klokken " + tooLate);
        }
        if(curTime < tooEarly){
            Utility.createErrorAlert("Udenfor tidsperioden", "Fravær kan først sættes fra klokken " + tooEarly);
        }         
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
        userLabel.setText("Logget ind som: " + atModel.getUser().getFirstname());
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

    @FXML
    private void changeAttendance(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentChangeAttendance.fxml"));
        AnchorPane root = loader.load();
        JStudentChangeAttendanceController con = loader.getController();
        con.setModel(atModel);
        con.loadView();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

}
