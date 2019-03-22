/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.gui.AttModel.AttendanceModel;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class JStudentMainViewController implements Initializable
{
    
    @FXML
    private Label userLabel;
    private AttendanceModel atModel;
    private Date date;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void setAttendance(ActionEvent event)
    {
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
   private void showStatistics(ActionEvent event)
    {
    }

    void setModel(AttendanceModel atModel) {
        this.atModel = atModel;
    }

    void setUser() {
       userLabel.setText(atModel.getUser().getFirstname());
    }
    
}
