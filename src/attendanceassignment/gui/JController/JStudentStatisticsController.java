/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Attendance;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JStudentStatisticsController implements Initializable
{

    @FXML
    private Label userNameTag;
    @FXML
    private Label attendanceTag;
    @FXML
    private JFXComboBox<?> requestChange;
    @FXML
    private JFXTextArea additionalText;
    @FXML
    private JFXListView<Attendance> listView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      listView.getItems().add(new Attendance("monday",7));
    }    

    @FXML
    private void logOut(ActionEvent event)
    {
    }

    @FXML
    private void showOverview(ActionEvent event)
    {
    }

    @FXML
    private void sendRequest(ActionEvent event)
    {
    }
    
}
