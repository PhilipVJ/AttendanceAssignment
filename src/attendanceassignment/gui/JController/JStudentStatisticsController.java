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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JStudentStatisticsController implements Initializable {

    @FXML
    private Label userNameTag;

    private AttendanceModel atModel;
    private BorderPane rootLayout;

    @FXML
    private Label checker;

    /**
     * Initializes the controller @FXML private JFXListView<?> requests; class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void showOverview(ActionEvent event) {
    }

    public void setModel(AttendanceModel atModel) {
        this.atModel = atModel;
    }

    public void loadView() throws SQLException {
        userNameTag.setText(atModel.getUser().getFirstname());

    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentMainView.fxml"));
        AnchorPane root = loader.load();
        JStudentMainViewController con = loader.getController();
        con.setModel(atModel);
        con.setUser();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

}
