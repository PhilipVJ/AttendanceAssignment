/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.User;
import attendanceassignment.bll.BLLManager;
import attendanceassignment.dal.AttendanceDAO;
import attendanceassignment.dal.DbConnection;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.ExceptionHandler;
import attendanceassignment.gui.AttModel.Utility;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JLoginController implements Initializable {

    private AttendanceModel atModel;
    private BorderPane rootLayout;

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    
    public JLoginController(){
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BLLManager bll = new BLLManager(new AttendanceDAO(DbConnection.getInstance()));
            atModel = new AttendanceModel(bll);
        } catch (Exception ex) 
        {
            ExceptionHandler.handleException(ex);
        } 
    }
    
    /*
    *Login metode + sikring
    *
    */ 
    @FXML
    private void login(ActionEvent event) {
        try {
        User user = atModel.login(username.getText(), password.getText());
        //sikrer at useren findes.
        if (user==null)
        {
            Utility.createErrorAlert("Login fejlede", "Brugernavn eller adgangskode er ugyldigt. Prøv igen.");
            return;
        }
        if (user.getType().equals("Teacher")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JTeacherView.fxml"));          
            AnchorPane root = loader.load();
            JTeacherViewController con = loader.getController();
            con.setModel(atModel);
            con.setRootLayout(rootLayout);
            con.setUser();
            con.loadViews();
            rootLayout.setCenter(root);
        }
        
        if (user.getType().equals("Student")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentMainView.fxml"));
            AnchorPane root = loader.load();
            JStudentMainViewController con = loader.getController();
            con.setModel(atModel);
            con.setUser();
            con.setRootLayout(rootLayout);
            rootLayout.setCenter(root);
        }} catch (Exception ex) 
        {
            ExceptionHandler.handleException(ex);

        } 
        
            
        

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

}
